package org.problem2;

public class PayService {
  public void processPay(String option, int amount) {
    boolean result = false;
    System.out.println("결제를 시작합니다: option=" + option + ", amount=" +
        amount);

    PaymentType paymentType = PayStore.findPaymentType(option);
    result = paymentType.pay(amount);

    if (result) {
      System.out.println("결제가 성공했습니다.");
    } else {
      System.out.println("결제가 실패했습니다.");
    }
  }

}
