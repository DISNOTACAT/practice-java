package section10.basic.unchecked;

/*
RuntimeException을 상속받는 예외는 언체크 예외가 된다.
 */
public class MyUncheckedException extends RuntimeException{
  public MyUncheckedException(String message) {
    super(message);
  }

}
