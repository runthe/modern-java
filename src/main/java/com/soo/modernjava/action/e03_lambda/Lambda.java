package com.soo.modernjava.action.e03_lambda;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    Predicate<Integer> predicate = o -> o > 3;
    List<Integer> result = filter(list, predicate);

    String s = result.stream().map(String::valueOf).collect(joining("\n"));

    Supplier<Apple> c1 = Apple::new;
    Apple a1 = c1.get();

    Function<Integer, Apple> c2 = Apple::new;
    Apple a2 = c2.apply(10);

    BiFunction<Integer, Integer, Apple> c3 = Apple::new;
    Apple a3 = c3.apply(10, 10);

    ThreeFunction<Integer, Integer, Integer, Apple> c4 = Apple::new;
    Apple a4 = c4.apply(10, 10, 10);


    String[] words = new String[]{"Hello", "hWorld"};


    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(4, 5, 6);

    numbers1.stream()
      .flatMap(n1 -> numbers2.stream().map(n2 -> new int[]{n1, n2}))
      .collect(toList());


    int[] tests = new int[]{1, 2, 3, 4};
    int testsResult1 = Arrays.stream(tests).reduce(0, (a, b) -> a + b);
    int testsResult2 = Arrays.stream(tests).reduce(0, Integer::sum);
    OptionalInt testsResult3 = Arrays.stream(tests).reduce((a, b) -> a + b);
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
    List<T> result = new ArrayList<>();

    list.forEach(e -> {
      if (predicate.test(e)) {
        result.add(e);
      }
    });

    return result;
  }
}

class Menu {
  private String name;

  private int calorie;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCalorie() {
    return calorie;
  }

  public void setCalorie(int calorie) {
    this.calorie = calorie;
  }
}

class Apple {

  private int weight;

  public Apple() {
    weight = 0;
  }

  public Apple(int weight) {
    this.weight = weight;
  }

  public Apple(int weight, int width) {
    this.weight = weight;
  }

  public Apple(int weight, int width, int height) {
    this.weight = weight;
  }
}

@FunctionalInterface
interface ThreeFunction<T1, T2, T3, R> {
  R apply(T1 t1, T2 t2, T3 t3);
}

interface Adder {
  int add(int a, int b);
}

interface SmartAdder extends Adder {
  int add(double a, double b);
}
