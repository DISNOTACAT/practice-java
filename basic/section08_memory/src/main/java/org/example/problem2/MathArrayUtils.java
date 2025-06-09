package org.example.problem2;

import java.util.Arrays;

public class MathArrayUtils {

  private MathArrayUtils() {} // 인스턴스 생성 방지

  public static int sum(int[] array) {
    return Arrays.stream(array).sum();
  }

  public static float average(int[] array) {
    float sum = Arrays.stream(array).sum();
    return sum / array.length;
  }

  public static int min(int[] array) {
    return Arrays.stream(array).sorted().toArray()[0];
  }

  public static int max (int[] array) {
    return Arrays.stream(array).sorted().toArray()[array.length-1];
  }

}
