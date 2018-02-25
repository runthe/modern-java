package com.soo.modernjava.action.e09_default;

public class Main {
  public static void main(String[] args) {
    C c = new C();
    c.hello();
  }
}

interface A {
  default void hello() {
    System.out.println("A Hello");
  }
}

interface E {
  default void hello() {
    System.out.println("E Hello");
  }
}

interface B extends A {
  default void hello() {
    System.out.println("B hello");
  }
}

class C extends D implements A, B {
  @Override
  public void hello() {
    System.out.println("abstract hello");
  }
}

abstract class D implements A {
  public abstract void hello();
}

class F implements A, E {

  public void hello() {
    A.super.hello();
  }
}

interface G {
  default void hello() {
    System.out.println("G hello");
  }
}

interface H extends G {

}

interface I extends G {

}

class J implements H, I {

}
