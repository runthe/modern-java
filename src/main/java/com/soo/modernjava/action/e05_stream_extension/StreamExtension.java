package com.soo.modernjava.action.e05_stream_extension;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExtension {
  public static void main(String[] args) {
    List<Transaction> list = new ArrayList<>();

    Trader trader = new Trader("soo", "seoul");
    Transaction transaction1 = new Transaction(trader, 2017, 20000);
    Transaction transaction2 = new Transaction(trader, 2010, 30000);
    Transaction transaction3 = new Transaction(trader, 2014, 40000);
    Transaction transaction4 = new Transaction(trader, 2012, 50000);

    list.add(transaction1);
    list.add(transaction2);
    list.add(transaction3);
    list.add(transaction4);

    //1.sorted
    list.stream()
      .filter(t -> t.getYear() == 2011)
      .sorted(comparing(Transaction::getValue))
      .collect(toList());

    //2.distinct
    list.stream()
      .map(transaction -> transaction.getTrader().getCity())
      .distinct()
      .collect(toList());

    //3.거래자의 이름을 알파벳순으로 정렬
    list.stream()
      .map(transaction -> transaction.getTrader().getName())
      .distinct()
      .sorted()
      .reduce("", (a, b) -> a + b);

    //4.joining 조이닝 StringBuilder사용함
    list.stream()
      .map(transaction -> transaction.getTrader().getName())
      .distinct()
      .sorted()
      .collect(joining());

    IntStream intStream = list.stream()
      //IntStream 반환
      .mapToInt(Transaction::getValue);

    Stream<Integer> boxedIntStream = intStream.boxed();
    System.out.println(IntStream.rangeClosed(1, 100).sum());

    Collectors.toList();

    Stream.iterate(0, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);

    Stream.generate(Math::random)
      .limit(10)
      .forEach(System.out::println);

    IntSupplier intSupplier = new IntSupplier() {
      private int previous = 0;
      private int current = 1;

      @Override
      public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };

    IntStream
      .generate(intSupplier)
      .limit(10)
      .forEach(System.out::println);
}

  public Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n)
      .boxed()
      .collect(partitioningBy(candidate -> isPrime(candidate)));
  }

  public boolean isPrime(int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    return IntStream.rangeClosed(2, candidateRoot)
      .noneMatch(i -> candidate % i == 0);
  }
}

class Trader {

  private String name;

  private String city;

  public Trader(String name, String city) {
    this.name = name;
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}

class Transaction {
  private final Trader trader;
  private final int year;
  private final int value;

  public Transaction(Trader trader, int year, int value) {
    this.trader = trader;
    this.year = year;
    this.value = value;
  }

  public Trader getTrader() {
    return trader;
  }

  public int getYear() {
    return year;
  }

  public int getValue() {
    return value;
  }
}