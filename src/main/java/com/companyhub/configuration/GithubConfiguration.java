package com.companyhub.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class GithubConfiguration {

  @Value("${github.api.url}")
  private String apiUrl;

  @Value("${github.api.token}")
  private String token;

  @Value("${github.repo.owner}")
  private String owner;

  @Value("${github.repo.name}")
  private String name;
}
