package section09.problem2;

import java.awt.print.Book;

public class Library {

  private Book[] books;
  private int index;

  public Library(int size) {
    this.books = new Book[size];
    this.index = 0;
  }

  public void addBood(String title, String author) {

    // 검증 로직을 처리하고 -> 얼리리턴으로 조건의 댑스를 줄이고, 가장 아래 정상 로직이 있음을 명시함
    if(index >= books.length) {
      System.out.println("도서관 저장 공간이 부족합니다.");
      return;
    }

    // 정상 로직 처리
    books[index++] = new Book(title, author);
  }

  public void showBooks() {
    System.out.println("=== 책 목록 출력 ===");
//    for(Book book: books) { // 책이 저장된 범위 까지만 돌아야함. 이렇게 돌리면 빈곳도 돌아버림
//      System.out.println("도서 제목: " + book.title + ", 저자: " + book.author);
//    }

    for(int i = 0; i < index; i++) {
      System.out.println("도서 제목: " + books[i].title + ", 저자: " + books[i].author); // 내부 클래스 사용이기 때문에 getter 필요 없음
    }
  }

  private static class Book { // 바깥 클래스의 인스턴스를 쓸 예정이 없으므로 static
    private String title;
    private String author;

    private Book(String title, String author) {
      this.title = title;
      this.author = author;
    }
  }
}
