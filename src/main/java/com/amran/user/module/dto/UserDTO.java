package com.amran.user.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long userId;
    @NotNull(message = "User Name cannot be null")
    private String userName;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "Gender cannot be null")
    private String gender;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "Role information is required")
    private UserRoleDTO userRoleDTO;
}
