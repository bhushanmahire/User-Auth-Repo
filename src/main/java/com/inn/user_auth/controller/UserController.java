package com.inn.user_auth.controller;

import com.inn.user_auth.model.User;
import com.inn.user_auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController implements IUserController{

  @Autowired
  IUserService userService;

  @Override
  public String registerUser(User user) {
    log.info("Inside @class UserController @method signUpUser");
    return userService.registerUser(user);
  }

  @Override
  public String loginUser(String userName, String passWord) {
    log.info("Inside @class UserController @method loginUser");
    return userService.loginUser(userName, passWord);
  }

  @Override
  public User getUserDetails(String userName) {
    log.info("Inside @class UserController @method getUserDetails");
    return userService.getUserDetails(userName);
  }

  @Override
  public List<User> getAllUserDetails() {
    return userService.getAllUserDetails();
  }
}
