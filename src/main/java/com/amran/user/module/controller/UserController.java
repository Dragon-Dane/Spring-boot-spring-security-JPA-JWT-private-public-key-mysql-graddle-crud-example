package com.amran.user.module.controller;

import com.amran.user.module.dto.UserDTO;
import com.amran.user.module.service.UserService;
import com.amran.user.module.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author : Amran Hosssain on 6/15/2020
 */
@RestController
@RequestMapping("/api/user")
public class UserController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> updateUser(@RequestBody @NotNull UserDTO userDTO) {
        LOGGER.info("UserController::updateUser() method call...");
        String result =  userService.saveOrUpdate(userDTO);
        if (result.equalsIgnoreCase(HttpStatus.OK.name())) {
            return new ResponseEntity<>(new CommonResponse("User update successful.",true,null), HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(new CommonResponse(result,false,null), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> deleteUser(@RequestParam @NotNull Long userId) {
        LOGGER.info("UserController::deleteUser() method call...");
        String result =  userService.deleteUserByUserId(userId);
        if (result.equalsIgnoreCase(HttpStatus.OK.name())) {
            return new ResponseEntity<>(new CommonResponse("User delete successful.",true,null), HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(new CommonResponse(result,false,null), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(path = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getAllUsers() {
        LOGGER.info("UserController::getAllUsers() method call...");
        CommonResponse commonResponse = null;
        List<UserDTO> dtoList = userService.getAllUsers();
        commonResponse = new CommonResponse("Request Execute Success.", true, dtoList);
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getUserByUserId(@PathVariable @NotNull Long userId) {
        LOGGER.info("UserController::getUserByUserId() method call...");
        CommonResponse commonResponse = null;
        UserDTO userDTO = userService.getUserByUserId(userId);
        commonResponse = new CommonResponse("Request Execute Success.", true, userDTO);
        return new ResponseEntity<>(commonResponse, HttpStatus.FOUND);
    }

    @GetMapping(path = "/get/by/username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getUserByUserName(@RequestParam @NotNull String userName) {
        LOGGER.info("UserController::getUserByUserName() method call...");
        CommonResponse commonResponse = null;
        UserDTO userDTO = userService.getUserByUserName(userName);
        commonResponse = new CommonResponse("Request Execute Success.", true, userDTO);
        return new ResponseEntity<>(commonResponse, HttpStatus.FOUND);
    }
}
