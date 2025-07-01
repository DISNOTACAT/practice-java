package section10.basic.unchecked;


public class Service {
  Client client = new Client();
  public void callCatch() {
    try {
      client.call();
    } catch (MyUncheckedException e) {
      System.out.println("예외 처리, message = " + e.getMessage());
    }
    System.out.println("정상 로직");
  }

  /*
  예외를 잡지 않아도 자동으로 처리된다.
   */
  public void callThrow() {
    client.call();
  }
}
