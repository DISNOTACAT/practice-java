package section07;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class DurationMain {

  public static void main(String[] args) {

    Duration duration = Duration.ofMinutes(30);
    System.out.println("duration = " + duration); // duration = PT30M

    LocalTime lt = LocalTime.of(1, 0);
    System.out.println("lt = " + lt); // lt = 01:00
    
    //게산에 사용
    LocalTime plusTime = lt.plus(duration);
    System.out.println("plusTime = " + plusTime);  // plusTime = 01:30
    
    // 시간 차이
    LocalTime start = LocalTime.of(9, 0);
    LocalTime end = LocalTime.of(10, 0);
    Duration between = Duration.between(start, end);
    System.out.println("between = " + between);
    System.out.println("근무시간: " + between.toHours() + "시간 " + between.toMinutes() + "분"); // 근무시간: 1시간60분 (toMinutesPart(): 시간 뺴고 남은 분을 반환 --> 0분)
    
  }

}
