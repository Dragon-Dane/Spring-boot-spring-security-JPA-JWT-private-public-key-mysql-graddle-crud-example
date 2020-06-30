package com.amran.user.module.repository;

import com.amran.user.module.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    User findByUserName(String userName);

    User findByEmail(String email);
}
