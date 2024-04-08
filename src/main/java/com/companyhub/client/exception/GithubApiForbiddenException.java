package com.companyhub.client.exception;

public class GithubApiForbiddenException extends RuntimeException {

  public GithubApiForbiddenException(String message) {
    super("Access is forbidden: " + message);
  }
}
