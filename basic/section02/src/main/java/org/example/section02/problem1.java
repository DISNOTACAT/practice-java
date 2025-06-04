package org.example.section02;

public class problem1 {

  private static class MovieReview {

    String title;
    String review;

    public MovieReview(String title, String review) {
      this.title = title;
      this.review = review;
    }
  }

  public static void main(String[] args) {

    MovieReview[] reviews = new MovieReview[2];

    // 영화 리뷰 정보 선언
    String title1 = "인셉션";
    String review1 = "인생은 무한 루프";

    String title2 = "어바웃 타임";
    String review2 = "인생 시간 영화!";

    MovieReview inception = new MovieReview(title1, review1);
    MovieReview aboutTime = new MovieReview(title2, review2);
    reviews[0] = inception;
    reviews[1] = aboutTime;

    // 영화 리뷰 정보 출력
    for(MovieReview r : reviews) {
      System.out.println("영화 제목 : " + r.title + " , 리뷰 : " + r.review);
    }


  }

}
