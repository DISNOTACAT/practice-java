package section02;

import java.util.Objects;

public class Rectangle {

  private int width;
  private int height;

  public Rectangle(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return "R{" +
        "width=" + width +
        ", height=" + height +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rectangle rectagle = (Rectangle) o;
    return width == rectagle.width && height == rectagle.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
}
