package com.amran.user.module.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/16/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements Serializable {

    private String message;
    private boolean validRequest;
    private String userName;
    private String token;
}
