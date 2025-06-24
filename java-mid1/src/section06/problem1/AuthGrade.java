package section06.problem1;

public enum AuthGrade {

  GUEST(1, "손님"),
  LOGIN(2, "로그인 회원"),
  ADMIN(3, "관리자");

  private final int level;
  private final String description;

  AuthGrade(int level, String description) {
    this.level = level;
    this.description = description;
  }

  public static String getMenuOf(AuthGrade grade) {

    StringBuilder sb = new StringBuilder(
        "== 메뉴 목록 ==" + "\n" +
        "- 메인화면" + "\n");

    switch (grade){
      case LOGIN -> sb.append(
          "- 이메일 관리 화면" + "\n"
      );
      case ADMIN -> sb.append(
          "- 관리자 화면" + "\n"
      );
    }

    return sb.toString();
  }

  public int getLevel() {
    return level;
  }

  public String getDescription() {
    return description;
  }

  public static AuthGrade settingGrade(String input) {
    for(AuthGrade grade : AuthGrade.values()) {
      if (grade.name().equals(input.toUpperCase())) return grade;
    }
    return null;
  }

  @Override
  public String toString() {
    return "grade=" + name() +
        ", level=" + level +
        ", description=" + description ;
  }
}
