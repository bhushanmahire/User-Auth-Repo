package com.inn.user_auth.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.Random;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_name", unique = true, nullable = false)
  private String userName;

  @Column(name= "pass_word",nullable = false)
  private String passWord;

  @Column(name = "first_name",nullable = false)
  private String firstName;

  @Column(name = "last_name",nullable = false)
  private String lastName;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  @Column(name = "gender")
  private String gender;

  @Column(name= "email", unique = true, nullable = false)
  private String email;

  @Column(name = "role", nullable = false)
  private String role = "user";

  @Column(name =  "created_at", insertable = true, updatable = false)
  private Date createdAt;

  @Column(name = "last_login", insertable = true, updatable = true)
  private Date lastLogin;

  @Column(name = "is_verified")
  private boolean isVerified = false;

  public String generateUsername(String firstName, String lastName) {
    Random random = new Random();
    int suffix = random.nextInt(10000);
    return firstName.toLowerCase() + "_" + lastName.toLowerCase() + "_" + suffix;
  }

  public boolean isValidPassword(String password) {
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    return password.matches(regex);
  }

  public boolean isValidEmail(String email) {
    String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
    return email.matches(regex);
  }

  public boolean isValidMobileNumber(String mobileNumber) {
    String regex = "^[6-9]\\d{9}$";
    return mobileNumber.matches(regex);
  }

}
