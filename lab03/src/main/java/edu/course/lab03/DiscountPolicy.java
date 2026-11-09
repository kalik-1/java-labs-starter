package edu.course.lab03;

public interface DiscountPolicy {
	double apply(double price);
}

class NoDiscount implements DiscountPolicy {

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

class PercentDiscount implements DiscountPolicy {
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

class PriceCalculator {
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