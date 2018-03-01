package com.soo.modernjava.action.e19_bytecode;

import java.util.function.Function;

public class ByteMain {
  public static void main(String[] args) {
    Function<Object, String> f = o -> o.toString();
  }
}
