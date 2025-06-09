package org.example.problem1;

import java.util.ArrayList;
import java.util.List;

public class Car {

  private String name;

  public Car(String name) {
    this.name = name;
    CarCount.cars.add(this);
    CarCount.count++;
  }

  public static void showCars(Car car) {
    System.out.println("챠랑 구입, 이름: " + car.name);
  }

}
