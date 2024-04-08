package com.companyhub.client.exception;

public class GithubApiResourceNotFoundException extends RuntimeException {

  public GithubApiResourceNotFoundException(String message) {
    super("Resource not found: " + message);
  }
}
