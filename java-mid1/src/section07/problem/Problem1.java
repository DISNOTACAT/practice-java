package section07.problem;

import java.time.LocalDateTime;

public class Problem1 {

  public static void main(String[] args) {

    /*
    2024년 1월 1일 0시 0분 0초에 1년 2개월 3일 4시간 후의 시각
     */
    LocalDateTime dt = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
    LocalDateTime plusDt = dt.plusYears(1).plusMonths(2).plusDays(3)
        .plusHours(4);

    System.out.println("plusDt = " + plusDt); // 2025-03-04T04:00
  }

}
