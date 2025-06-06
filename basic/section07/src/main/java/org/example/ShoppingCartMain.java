package org.example;

public class ShoppingCartMain {

  public static void main(String[] args) {

    ShoppingCart cart = new ShoppingCart();

    Item item1 = new Item("마늘", 2000, 2);
    Item item2 = new Item("상추", 3000, 14);

    // 장바구니에는 최대 10개만 담을 수 있다.
    // 10개 초과시 " 장바구니가 가득 찻습니다." 출력
    cart.addItem(item1);
    cart.addItem(item2);

    cart.displayItems();
  }

}
