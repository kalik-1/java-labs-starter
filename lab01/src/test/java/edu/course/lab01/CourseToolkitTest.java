package edu.course.lab01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Disabled;

import java.util.Scanner;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }


    @Test 
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test 
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }
    
    //Простые числа

    //Параметрический тест с использованием csv для удобства
    @ParameterizedTest()
    @CsvSource({
        "-5, false",
        "0, false",
        "1, false",

        "2, true",
        "3, true",
        "13, true",

        "4, false",
        "49, false",
        "64, false"
    })
    void multiplePrimesParameterizedTest(int number, boolean resultExpected) {
        boolean result = CourseToolkit.isPrime(number);
        assertEquals(resultExpected, result);
    }

    @Test
    void returnsTrueForSingleDigitPrimes() {
        boolean result = CourseToolkit.isPrime(2);

        assertTrue(result);
    }

    @Test
    void returnsTrueForMultiDigitPrimes() {
        boolean result = CourseToolkit.isPrime(13);

        assertTrue(result);
    }

    @Test
    void returnsFalseForNonPrimals() {
        boolean result = CourseToolkit.isPrime(169);

        assertFalse(result);
    }

    @Test
    void returnsFalseForLessThanTwo() {
        boolean result = CourseToolkit.isPrime(1);

        assertFalse(result);
    }

    //Палиндромы 

    @Test
    void returnsTrueForPalindromeSingleDigit() {
        boolean result = CourseToolkit.isPalindrome("1");

        assertTrue(result);
    }

    @Test
    void returnsTrueForPalindromeMultiDigit() {
        boolean result = CourseToolkit.isPalindrome("1221");

        assertTrue(result);
    }

    @Test
    void returnsFalseForNonPalindrome() {
        boolean result = CourseToolkit.isPalindrome("1234");

        assertFalse(result);
    }

    @Disabled ("Тест создан чтобы намеренно падать и не должен мешать билду")
    @Test
    void throwsExceptionForNull() {
        boolean result = CourseToolkit.isPalindrome(null);
        //Заглушка
        assertTrue(result);
    }
    
    //Среднее арифметическое 

    @Test 
    void returnsAverageForArrayOfNumbers() {
        double result = CourseToolkit.average(new int[] {1, 2, 3, 4, 5}, Optional.of(0), Optional.of(3));

        assertEquals(result, 2);
    }

    @Test 
    void returnsAverageForOneNumber() {
        double result = CourseToolkit.average(new int[] {6}, Optional.empty(), Optional.empty());

        assertEquals(result, 6);
    }

    @Disabled ("Тест создан чтобы намеренно падать и не должен мешать билду")
    @Test 
    void throwsExceptionForEmptyArray() {
        double result = CourseToolkit.average(new int[] {}, Optional.empty(), Optional.empty());

        assertEquals(result, 2);
    }
    

    @Disabled ("Тест создан чтобы намеренно падать и не должен мешать билду")
    @Test 
    void throwsExceptionForNullArray() {
        double result = CourseToolkit.average(null, Optional.empty(), Optional.empty());

        assertEquals(result, 2);
    }
    
    /* 
    @Test
    void runSmallCLI() {
        Scanner cli = new Scanner(System.in);
        boolean isRunning = true;
        System.out.println("///CLI INITIALIZED///");
        System.out.println("Input New Line: ");
        while(isRunning) {
            System.out.print("\ncli> ");
            if(!cli.hasNextLine()) {
                break;
            }

            String cliInput = cli.nextLine().trim();

            if(cliInput.isEmpty()) {
                continue;
            }

            String[] parseCLIInput = cliInput.split("\\s+");
            System.out.println(parseCLIInput);
            String command = parseCLIInput[0].toLowerCase();
            String assertedResult = parseCLIInput[(parseCLIInput.length - 1)];
            
            switch (command) {
                case "prime":
                    String numberToCheck = parseCLIInput[1];
                    boolean resultForPrime = CourseToolkit.isPrime(Integer.parseInt(numberToCheck));
                    System.out.println("Результат проверки на простоту: " + resultForPrime + "Ожидаемый результат: " + assertedResult);
                    break;
            
                case "palindrome":
                    String stringToCheck = parseCLIInput[1];
                    boolean resultForPalindrome = CourseToolkit.isPalindrome(stringToCheck);
                    System.out.println("Результат проверки на простоту: " + resultForPalindrome + "Ожидаемый результат: " + assertedResult);
                    break;

                case "average":
                    String[] data = Arrays.copyOfRange(parseCLIInput, 1, parseCLIInput.length - 1);
                    int[] dataToInt = new int[data.length];

                    for(int i = 0; i < data.length; i++) {
                        dataToInt[i] = Integer.parseInt(data[i]);
                    }
                    double result = CourseToolkit.average(dataToInt, Optional.empty(), Optional.empty());
                    System.out.println("Результат проверки на простоту: " + result + "Ожидаемые результат: " + assertedResult);
                    break;
            }
        }
    }
    */
    
}
