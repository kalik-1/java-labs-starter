package edu.course.lab05;

import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class HomeTest {
    
    @TempDir
    Path tempDir;

    @Test 
    void checkFileInTemporaryDirectory() throws IOException //тест на работу врем. директории
    {
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"));

        List<String> container = Files.readAllLines(file);
        assertEquals(container, List.of("10", "bad", "20"));//содержимое также соответствует заданному
        String filename = file.getFileName().toString();
        assertEquals("numbers.txt", filename); //файл создался с правильным именем и доступен
    }

    @Test 
    void checkIntificationThrowsNumberFormatException() throws IOException{
        Path file = tempDir.resolve("numbers.txt");
        Files.write(file, List.of("10", "bad", "20"));
        List<String> container = Files.readAllLines(file);
        int[] parsedInts = new int[3];
        int count = -1;
        for(String line : container) {
            count++;
            try {
                parsedInts[count] = Integer.parseInt(line);
            } catch (NumberFormatException exc) {
                System.out.printf("Format exception caught parsing file %s", file.getFileName().toString());
            }
        }
    }

}
