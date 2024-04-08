package com.companyhub.utils;

import com.companyhub.model.Company;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;

public class CompanyHelper {

  public static Company createRandomCompany() {
    String name = createRandomName(30, 30);
    Integer employeesTotal = createRandomNumber(3, 3);
    Integer customersTotal = createRandomNumber(4, 4);
    String country = createRandomName(10, 10);
    return Company.builder()
        .name(name)
        .employeesTotal(employeesTotal)
        .customersTotal(customersTotal)
        .country(country)
        .build();
  }

  public static List<Company> createRandomCompanies(int number) {
    if (number <= 0) return List.of();
    return IntStream.range(0, number).mapToObj(i -> createRandomCompany()).toList();
  }

  private static String createRandomName(int minLength, int maxLength) {
    return RandomStringUtils.randomAlphabetic(minLength, maxLength);
  }

  private static Integer createRandomNumber(int minLength, int maxLength) {
    String randomNumeric = RandomStringUtils.randomNumeric(minLength, maxLength);
    return Integer.valueOf(randomNumeric);
  }
}
