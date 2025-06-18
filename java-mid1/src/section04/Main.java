package section04;

public class Main {

  public static void main(String[] args) {

    String strObj1 = new String("hello");
    String strObj2 = new String("hello");

    System.out.println("== 비교 : " + (strObj1 == strObj2)); // false
    System.out.println("equals() 비교 : " + strObj1.equals(strObj2)); // true

    String str1 = "hello";
    String str2 = "hello";

    System.out.println("== 비교 : " + (str1 == str2)); // true
    System.out.println("equals() 비교 : " + str1.equals(str2)); // true


  }

  private static boolean isSameString(String str1, String str2) {
    return str1 == str2;
  }

}
