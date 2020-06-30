package com.amran.user.module.mapper;

import com.amran.user.module.dto.UserDTO;
import com.amran.user.module.dto.UserRoleDTO;
import com.amran.user.module.entity.User;
import com.amran.user.module.entity.UserRole;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
public interface MapperService {

    User mapUserFromUserDTO(UserDTO userDTO);

    UserDTO mapUserDTOFromUser(User user);

    UserRole mapUserRoleFromUserRoleDTO(UserRoleDTO userRoleDTO);

    UserRoleDTO mapUserRoleDTOFromUserRole(UserRole userRole);
}
