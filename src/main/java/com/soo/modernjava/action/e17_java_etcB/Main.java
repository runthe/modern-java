package com.soo.modernjava.action.e17_java_etcB;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  private static Map<String, String> cache;

  public static void main(String[] args) {
    Map<String, Integer> carInventory = new HashMap<>();
    Integer count = 0;

    if (carInventory.containsKey("Aston Martin")) {
      count = carInventory.get("Aston Martin");
    }

    //짜짠
    count = carInventory.getOrDefault("Aston Martin", 0);

    //삭제 기능 추가 ArrayList는 삭제가 안됨
    List<Integer> list = Arrays.asList(1, 2, 3);
    list.replaceAll(x -> x * 2);
    list.forEach(System.out::println);

    //join
    String join = String.join(",", "Soo", "Kim", "Hwan");
    System.out.println(join);
  }

  static String getData(String url) {
    String data = cache.get(url);

    if (data == null) {
      data = getData(url);
      cache.put(url, data);
    }

    return data;
  }

  static String getComputedData(String url) {
    //return cache.computeIfAbsent(url, getData());
    return "";
  }
}
