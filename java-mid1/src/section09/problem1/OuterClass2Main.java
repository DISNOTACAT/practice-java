package section09.problem1;

import section09.problem1.OuterClass2.InnerClass;

public class OuterClass2Main {

  public static void main(String[] args) {

    OuterClass2 outer = new OuterClass2();
    OuterClass2.InnerClass inner = outer.new InnerClass();
    inner.hello();
  }
}
