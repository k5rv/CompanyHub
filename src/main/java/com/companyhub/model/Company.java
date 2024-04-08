package com.companyhub.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
  private String name;
  private Integer employeesTotal;
  private Integer customersTotal;
  private String country;
}
