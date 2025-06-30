package section08;

public class Clothes {
  private Cat cat;

  public Clothes(Cat cat) {
    this.cat = cat;
  }
  public void clothing() {
    String catName = cat.getName();
    String catSize = cat.getSize();
    System.out.println("고양이 " + catName + "에게 " + catSize + "사이즈의 옷을 입힙니다.");
  }

}
