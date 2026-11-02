package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountTest {
    // Товар
    double pantsPrice = 300.0; 
    // Политика скидок
    DiscountPolicy blekFriday = new PercentDiscount(33);
    DiscountPolicy whitFriday = new PercentDiscount(1);
    DiscountPolicy regularFriday = new NoDiscount();
    //Калькулятор
    PriceCalculator priceCalculator = new PriceCalculator(regularFriday);

    @Test 
    void regularPriceIsCorrect(){
        assertEquals(priceCalculator.getPriceCalculated(pantsPrice), pantsPrice);
    }

    @Test 
    void changedPolicyWorks(){
        priceCalculator.setNewPolicy(blekFriday);
        assertEquals(priceCalculator.getPriceCalculated(pantsPrice), pantsPrice - ((pantsPrice / 100.0) * 33.0));
    }

    @Test 
    void changeIsCorrect(){
        priceCalculator.setNewPolicy(whitFriday);
        assertEquals(priceCalculator.geDiscountPolicy(), whitFriday);
    }
}
