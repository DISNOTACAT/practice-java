package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private List<Item> itemList;
  private int itemCount;

  public void addItem(Item item) {

    if(itemList == null) {
      itemList = new ArrayList<>();
    }

    if((itemCount + item.getQuantity()) >= 10) {
      System.out.println("장바구니가 가득 찼습니다.");
      return;
    }

    itemList.add(item);
    itemCount += item.getQuantity();
  }

  public void displayItems() {
    int sum = 0;

    System.out.println("장바구니 상품 출력");

    for(Item item : itemList) {
      sum += item.getTotalPrice();
      item.display();
    }

    System.out.println("전체 가격 합 : " + sum);
  }
}
