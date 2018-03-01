package com.soo.modernjava.action.e11_future;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class FutureMain {

  final Executor excutor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    }
  });

  private static List<Shop> shops = Arrays.asList(
    new Shop("Product"),
    new Shop("Product1"),
    new Shop("Product2"),
    new Shop("Product3"));

  public static void main(String[] args) {
    int processCount = Runtime.getRuntime().availableProcessors();

    System.out.format("Count : %d", processCount);

    Shop shop = new Shop("BestShop");
    long startTime = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsync("my favorite name");
    long endTime = System.nanoTime();
    long invocationTime = (endTime - startTime) / 1_000_000;

    System.out.println("Invocation Time :: " + invocationTime + "msecs");

    //제품의 가격을 계산하는동안
    //다른 상점 검색 등 작업 수행
    doSomethingElse();

    try {
      double price = futurePrice.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    long retrievalTime = System.nanoTime() - startTime / 1_000_000;
    System.out.println("Price returned after :: " + retrievalTime + "msecs");

    long findPriceStartTime = System.nanoTime();
    findPrices("myPhone27S");
    long findPriceDurationTime = (System.nanoTime() - findPriceStartTime) / 1_000_000;

    System.out.println("Done in " + findPriceDurationTime + " msecs");
  }

  private static void doSomethingElse() {
    System.out.println("Do Something Else..");
  }

  public static List<String> findPrices(String product) {
    //Parallel이라고 이용할수있는거 4개 코어 4초이니까 -> 1초로
    return shops.parallelStream()
      .map(shop -> String.format("%s price is % .2f", shop.getName(), shop.getPrice(product)))
      .collect(toList());
  }
}

class Shop {


  private String name;

  public Shop(String name) {
    this.name = name;
  }

  public Future<Double> getPriceAsync(String product) {
    //CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> calculatePrice(product));

/*
    priceFutures.stream()
      .map(CompletableFuture::join)
      .collect(toList());
*/


/*
    new Thread(() -> {
      try {
        double price = calculatePrice(product);
        //가격이 정상적으로 저장되면 Future에 가격정보를 저장한 채로 Future를 종료
        futurePrice.complete(price);
      } catch (Exception ex) {
        //도중에 문제가 발생하면 발생한 에러를 포함시켜 Future를 종료한다.
        futurePrice.completeExceptionally(ex);
      }
    }).start();
*/

    return futurePrice;
  }

  public double getPrice(String product) {
    return calculatePrice(product);
  }

  //비동기로 연동해야할 외부서비스
  private double calculatePrice(String product) {
    delay();
    return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
  }

  private static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String getName() {
    return name;
  }
}
