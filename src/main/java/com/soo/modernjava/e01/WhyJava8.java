package com.soo.modernjava.e01;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.List;

/**
 * Created by soo on 2016. 12. 4..
 */
public class WhyJava8 {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    final StringBuilder stringBuilder = new StringBuilder();
    for (Integer number : numbers) {
      stringBuilder.append(number).append(" : ");
    }

    System.out.println(stringBuilder.toString());

/*
    final String result = numbers.stream()
      .map(String::valueOf)
      .collect(joining(":"));
*/
    final String result = numbers.stream()
      .map(String::valueOf)
      .collect(joining(":"));

    System.out.println(result);
  }
}
