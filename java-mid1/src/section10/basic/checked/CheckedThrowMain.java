package section10.basic.checked;

public class CheckedThrowMain {

  public static void main(String[] args) throws MyCheckedException {
    Client client = new Client();
    client.call();
    System.out.println("정상 종료");
  }

}
