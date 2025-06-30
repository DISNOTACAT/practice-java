package section08;

public class Cat {

  private String name;
  private String size;
  private Clothes clothes;

  public Cat(String name, String size) {
    this.name = name;
    this.size = size;
    this.clothes = new Clothes(this);
  }

  public String getName() {
    return name;
  }

  public String getSize() {
    return size;
  }

  public void dressing() {
    clothes.clothing();
    System.out.println("옷을 입었다. 냥");
  }
}
