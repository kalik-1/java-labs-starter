package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if(number < 2)
        {
            return false;
        }
        for(int i = 2; i < (((int) (Math.sqrt(number))) + 1); i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String text) {
        if (text == null)
        {
            throw new IllegalArgumentException("Text must be of string and not null");
        }
        String text_reverse = "";
        String text_actual = text;
        for(int i = text.length() - 1; i >= 0; i--)
            {
                text_reverse += text.charAt(i);
            }
        if(text_reverse.equals(text_actual))
            {
                return true;
            }
        else
            {
                return false;
            }
    }

    public static double average(int[] values) {
        if (values == null || values.length == 0)
        {
            throw new IllegalArgumentException("Array must be not null or have more than 0 digits");
        }
        int sum = 0;
        int count = 0;
        for(int i = 0; i < values.length; i++)
        {
            sum += values[i];
            count++;
        }
        return(sum / count);
    }
}
