package com.amran.user.module.service;

/**
 * @Author : Amran Hosssain on 6/17/2020
 */
public interface UserTokenService {

    void saveOrUpdateTokenIntoDatabase(String userName, String token);

    boolean isExistTokenByUserName(String userName);

    boolean userTokenRemove(String userName);
}
