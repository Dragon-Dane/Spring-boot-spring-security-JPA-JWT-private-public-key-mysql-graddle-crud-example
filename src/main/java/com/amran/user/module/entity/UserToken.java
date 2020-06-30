package com.amran.user.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author : Amran Hosssain on 6/17/2020
 * This Entity Used for managed user token logout process
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_user_token",uniqueConstraints ={@UniqueConstraint(columnNames = {"user_name","token"})})
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Column(name = "token",nullable = false,length = 1000)
    private String token;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
