package com.amran.user.module.mapper;

import com.amran.user.module.dto.UserDTO;
import com.amran.user.module.dto.UserRoleDTO;
import com.amran.user.module.entity.User;
import com.amran.user.module.entity.UserRole;
import com.amran.user.module.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author : Amran Hosssain on 6/15/2020
 * In this component only Object mapping like DTO to Entity and Entity to DTO
 * DTO = Data Transaction Object
 * Encapsulate like setter and getter
 */
@Component
public class MapperServiceImpl implements MapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User mapUserFromUserDTO(UserDTO userDTO) {
        LOGGER.info("MapperServiceImpl::mapUserFromUserDTO() method call...");
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setGender(userDTO.getGender());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        return user;
    }

    @Override
    public UserDTO mapUserDTOFromUser(User user) {
        LOGGER.info("MapperServiceImpl::mapUserDTOFromUser() method call...");
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setGender(user.getGender());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

    @Override
    public UserRole mapUserRoleFromUserRoleDTO(UserRoleDTO userRoleDTO) {
        LOGGER.info("MapperServiceImpl::mapUserRoleFromUserRoleDTO() method call...");
        UserRole userRole = new UserRole();
        userRole.setRoleId(userRoleDTO.getRoleId());
        userRole.setRoleName(userRoleDTO.getRoleName().toUpperCase());
        userRole.setRoleStatus(userRoleDTO.getRoleStatus());
        userRole.setRoleCreationDate(userRoleDTO.getRoleCreationDate());
        return userRole;
    }

    @Override
    public UserRoleDTO mapUserRoleDTOFromUserRole(UserRole userRole) {
        LOGGER.info("MapperServiceImpl::mapUserRoleDTOFromUserRole() method call...");
        UserRoleDTO userRoleDTO= new UserRoleDTO();
        userRoleDTO.setRoleId(userRole.getRoleId());
        userRoleDTO.setRoleName(userRole.getRoleName());
        userRoleDTO.setRoleStatus(userRole.getRoleStatus());
        userRoleDTO.setRoleCreationDate(userRole.getRoleCreationDate());
        return userRoleDTO;
    }
}
