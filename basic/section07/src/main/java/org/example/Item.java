package org.example;

public class Item {

  private String itemName;
  private int price;
  private int quantity;

  public Item(String itemName, int price, int quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }

  public int getTotalPrice() {
    return price * quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void display() {
    System.out.printf(" 상품명: %s , 합계: %d%n", itemName, getTotalPrice());
  }
}
