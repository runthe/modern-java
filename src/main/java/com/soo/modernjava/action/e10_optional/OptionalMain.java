package com.soo.modernjava.action.e10_optional;

import java.util.Optional;
import java.util.Properties;

public class OptionalMain {
  public static void main(String[] args) {
    //빈 Optional
    Optional<String> optionalString = Optional.empty();

    //null이 아닌 값으로 Optional 만들기
    //바로 접근하려고하면 nullPointer가 떨어진다.
    Optional<String> optionalString1 = Optional.of("What");

    //null값으로 Optional만들기
    Optional<String> optionalString2 = Optional.ofNullable("what");

    Optional<Person> personOptional = Optional.empty();
    //personOptional.map(Person::getCar).map(Car::getName);

    Properties properties = new Properties();
    properties.setProperty("a", "5");
    properties.setProperty("b", "true");
    properties.setProperty("c", "-3");
  }

  public static Optional<Integer> stringToInt(String s) {
    try {
      return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }
  /*
  public String getCarInsuranceName(Optional<Person> person, int minAge) {
    return person.filter(p -> p.getAge() >= minAge)
      .flatMap(Person::getCar)
      .flatMap(Car::getInsurace)
      .map(Insurance::getName)
      .orElse("Unknown");
  }*/
}

class Person {
  private Car car;

  public Optional<Car> getCarAsOptional() {
    return Optional.ofNullable(car);
  }
}

class Car {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
