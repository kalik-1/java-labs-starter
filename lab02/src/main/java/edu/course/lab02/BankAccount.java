package edu.course.lab02;

public final class BankAccount{
    private int balance;

    public BankAccount(int accountBalance) {
        if(accountBalance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = accountBalance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void widthdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

}