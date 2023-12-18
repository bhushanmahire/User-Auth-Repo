package com.inn.user_auth.service;

import com.inn.user_auth.exceptions.BusinessException;
import com.inn.user_auth.model.User;
import com.inn.user_auth.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService{

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public String registerUser(User user) throws BusinessException {
    log.info("Inside UserService signUpUser method");
    if (userRepository.findByUserName(user.getUserName()) != null) {
      throw new BusinessException("User already exists! Please try with a new username.");
    }
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new BusinessException("User Email already exists!");
    }
    user.setUserName(user.generateUsername(user.getFirstName(), user.getLastName()));
    if (!user.isValidPassword(user.getPassWord())) {
      throw new BusinessException("Invalid password. Please follow the password criteria.");
    }
    user.setPassWord(passwordEncoder.encode(user.getPassWord()));
    if (!user.isValidMobileNumber(user.getMobileNumber())) {
      throw new BusinessException("Invalid Mobile Number");
    }
    user.setMobileNumber(user.getMobileNumber());
    if (!user.isValidEmail(user.getEmail())) {
      throw new BusinessException("Email is not valid");
    }
    user.setEmail(user.getEmail());
    userRepository.save(user);
    return "User created successfully!";
  }

  @Override
  public String loginUser(String userName, String passWord) {
    log.info("Inside @class UserService @method loginUser");
    User user = userRepository.findByUserName(userName);
    if (user != null && passwordEncoder.matches(passWord, user.getPassWord())) {
      return "Login successful!";
    } else {
      return "Invalid username or password";
    }
  }

  @Override
  public User getUserDetails(String userName) {
    log.info("Inside @class UserService @method getUserDetails");
    return userRepository.findByUserName(userName);
  }

  @Override
  public List<User> getAllUserDetails() {
    log.info("Inside @class UserService @method getAllUserDetails");
    return userRepository.findAll();
  }
}
