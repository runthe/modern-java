package com.soo.modernjava.action.e11_future;

public class FutureMain {

  public static void main(String[] args) {
    int processCount = Runtime.getRuntime().availableProcessors();

    System.out.format("Count : %d", processCount);
  }
}
