package com.amran.user.module.controller;

import com.amran.user.module.common.AuthResponse;
import com.amran.user.module.dto.UserDTO;
import com.amran.user.module.util.JwtTokenUtil;
import com.amran.user.module.common.CommonResponse;
import com.amran.user.module.dto.LoginRequestDTO;
import com.amran.user.module.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> singUpUser(@RequestBody @NotNull UserDTO userDTO) {
        LOGGER.info("UserController::singUpUser() method call...");
        String result = userService.signupUser(userDTO);
        if (!result.equalsIgnoreCase(HttpStatus.OK.name())) {
            return new ResponseEntity<>(new CommonResponse(result, false, null), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(new CommonResponse("Signup Successful Completed.", true, null), HttpStatus.CREATED);
    }

    /*
     *User Can entry using this controller with method login and provide security credentials
     */
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @NotNull LoginRequestDTO dto) {
        LOGGER.info("UserController::singUpUser() method call...");
        if (null == dto.getUserName() || dto.getUserName().isEmpty()) {
            return new ResponseEntity<>(new AuthResponse("Request is not valid", false, dto.getUserName(), null), HttpStatus.BAD_REQUEST);
        }
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(new AuthResponse("Request Execute Successful.", true, dto.getUserName(), token), HttpStatus.CREATED);
    }
}
