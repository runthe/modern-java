package com.soo.modernjava.e02_function;

import java.util.function.Function;

public class FunctionExamples {

  public static void main(String[] args) {
    functionExamples();
  }

  private static void functionExamples() {
    final Function<String, Integer> toInt = Integer::valueOf;

    final Integer number = toInt.apply("100");

    final Function<Integer, Integer> identity = Function.identity();
    final Function<Integer, Integer> identity2 = t -> t;

    System.out.println(identity.apply(999));
    System.out.println(identity2.apply(999));

    System.out.println(number);
  }
}
