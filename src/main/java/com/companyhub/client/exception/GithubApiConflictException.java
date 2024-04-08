package com.companyhub.client.exception;

public class GithubApiConflictException extends RuntimeException {

  public GithubApiConflictException(String message) {
    super("Conflict: " + message);
  }
}
