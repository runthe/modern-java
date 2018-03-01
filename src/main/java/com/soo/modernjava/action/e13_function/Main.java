package com.soo.modernjava.action.e13_function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

public class Main {

  public static void main(String[] args) {
    List<List<Integer>> subsets = subsets(Arrays.asList(1, 4, 9));

    subsets.forEach(list -> {
      System.out.println(list.toString());
    });

    long startNano1 = System.nanoTime();
    long result = factorialStreams(500);
    System.out.println(System.nanoTime() - startNano1 / 1_000_000);

    long startNano2 = System.nanoTime();
    long result2 = factorialTailRecursive(500);
    System.out.println(System.nanoTime() - startNano2 / 1_000_000);
    System.out.println("Factorail :: " + result);
    System.out.println("Factorail :: " + result2);
  }

  private static List<List<Integer>> subsets(List<Integer> list) {
    if (list.isEmpty()) {
      List<List<Integer>> ans = new ArrayList<>();
      ans.add(Collections.emptyList());
      return ans;
    }

    Integer first = list.get(0);
    List<Integer> rest = list.subList(1, list.size());

    List<List<Integer>> subans = subsets(rest);
    List<List<Integer>> subans2 = insertAll(first, subans);
    return concat(subans, subans2);
  }

  private static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
    List<List<Integer>> copyList = new ArrayList<>(a);
    copyList.addAll(b);

    return copyList;
  }

  private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
    List<List<Integer>> result = new ArrayList<>();

    for (List<Integer> list : lists) {
      List<Integer> copyList = new ArrayList<>();
      copyList.add(first);
      copyList.addAll(list);
      result.add(copyList);
    }

    return result;
  }

  private static long factorialStreams(long n) {
    return LongStream.rangeClosed(1, n)
      .reduce(1, (a, b) -> a * b);
  }

  private static long factorialTailRecursive(long n) {
    return factorialHelper(1, n);
  }

  //일반 재귀 n == 1 ? 1 : n * factorialHelper(n - 1);
  private static long factorialHelper(long acc, long n) {
    return n == 1 ? acc : factorialHelper(acc * n, n - 1);
  }
}
