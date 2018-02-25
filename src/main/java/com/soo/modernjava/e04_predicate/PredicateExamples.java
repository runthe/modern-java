package com.soo.modernjava.e04_predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PredicateExamples {

  public static void main(String[] args) {
    predicateExamples();
  }

  private static void predicateExamples() {
    final Predicate<Integer> isPositive = i -> i > 0;

    System.out.println(isPositive.test(5));
    System.out.println(isPositive.test(0));
    System.out.println(isPositive.test(-1));

    System.out.println(IntStream.rangeClosed(1, 100).sum());

    String a = "Hi";
    String b = "Hi";

    System.out.println("잘못됬따");
    System.out.println(a == b);


    final List<Integer> numbers = Arrays.asList(1, 5, 13, 3, -5, -4, 0);
    final List<Integer> positiveNumbers = new ArrayList<>();

    for (final Integer num : numbers) {
      if (isPositive.test(num)) {
        positiveNumbers.add(num);
      }
    }

    System.out.println("positive integers: " + positiveNumbers);

    final Predicate<Integer> lessThan3 = i -> i < 3;
    final List<Integer> numbersLessThan3 = new ArrayList<>();

    for (final Integer num : numbers) {
      if (lessThan3.test(num)) {
        numbersLessThan3.add(num);
      }
    }

    System.out.println("less than 3: " + numbersLessThan3);

    System.out.println("positive integers: " + filter(numbers, isPositive));
    System.out.println("less than 3: " + filter(numbers, lessThan3));
  }

  static <T> List<T> filter(List<T> list, Predicate<T> filter) {
    final List<T> result = new ArrayList<>();

    for (final T input : list) {
      if (filter.test(input)) {
        result.add(input);
      }
    }

    return result;
  }
}
