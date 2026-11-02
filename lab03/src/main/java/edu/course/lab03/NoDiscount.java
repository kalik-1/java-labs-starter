package edu.course.lab03;

public class NoDiscount implements DiscountPolicy {

	NoDiscount() {
	}

	@Override
	public double apply(double price) {
		if(price >= 0) {
			return price;
		}
		else {
			throw new IllegalArgumentException("Price cannot be negative. Ever heard about Amazon?");
		}
	}
}