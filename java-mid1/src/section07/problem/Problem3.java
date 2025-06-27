package section07.problem;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Problem3 {

  public static void main(String[] args) {

    /*
    디데이 구하기
     */

    LocalDate startDate = LocalDate.of(2025, 6, 27);
    LocalDate endDate = LocalDate.of(2023, 11, 15);
    System.out.println("시작 날짜 : " + startDate);
    System.out.println("목표 날짜 : " + endDate);

    Period period = Period.between(startDate, endDate);

    System.out.println("남은 기간: " + period.getYears() + "년 " +period.getMonths() + "개월 " + period.getDays() + "일");

    long dayAmount = ChronoUnit.DAYS.between(startDate, endDate);
    System.out.println("디데이 = " + dayAmount + "일 남음");

  }

}
