package org.problem2;

public class PayMain0 {
  //새로 추가한 결제 수단 선택
  public static void main(String[] args) {
    PayService payService = new PayService();
    //kakao 결제
    String payOption1 = "kakao";
    int amount1 = 5000;
    payService.processPay(payOption1, amount1);
    //naver 결제
    String payOption2 = "naver";
    int amount2 = 10000;
    payService.processPay(payOption2, amount2);
    //잘못된 결제 수단 선택
    String payOption3 = "bad";
    int amount3 = 15000;
    payService.processPay(payOption3, amount3);
    // 새로 추가하기
    String payOption4 = "new";
    int amount4 = 200000;
      payService.processPay(payOption4, amount4);
  }

}
