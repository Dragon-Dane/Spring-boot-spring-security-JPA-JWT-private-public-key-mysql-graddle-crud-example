package com.amran.user.module.service;

import com.amran.user.module.dto.UserDTO;

import java.util.List;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
public interface UserService {

    String signupUser(UserDTO userDTO);

    String saveOrUpdate(UserDTO userDTO);

    String deleteUserByUserId(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUserName(String userName);

    UserDTO getUserByUserEmail(String email);

    UserDTO getUserByUserId(Long userId);

}
