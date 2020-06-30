package com.amran.user.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO implements Serializable {

    private Long roleId;
    private String roleName;
    private String roleStatus;
    private Date roleCreationDate;
}
