package com.amran.user.module.repository;

import com.amran.user.module.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : Amran Hosssain on 6/17/2020
 */
public interface UserTokenRepository extends JpaRepository<UserToken,Long> {

    UserToken findByUserName(String userName);
}
