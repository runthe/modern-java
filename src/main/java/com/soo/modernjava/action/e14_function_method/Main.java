package com.soo.modernjava.action.e14_function_method;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class Main {

  public static void main(String[] args) {
    DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
    DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
    DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

    convertCtoF.applyAsDouble(1000);
  }

  static DoubleUnaryOperator curriedConverter(double f, double b) {
    return (double x) -> x * f + b;
  }

  static <A, B, C> Function<A, C> compose(Function<B, C> a, Function<A, B> b) {
    return x -> a.apply(b.apply(x));
  }
}
