package section07.problem;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Problem4 {

  public static void main(String[] args) {

    int year = 2024;
    int month = 1;

    // month의 첫날 요일
    LocalDate ld = LocalDate.of(year, month, 1);
    System.out.println(ld.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek());

    // 마지막 요일
    System.out.println(ld.with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek());
  }

}
