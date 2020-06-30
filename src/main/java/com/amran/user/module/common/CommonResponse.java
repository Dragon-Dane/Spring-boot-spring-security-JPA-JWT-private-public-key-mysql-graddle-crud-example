package com.amran.user.module.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/16/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse implements Serializable {

    private String message;
    private boolean requestValid;
    private Object data;
}
