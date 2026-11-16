package edu.course.lab04;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BookTest {
    Map<Book, Integer> bookMap = new HashMap<>();

    Book book1 = new Book("Merry Go Round Of Life", "1234567890123");
    Book book2 = new Book("Merry Go Round Of Life (Alt Version)", "1234567890123");
    Book book3 = new Book("Toccata", "3210987654321");

    @Test 
    void hashMapKeyFinder() {
        bookMap.put(book1, 16);

        Integer foreignKey = bookMap.get(book2);

        assertEquals(16, foreignKey);
    }

    @Test 
    void differentISBNs() {
        /*
        System.out.println(book3.hashCode());
        System.out.println(book2.hashCode());
        System.out.println(book1.hashCode());
        */
       assertNotEquals(book3.hashCode(), book1.hashCode());
    }
}
