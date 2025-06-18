package section04;

public class Chain {

  private static class ValueAdder {

    private int value;

    public ValueAdder add(int addValue) {
      value += addValue;
      return this;
    }

    public int getValue() {
      return value;
    }
  }

  public static void main(String[] args) {

    ValueAdder adder = new ValueAdder();
    ValueAdder result = adder.add(1).add(2).add(3);
    System.out.println(result.getValue()); // result = 6

    StringBuilder sb = new StringBuilder();
    String str = sb.append("A").append("B").append("C")
        .insert(4, "Java")
        .delete(4, 8)
        .reverse()
        .toString();


  }
}
