package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public final class BankAccountTest {
    BankAccount accountBoris2007 = new BankAccount(100);
    BankAccount accountMaxMonster = new BankAccount(100);


    @Test 
    void SuccessfullBalanceWidthdraw() {
        int widthdrawAmount = 50;
        accountBoris2007.widthdraw(widthdrawAmount);
        int moneyLeft = accountBoris2007.getBalance();

        assertEquals(moneyLeft, 50);
    }

    @Test 
    void BalanceCanBecomeCredit() {
        int widthdrawAmount = 101;
        accountMaxMonster.widthdraw(widthdrawAmount);
        int moneyLeft = accountMaxMonster.getBalance();

        assertEquals(moneyLeft, -1);
    }

    @Test 
    void ThrowErrorOnNegativeBalance() {
        BankAccount accountHakerDenis = new BankAccount(-1);
    }
}
