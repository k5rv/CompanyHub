package com.companyhub.client.exception;

public class GithubApiUnauthorizedException extends RuntimeException {

  public GithubApiUnauthorizedException(String message) {
    super("Bad or expired token: " + message);
  }
}
