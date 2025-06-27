package section07;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class GetTimeMain {

  public static void main(String[] args) {
    LocalDateTime dt = LocalDateTime.of(2030, 1, 1, 13, 30, 59);
    System.out.println("dt.get(ChronoField.YEAR) = " + dt.get(ChronoField.YEAR));
    System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.get(ChronoField.MONTH_OF_YEAR));
    System.out.println("dt.get(ChronoField.HOUR_OF_DAY) = " + dt.get(ChronoField.HOUR_OF_DAY));

    System.out.println("dt.getYear() = " + dt.getYear());
    System.out.println("dt.getMonthValue() = " + dt.getMonthValue());
    System.out.println("dt.getDayOfMonth() = " + dt.getDayOfMonth());

    System.out.println("-----------편의 메서드에 없음--------------");
    System.out.println("dt.get(ChronoField.MINUTE_OF_DAY) = " + dt.get(ChronoField.MINUTE_OF_DAY));
    System.out.println("dt.get(ChronoField.SECOND_OF_DAY) = " + dt.get(ChronoField.SECOND_OF_DAY));
  
  }
}
