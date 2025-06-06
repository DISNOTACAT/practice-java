package org.example;

public class ThisStudent {

  String name;
  int age;
  int grade;

  public ThisStudent(String name, int age, int grade) {
    this.name = name;
    this.age = age;
    this.grade = grade;
  }

  public ThisStudent(String name, int age) {
    this(name, age, 50);
    System.out.println("학생 이름: " + this.name + ", 나이: " + this.age + "기본 성적: " + 50);
  }
}
