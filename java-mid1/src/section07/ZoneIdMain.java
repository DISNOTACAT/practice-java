package section07;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneIdMain {

  public static void main(String[] args) {
    for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
      ZoneId zoneId = ZoneId.of(availableZoneId);
//      System.out.println(zoneId + " | " + zoneId.getRules()) ; // Asia/Aden | ZoneRules[currentStandardOffset=+03:00]
    }

    ZoneId zoneId = ZoneId.systemDefault();
//    System.out.println("ZoneId.systemDefault = "
//        + zoneId); // ZoneId.systemDefault = Asia/Tokyo

    ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
//    System.out.println("seoulZone = " + seoulZoneId);

    LocalDateTime ldt = LocalDateTime.of(2030,1,1,13,30,00);
    ZonedDateTime zdt1 = ZonedDateTime.of(ldt, ZoneId.of("Asia/Seoul"));
    System.out.println(zdt1); // 2030-01-01T13:30+09:00[Asia/Seoul]

    ZonedDateTime zdt2 = zdt1.withZoneSameInstant(ZoneId.of("UTC"));
    System.out.println(zdt2); // 2030-01-01T04:30Z[UTC]
  }
}
