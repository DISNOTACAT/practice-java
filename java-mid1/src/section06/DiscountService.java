package section06;

public class DiscountService {

  public int discount(ClassGrade classGrade, int price) {

    int discountPercent = 0;

    if(classGrade == ClassGrade.BASIC) {
      discountPercent = 10;
    } else if (classGrade == ClassGrade.GOLD) {
      discountPercent = 20;
    } else if (classGrade == ClassGrade.DIAMOND) {
      discountPercent = 30;
    }

    return price + discountPercent / 100;
  }

}
