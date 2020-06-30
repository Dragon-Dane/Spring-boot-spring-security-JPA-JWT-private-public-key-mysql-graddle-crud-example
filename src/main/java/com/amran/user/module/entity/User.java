package com.amran.user.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/15/2020
 * User Signup, Create, Update for that purpose i have create user entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_user",uniqueConstraints ={@UniqueConstraint(columnNames = {"user_name"})})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Column(name = "password",nullable = false, length = 1000)
    private String password;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "gender",nullable = false)
    private String gender;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "phone",nullable = false)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    private UserRole userRole;

}
