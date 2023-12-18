package com.inn.user_auth.service;

import com.inn.user_auth.model.User;

import java.util.List;

public interface IUserService {

  String registerUser(User user);

  String loginUser(String userName, String password);

  User getUserDetails(String userName);

  List<User> getAllUserDetails();

}
