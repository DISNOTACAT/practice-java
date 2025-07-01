package section10.basic.unchecked;

public class Client {
  public void call() {
    throw new MyUncheckedException("ex");
  }

}
