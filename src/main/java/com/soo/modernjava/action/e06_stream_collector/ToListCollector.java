package com.soo.modernjava.action.e06_stream_collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

  //빈 누적자 인스턴스를 만드는 파라미터가 없는 함수다
  //ToListCollector 처럼 누적자를 반환하는 컬렉터에서는 빈 누적자가 비어있는 스트림의 수집 과정의 결과가 될 수 있다.
  //ToListCollector 에서 supplier는 다음처럼 빈 리스트를 반환한다.
  @Override
  public Supplier<List<T>> supplier() {
    //return () -> new ArrayList<T>();
    return ArrayList::new;
  }

  //누적자에 값을 더하는 연산
  @Override
  public BiConsumer<List<T>, T> accumulator() {
    //return (list, item) -> list.add(item);
    return List::add;
  }

  //나눠진 SubStream을 합치는 과정
  @Override
  public BinaryOperator<List<T>> combiner() {
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  //최종 변환값을 결과 컨테이너로 적용하기
  //메서드는 스트림 탐색을 끝내고 누적자 객체를 최종 결과로 변환하면서 누적 과정을 끝낼때 호출할 함수를 반환해야 한다.
  //여기서는 누적자 객체가 이미 최종결과인 경우가있기 떄문에 항등함수 identity를 내보낸다
  @Override
  public Function<List<T>, List<T>> finisher() {
    return Function.identity();
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
  }
}
