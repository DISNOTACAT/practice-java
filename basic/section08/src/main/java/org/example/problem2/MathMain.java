package org.example.problem2;

import static org.example.problem2.MathArrayUtils.*;
public class MathMain {

  public static void main(String[] args) {
    int[] values = {1, 2, 3, 4, 5};
    System.out.println("sum = " + sum(values));
    System.out.println("average = " + average(values));
    System.out.println("min = " + min(values));
    System.out.println("max = " + max(values));
  }
}
