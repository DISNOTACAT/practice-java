package section07.problem;

import java.time.LocalDate;

public class Problem2 {

  public static void main(String[] args) {

    /*
    2024년 1월 1일 부터 2주 간격으로 5번 반복하여 날짜를 출력하는 코드를 작성하세요.
     */
    LocalDate lt = LocalDate.of(2024, 1, 1);
    for(int i = 1; i <= 5; i++){
      System.out.println("날짜 " + i + " : " + lt);
      lt = lt.plusWeeks(2);
    }


  }

}
