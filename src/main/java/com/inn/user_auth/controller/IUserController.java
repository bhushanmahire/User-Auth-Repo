package com.inn.user_auth.controller;

import com.inn.user_auth.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserController {

  @PostMapping("/signup")
  public String registerUser(@RequestBody User user);

  @PostMapping("/login")
  public String loginUser(@RequestParam String username, @RequestParam String password);

  @GetMapping("/{userName}")
  public User getUserDetails(@PathVariable String userName);

  @GetMapping("/getAllUsers")
  public List<User> getAllUserDetails();
}
