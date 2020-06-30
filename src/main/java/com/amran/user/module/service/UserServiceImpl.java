package com.amran.user.module.service;

import com.amran.user.module.dto.UserDTO;
import com.amran.user.module.mapper.MapperService;
import com.amran.user.module.entity.User;
import com.amran.user.module.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Amran Hosssain on 6/15/2020
 * All Business logic will be implemented here; after got request from controller the service layer will work.
 * all kind of work business related logic and communicate with Database access layer if necessary.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    final UserRepository userRepository;
    final MapperService mapperService;
    final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperService mapperService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperService = mapperService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signupUser(UserDTO userDTO) {
        LOGGER.info("UserServiceImpl::signupUser() method call...");
        return saveOrUpdate(userDTO);
    }

    @Override
    public String saveOrUpdate(UserDTO userDTO) {
        LOGGER.info("UserServiceImpl::updateUser() method call...");
        try {
            User user = mapperService.mapUserFromUserDTO(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setUserRole(mapperService.mapUserRoleFromUserRoleDTO(userDTO.getUserRoleDTO()));
            User userEntity = userRepository.save(user);
            if (userEntity != null) {
                return HttpStatus.OK.name();
            }
        } catch (Exception ex) {
            LOGGER.error("Exception is " + ex.getMessage());
        }
        return "Operation execute failed!";
    }

    @Override
    public String deleteUserByUserId(Long userId) {
        LOGGER.info("UserServiceImpl::deleteUserByUserId() method call...");
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                return "User Not Found";
            }
            userRepository.delete(user);
            return HttpStatus.OK.name();
        } catch (Exception ex) {
            LOGGER.error("Exception is " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        LOGGER.info("UserServiceImpl::getAllUsers() method call...");
        List<User> userList = userRepository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(mapperService.mapUserDTOFromUser(user));
        }
        return dtoList;
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        LOGGER.info("UserServiceImpl::getUserByUserName() method call...");
        return mapperService.mapUserDTOFromUser(userRepository.findByUserName(userName));
    }

    @Override
    public UserDTO getUserByUserEmail(String email) {
        LOGGER.info("UserServiceImpl::getUserByUserEmail() method call...");
        return mapperService.mapUserDTOFromUser(userRepository.findByEmail(email));
    }

    @Override
    public UserDTO getUserByUserId(Long userId) {
        LOGGER.info("UserServiceImpl::getUserByUserId() method call...");
        return mapperService.mapUserDTOFromUser(userRepository.findByUserId(userId));
    }
}
