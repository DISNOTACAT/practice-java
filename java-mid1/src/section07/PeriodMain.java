package section07;
import java.time.*;
public class PeriodMain {

  public static void main(String[] args) {

    Period period = Period.ofDays(10);
    System.out.println("period = " + period); // period = P10D

    // 계산에 ㅅ용
    LocalDate curDate = LocalDate.of(2030, 1,1);
    LocalDate plusDate = curDate.plus(period);
    System.out.println("curDate = " + curDate); // curDate = 2030-01-01
    System.out.println("plusDate = " + plusDate); // plusDate = 2030-01-11
    
    // 기간 차이
    LocalDate startDate = LocalDate.of(2030, 1,1);
    LocalDate endDate = LocalDate.of(2030, 4,2);
    Period between = Period.between(startDate, endDate);
    System.out.println("between = " + between); // between = P3M1D
    System.out.println("기간: " + between.getMonths() + "개월 " + between.getDays() + "일"); // 기간: 3개월 1일
  }

}
