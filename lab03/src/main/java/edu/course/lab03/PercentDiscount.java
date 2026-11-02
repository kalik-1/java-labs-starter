package edu.course.lab03;

public class PercentDiscount implements DiscountPolicy {
    private double discountPercent;

	PercentDiscount(double discountPercent) {
        if(!((discountPercent <= 100.0) && (discountPercent >= 0.0))) {
            throw new IllegalArgumentException("Discount must be a value of 0-100.");
        }
        this.discountPercent = discountPercent;
    } 

	@Override
	public double apply(double price) {
		if(price >= 0) {
			price = price - ((price / 100) * this.discountPercent);
            return price;
		}
		else {
			throw new IllegalArgumentException("Price cannot be negative.");
		}
	}
}
