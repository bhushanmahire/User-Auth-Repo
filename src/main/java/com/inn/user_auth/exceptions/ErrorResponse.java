package com.inn.user_auth.exceptions;

public class ErrorResponse {
  private final String message;
  private final int statusCode;

  public ErrorResponse(String message, int statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
