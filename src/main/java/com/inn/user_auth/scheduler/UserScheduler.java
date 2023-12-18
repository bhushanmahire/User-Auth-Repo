package com.inn.user_auth.scheduler;

import com.inn.user_auth.model.User;
import com.inn.user_auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class UserScheduler {
  @Autowired
  IUserService userService;


  @Scheduled(cron = "0 0 0 1/60 * ?")
  public void verifyUsers() {
    //check user last login
    log.info("Inside @class UserScheduler");
    List<User> users = userService.getAllUserDetails();
    int count = 0;
    for (User user : users) {
      Date lastLoginDate = user.getLastLogin();
      LocalDate lastLoginLocalDate = lastLoginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      long daysDifference = LocalDate.now().toEpochDay() - lastLoginLocalDate.toEpochDay();
      if (daysDifference > 60) {
        count++;
      }
    }
    System.out.println("Total " + count + " Users last login was more than 60 days ago.");
  }
}
