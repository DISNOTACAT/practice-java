package section07.problem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Problem5 {

  // 서울의 회의 시간은 2024년 1월 1일 오전 9시다. 이때 런던, 뉴욕의 회의 시간을 구해라.

  // Europe/London
  // America/New_York
  public static void main(String[] args) {

    LocalDateTime ldt = LocalDateTime.of(2024, 1, 1, 9, 0, 0);
    ZonedDateTime seoul = ldt.atZone(ZoneId.of("Asia/Seoul"));
    System.out.println("서울의 회의 시간 = " + seoul);
    System.out.println("런던의 회의 시간 = " + seoul.withZoneSameInstant(ZoneId.of("Europe/London")));
    System.out.println("뉴욕의 회의 시간 = " + seoul.withZoneSameInstant(ZoneId.of("America/New_York")));

//    Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//    for (String s : availableZoneIds) {
//      System.out.println(s);
//    }



  }

}
