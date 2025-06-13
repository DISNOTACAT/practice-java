package org.problem2;

public class DefaultPay implements PaymentType{

  @Override
  public boolean pay(int amount) {
    System.out.println("결제수단이 없습니다!");
    return true;
  }
}
