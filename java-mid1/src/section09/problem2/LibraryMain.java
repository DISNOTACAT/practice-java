package section09.problem2;

public class LibraryMain {

  public static void main(String[] args) {

    Library library = new Library(4);
    library.addBood("책1", "저자1");
    library.addBood("책2", "저자2");
    library.addBood("책3", "저자3");
    library.addBood("책4", "저자4");
    library.addBood("책5", "저자5");
    library.showBooks();
  }

}
