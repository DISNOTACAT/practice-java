package section05;

import java.util.Arrays;

public class ProblemWrapping {

  public static void main(String[] args) {

    // 문자 두 수의 합
    String str1 = "10";
    String str2 = "20";
    int sum = Integer.valueOf(str1) + Integer.valueOf(str2);
    System.out.println("두 수의 합 : " + sum);

    // 문자 배열의 모든 숫자 합
    String[] arr = {"1.5", "2.5", "3.0"};
    double sum2 = Arrays.stream(arr).mapToDouble(Double::parseDouble).sum();
    System.out.println("배열의 합 : " + sum2);

    // 박싱 언박싱
    String strValue = "100";

    Integer integerValue = Integer.valueOf(strValue);
    int intValue = integerValue;
    Integer newIntegerValue = intValue;

  }

}
