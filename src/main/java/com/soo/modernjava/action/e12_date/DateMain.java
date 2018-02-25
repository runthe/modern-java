package com.soo.modernjava.action.e12_date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.TimeZone;

public class DateMain {
  public static void main(String[] args) {
    System.out.println(LocalDate.now());

    LocalDate date = LocalDate.of(2016, 3, 19);
    System.out.println(date);

    LocalTime time = LocalTime.of(3, 50, 30);
    LocalTime time2 = LocalTime.of(5, 50, 30);
    System.out.println(time);

    //문자열도 가능함
    LocalDate parsedDate1 = LocalDate.parse("2018-09-12");

    System.out.println(parsedDate1);

    LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
    LocalDateTime dt2 = LocalDateTime.of(date, time);
    LocalDateTime dt3 = date.atTime(13, 45, 20);
    LocalDateTime dt4 = date.atTime(time);
    LocalDateTime dt5 = time.atDate(date);

    System.out.println(dt1.toLocalDate());
    System.out.println(dt1.toLocalTime());

    //Duration Period

    Duration d1 = Duration.between(time, time2);
    Duration d2 = Duration.between(dt1, dt2);

    System.out.format("Duration :: %d %d", d1.toMinutes(), d2.toMinutes());
    System.out.println();

    Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 19));
    System.out.format("Period :: %d", tenDays.getDays());
    System.out.println();

    Duration threeMinutes1 = Duration.ofMinutes(3);
    Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

    Period tenDays2 = Period.ofDays(10);
    Period threeWeeks = Period.ofWeeks(3);
    Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

    //날짜 조정, 파싱, 포매터
    /*
    2014-03-18
    2011-03-18
    2011-03-25
    2011-09-25
     */
    LocalDate immutableDate1 = LocalDate.of(2014, 3, 18);
    LocalDate immutableDate2 = immutableDate1.withYear(2011);
    LocalDate immutableDate3 = immutableDate2.withDayOfMonth(25);
    LocalDate immutableDate4 = immutableDate3.with(ChronoField.MONTH_OF_YEAR, 9);

    System.out.println(immutableDate1);
    System.out.println(immutableDate2);
    System.out.println(immutableDate3);
    System.out.println(immutableDate4);

    LocalDate date1 = LocalDate.of(2014, 3, 18);
    LocalDate plusWeekdate = date1.plusWeeks(1);

    System.out.println(date1);
    System.out.println(plusWeekdate);

    //Util 메소드 TemporalAdjusters
    LocalDate date3 = LocalDate.of(2014, 3, 18).with(TemporalAdjusters.lastDayOfMonth());
    System.out.println(date3);

    //날짜와 시간 객체 출력과 파싱
    String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
    String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

    System.out.println("=== DateTimeFormatter ===");
    System.out.println(s1);
    System.out.println(s2);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = date.format(formatter);
    LocalDate customPatternDate = LocalDate.parse(formattedDate, formatter);

    DateTimeFormatter koreanFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
    String koreanFormattedDate = date.format(koreanFormatter);
    System.out.println(koreanFormattedDate);

    DateTimeFormatter koreanBuilderFormatter = new DateTimeFormatterBuilder()
      .appendText(ChronoField.YEAR)
      .appendLiteral("년")
      .appendText(ChronoField.MONTH_OF_YEAR)
      .appendText(ChronoField.DAY_OF_MONTH)
      .appendLiteral("일")
      .parseCaseInsensitive()
      .toFormatter(Locale.KOREAN);


    System.out.println(date.format(koreanBuilderFormatter));

    System.out.println("다양한 시간대와 캘린더 활용방법");
    ZoneId roomZone = ZoneId.of("Europe/Rome");

    LocalDate nowDate = LocalDate.now();
    LocalDateTime nowDateTime = LocalDateTime.now();
    //TimeZone 객체를 ZoneId 객체로 변환가능
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    ZonedDateTime zdt1 = nowDate.atStartOfDay(roomZone);
    ZonedDateTime zdt2 = nowDateTime.atZone(roomZone);
    Instant instant = Instant.now();
    ZonedDateTime zdt3 = instant.atZone(roomZone);

    System.out.println(instant);
    System.out.println(zdt1);
    System.out.println(zdt2);
    System.out.println(zdt3);
  }
}
