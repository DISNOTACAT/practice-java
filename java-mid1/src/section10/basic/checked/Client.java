package section10.basic.checked;

public class Client {

  public void call() throws MyCheckedException {
    // 문제 발생
    throw new MyCheckedException("ex");
  }

}
