package section06.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuthGradeMain2 {

  public static void main(String[] args) throws IOException {

    while(true) {

      try{

        System.out.print("당신의 등급을 입력하세요.[GUEST, LOGIN, ADMIN]: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        AuthGrade grade = AuthGrade.settingGrade(br.readLine());
        if(grade == null) {
          System.out.print("x" + "\n");
          continue;
        }

        System.out.println("당신의 등급은 " + grade.getDescription() + "입니다.");
        System.out.println(AuthGrade.getMenuOf(grade));

        break;

      } catch (Exception e) {
        System.out.println(e);
      }

    }

  }



}
