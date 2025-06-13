package org.example.section03;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("입력할 주문의 개수를 입력하세요: ");
    int n = scanner.nextInt();
    scanner.nextLine();

    ProductOrder[] orders = new ProductOrder[3];
    for(int i = 0; i < orders.length; i++) {
      System.out.println("상품명 : ");
      String productName = scanner.nextLine();

      System.out.println("가격 : ");
      int price = scanner.nextInt();

      System.out.println("수량 : ");
      int quantity = scanner.nextInt();
      scanner.nextLine();

      orders[i] = createOrder(productName, price, quantity);
    }

//    ProductOrder order1 = createOrder("두부", 2000, 2);
//    ProductOrder order2 = createOrder("김치", 5000, 1);
//    ProductOrder order3 = createOrder("콜라", 1500, 2);

    printOrders(orders);
    System.out.println("총 결제 금액: " + getTotalAmount(orders));

  }

  private static ProductOrder createOrder(String productName, int price, int quantity) {
    ProductOrder order = new ProductOrder();
    order.productName = productName;
    order.price = price;
    order.quantity = quantity;
    return order;
  }

  private static void printOrders(ProductOrder[] orders) {
    for(ProductOrder order : orders) {
      System.out.println("상품명: " + order.productName + ", 가격: " + order.price + ", 수량: " + order.quantity);
    }
  }

  private static int getTotalAmount(ProductOrder[] orders) {
    return Arrays.stream(orders)
        .mapToInt(order -> order.price * order.quantity)
        .sum();
  }


}
