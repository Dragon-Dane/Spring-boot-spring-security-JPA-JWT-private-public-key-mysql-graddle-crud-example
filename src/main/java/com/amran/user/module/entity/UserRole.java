package com.amran.user.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(catalog = "tbl_user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name",nullable = false)
    private String roleName;
    @Column(name = "role_status",nullable = false)
    private String roleStatus;
    @Column(name = "role_creation_date")
    private Date roleCreationDate;
}
