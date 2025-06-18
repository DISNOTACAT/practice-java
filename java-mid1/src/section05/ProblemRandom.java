package section05;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProblemRandom {

  public static void main(String[] args) {
    /*
    로또 번호 자동 생성기
    - 번호는 1~45 사이의 숫자 중 6개
    - 중복 없음
    - 실행마다 다른 결과
     */

    Random random = new Random();
    Set<Integer> lotto = new HashSet<>();

    while(lotto.size() < 6) {
      int num = random.nextInt(1, 45);
      lotto.add(num);
    }

    List<Integer> list = lotto.stream().toList();
    for(Integer i : list) {
      System.out.print(i + " ");
    }
  }
}
