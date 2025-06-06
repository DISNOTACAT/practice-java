package org.example.problem1.tobe;

public class Rectangle {

  int width;
  int height;

  public Rectangle(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int calculateArea() {
    return this.width * this.height;
  }

  public int calculatePerimeter() {
    return 2 * (this.width + this.height);
  }

  public boolean isSquare() {
    return this.width == this.height;
  }
}
