package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
    @Test
    void returnsTrueForSingleDigitPrimes() {
        boolean result = CourseToolkit.isPrime(7);

        assertTrue(result);
    }

    @Test
    void returnsTrueForMultiDigitPrimes() {
        boolean result = CourseToolkit.isPrime(13);

        assertTrue(result);
    }

    @Test
    void returnsFalseForNonPrimals() {
        boolean result = CourseToolkit.isPrime(64);

        assertFalse(result);
    }

    @Test
    void returnsFalseForLessThanThree() {
        boolean result = CourseToolkit.isPrime(2);

        assertFalse(result);
    }
}
