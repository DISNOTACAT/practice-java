package section07;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingMain1 {

  public static void main(String[] args) {

    // 포맷팅 : 날짜를 문자로
    LocalDate date = LocalDate.of(2024, 12, 13);
    System.out.println("date = " + date);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    String fomattedDate = date.format(formatter);
    System.out.println("fomattedDate = " + fomattedDate);

    // 파싱 : 문자를 날짜로
    String input = "2040년 01월 01일";
    System.out.println("input = " + input);
    LocalDate parseDate = LocalDate.parse(input, formatter);
    System.out.println("parseDate = " + parseDate);
  }

}
