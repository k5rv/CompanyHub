package com.companyhub.client.exception;

public class GithubApiValidationException extends RuntimeException {



  public GithubApiValidationException(String message) {
    super("Validation failed, or the endpoint has been spammed: " + message);
  }
}
