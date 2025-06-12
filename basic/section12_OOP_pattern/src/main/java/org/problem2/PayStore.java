package org.problem2;

public abstract class PayStore {

  public static PaymentType findPaymentType(String option) {

    if (option.equals("kakao")) {
      return new KakaoPay();
    }

    if (option.equals("naver")) {
      return new NaverPay();
    }

    if (option.equals("new")) {
      return new NewPay();
    }

    return new DefaultPay();

  }


}
