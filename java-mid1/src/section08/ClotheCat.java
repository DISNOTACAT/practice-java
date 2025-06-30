package section08;

public class ClotheCat {

  private String name;
  private String size;
  private Clothes clothes;

  public ClotheCat(String name, String size) {
    this.name = name;
    this.size = size;
    this.clothes = new Clothes(); // Cat 매개변수 필요없어짐
  }

  public void dressing() {
    clothes.clothing();
    System.out.println("옷을 바로 입었다. 냥"); // 예시 결과 구분을 위한 문구 수정
  }

  private class Clothes {
    // Cat 생성 삭제
    public void clothing() { // 필드에 접근
      System.out.println("고양이 " + name + "에게 " + size + "사이즈의 옷을 입힙니다.");
    }

  }
}
