package section10.ex4;


import java.util.Scanner;
import section10.ex4.exception.SendExceptionV4;

public class MainV4 {

  public static void main(String[] args) {
//    NetworkServiceV4 service = new NetworkServiceV4();
    NetworkServiceV5 service = new NetworkServiceV5();

    Scanner scanner = new Scanner(System.in);
    while(true) {
      System.out.print("전송할 문자: ");
      String input = scanner.nextLine();
      if (input.equals("exit")) {
        break;
      }

      try{
        service.sendMessage(input);
      } catch (Exception e) {
        exceptionHandler(e);
      }

      System.out.println();
    }
    System.out.println("프로그램을 정상 종료합니다.");
  }

  // 공통 예외 처리 로직
  private static void exceptionHandler(Exception e) {
    System.out.println("사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.");
    System.out.println("===log: 개발자용 디버깅 메시지===");
    e.printStackTrace(System.out);
//    e.printStackTrace();

    // 필요시 예외 별로 별도의 로직 추가
    if (e instanceof SendExceptionV4 sendEx) {
      System.out.println("[전송 오류] 전송 데이터: " + sendEx.getSendData());
    }
  }

}
