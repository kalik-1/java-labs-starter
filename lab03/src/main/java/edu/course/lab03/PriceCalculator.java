package edu.course.lab03;

public class PriceCalculator {
    private DiscountPolicy currentPolicy;

    PriceCalculator(DiscountPolicy currentPolicy) {
        this.currentPolicy = currentPolicy;
    }  

    public void setNewPolicy(DiscountPolicy newPolicy) {
        this.currentPolicy = newPolicy;
    }

    public double getPriceCalculated(double itemPrice) {
        return this.currentPolicy.apply(itemPrice);
    }

    public DiscountPolicy geDiscountPolicy() {
        return this.currentPolicy;
    }
}