package com.amran.user.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Amran Hosssain on 6/16/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    private String userName;
    private String password;
}
