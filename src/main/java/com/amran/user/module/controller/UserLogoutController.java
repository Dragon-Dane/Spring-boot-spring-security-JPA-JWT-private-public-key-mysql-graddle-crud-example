package com.amran.user.module.controller;

import com.amran.user.module.service.UserTokenService;
import com.amran.user.module.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Principal;

/**
 * @Author : Amran Hosssain on 6/17/2020
 */
@RestController
@RequestMapping("/api/user")
public class UserLogoutController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLogoutController.class);

    @Autowired
    UserTokenService userTokenService;

    @GetMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> userLogout(Principal principal) {
        LOGGER.info("UserController::userLogout() method call...");
        if (userTokenService.userTokenRemove(principal.getName())) {
            return new ResponseEntity<>(new CommonResponse("User Logout Successful.", true, null), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new CommonResponse("User Logout Can't Successful", false, null), HttpStatus.BAD_REQUEST);
        }
    }
}
