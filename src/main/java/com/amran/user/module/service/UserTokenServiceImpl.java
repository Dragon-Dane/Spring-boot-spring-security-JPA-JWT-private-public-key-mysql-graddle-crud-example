package com.amran.user.module.service;

import com.amran.user.module.entity.UserToken;
import com.amran.user.module.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author : Amran Hosssain on 6/17/2020
 */

@Service
public class UserTokenServiceImpl implements UserTokenService {

    final UserTokenRepository userTokenRepository;

    public UserTokenServiceImpl(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public void saveOrUpdateTokenIntoDatabase(String userName, String token) {
        UserToken userToken =  userTokenRepository.findByUserName(userName);
        if(userToken !=null){
            userToken.setUserName(userName);
            userToken.setToken(token);
            userToken.setDateTime(LocalDateTime.now());
            userTokenRepository.save(userToken);
        }else{
            userToken = new UserToken();
            userToken.setUserName(userName);
            userToken.setToken(token);
            userToken.setDateTime(LocalDateTime.now());
            userTokenRepository.save(userToken);
        }
    }

    @Override
    public boolean isExistTokenByUserName(String userName) {
        return userTokenRepository.findByUserName(userName) == null ? false : true;
    }

    @Override
    public boolean userTokenRemove(String userName) {
        UserToken userToken = userTokenRepository.findByUserName(userName);
        userTokenRepository.delete(userToken);
        return true;
    }
}
