package section07.problem;

import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class Problem6 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("년도를 입력하세요: ");
    int year = sc.nextInt();
    sc.nextLine();

    System.out.print("월을 입력하세요: ");
    int month = sc.nextInt();
    sc.nextLine();

    printCalender(year, month);

  }

  private static void printCalender(int year, int month) {

    LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
    LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);

    int offsetWeekDays = firstDayOfMonth.getDayOfWeek().getValue() % 7;

    System.out.println("Su Mo Tu We Th Fr Sa ");
    for(int i = 0; i < offsetWeekDays; i++) {
      System.out.print("   ");
    }

    LocalDate dayIterator = firstDayOfMonth;
    while (dayIterator.isBefore(firstDayOfNextMonth)) {
      System.out.printf("%2d ", dayIterator.getDayOfMonth());
      if(dayIterator.getDayOfWeek() == DayOfWeek.SATURDAY) {
        System.out.println();
      }
      dayIterator = dayIterator.plusDays(1);
    }

  }

}
