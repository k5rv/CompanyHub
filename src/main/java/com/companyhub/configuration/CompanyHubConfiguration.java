package com.companyhub.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CompanyHubConfiguration {

  @Value("${companyhub.path}")
  private String filePath;

  @Value("${companyhub.source}")
  private String sourceBranch;

  @Value("${companyhub.target}")
  private String targetBranch;
}
