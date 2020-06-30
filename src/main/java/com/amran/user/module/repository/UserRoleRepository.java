package com.amran.user.module.repository;

import com.amran.user.module.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
