package com.soo.modernjava.e03_consumer;

import java.util.function.Consumer;

public class ConsumerExamples {

  public static void main(String[] args) {
    consumerExamples();
  }

  private static void consumerExamples() {
    Consumer<String> prints = value -> System.out.println(value);
    prints.accept("Soo");
  }
}
