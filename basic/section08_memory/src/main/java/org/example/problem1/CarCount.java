package org.example.problem1;

import java.util.ArrayList;
import java.util.List;

public class CarCount {

  public static List<Car> cars = new ArrayList<>();
  public static int count;


  public static void showTotalCars() {
    for(Car car : cars) {
      Car.showCars(car);
    }
    System.out.println("구매한 차량 수: " + count);
  }

}
