package com.soo.modernjava.action.e18_parallel_fork;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class ForkMain {
  public static void main(String[] args) {
  }
}

class StreamForker<T> {
  private final Stream<T> stream;
  private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

  public StreamForker(Stream<T> stream) {
    this.stream = stream;
  }
}
