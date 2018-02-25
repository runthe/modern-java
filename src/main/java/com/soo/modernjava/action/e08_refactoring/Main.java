package com.soo.modernjava.action.e08_refactoring;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    //람다 사용
    Validator validator1 = new Validator(o -> o.matches("[a-z]+"));
    Validator validator2 = new Validator(o -> o.matches("\\d+"));

    Feed f = new Feed();
    f.registerObjserver(new NYTimes());
    f.registerObjserver(new Guardian());
    f.notifyObservers("The queen said her favorite book is Java8 in Action");

    f.registerObjserver(tweet -> {
      if (tweet != null && tweet.contains("money")) {
        System.out.println("Breaking news in NY! " + tweet);
      }
    });

    f.registerObjserver(tweet -> {
      if (tweet != null && tweet.contains("queen")) {
        System.out.println("Yet another news in London! " + tweet);
      }
    });


    //의무체인 커링
    UnaryOperator<String> headerProcessing = (text) -> "From Raoul, Mario And Alan:" + text;
    UnaryOperator<String> spellCheckerProcessing = (text) -> text.replaceAll("labda", "lambda");
    Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

    String result = pipeline.apply("Aren't labdas really sexy?");


    Stream.of(1, 2, 3).peek(x -> System.out.println(x)).map(String::valueOf).peek(x -> System.out.println(x));
  }
}

//기존 전략패턴
interface ValidationStrategy {

  boolean execute(String s);

}

class IsAllLowerCase implements ValidationStrategy {
  @Override
  public boolean execute(String s) {
    return s.matches("[a-z]+");
  }
}

class IsNumeric implements ValidationStrategy {
  @Override
  public boolean execute(String s) {
    return s.matches("\\d+");
  }
}

class Validator {
  private final ValidationStrategy strategy;

  public Validator(ValidationStrategy strategy) {
    this.strategy = strategy;
  }

  public boolean validate(String s) {
    return strategy.execute(s);
  }
}

/*
템플릿 메소드 패턴
abstract class OnlineBanking {
  public void processCustomer(int id) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy(c);
  }

  public void processCustomerLambda(int id, Consumer<Customer> makeCustomerHappy) {
    Customer c = Database.getCustomerWithId(id);
    makeCustomerHappy.accept(c);
  }

  abstract void makeCustomerHappy(Customer c);
}

*/

interface Observer {

  void notify(String tweet);

}

class NYTimes implements Observer {

  @Override
  public void notify(String tweet) {
    if (tweet != null && tweet.contains("money")) {
      System.out.println("Breaking news in NY! " + tweet);
    }
  }
}

class Guardian implements Observer {

  @Override
  public void notify(String tweet) {
    if (tweet != null && tweet.contains("queen")) {
      System.out.println("Yet another news in London! " + tweet);
    }
  }
}

interface Subject {

  void registerObjserver(Observer observer);

  void notifyObservers(String tweet);

}

class Feed implements Subject {

  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void registerObjserver(Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void notifyObservers(String tweet) {
    observers.forEach(o -> o.notify(tweet));
  }
}

//팩토리

/*
class ProductFactory {

  Supplier<Product> loanSupplier = Loan::new;
  Loan loan = loanSupplier.get();

  final static Map<String, Supplier<Product>> map = new HashMap<>();

  static {
    map.put("loan", Loan::new);
    map.put("stock", Stock::new);
    map.put("bond", Bond::new);
  }

  public static Product createProductWithLambda(String name) {
    Supplier<Product> p = map.get(name);
    if (p != null) return p.get();
    throw new IllegalArgumentException("No such product" + name);
  }

  public static Product createFactory(String name) {
    switch (name) {
      case "loan":
        return new Loan();

      case "stock":
        return new Stock();

      default:
        throw new RuntimeException("what");
    }
  }
}
*/
