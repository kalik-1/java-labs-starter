package edu.course.lab05;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import edu.course.lab05.FileValidationThrowables.DataLoadingException;
import edu.course.lab05.FileValidationThrowables.LoadResult;
import edu.course.lab05.FileValidationThrowables.SaveTaskFileLoader;

public class FileLoaderTest {
    @TempDir 
    Path tempDir; /* путь до директории, отличие path & string в том, что первый имеет валидатор и методы работы с путями в ОС 
     а также определяет какой из слешей использовать */

    String validLine = "T-101;Prepare release;backend;8;READY";
    String unicodeLine = "T-102;Настройка авторизации 🚀;бэкенд;5;IN_PROGRESS";
    String emptyFieldLine = "T-103;;backend;8;READY";
    String invalidNumberLine = "T-104;Prepare release;backend;eight;READY";
    String unknownStatusLine = "T-105;Prepare release;backend;8;UNKNOWN_STATUS";
    String wrongColumnsLine = "T-106;Prepare release;backend;8";
    String mixedLines = "T-101;Prepare release;backend;8;READY\nT-104;Broken hours;backend;abc;READY";

    private Path testSelfFile; //инициализация переменной пути которая будет использоваться всеми тестами 

    SaveTaskFileLoader loader;

    @BeforeEach
    void setUp() throws IOException /* потому что работа уже идет не в IDE а с системой */
    {
        FileValidationThrowables outerClass = new FileValidationThrowables();//ссылка на класс-обертку для нестатического загрузчика класса
        loader = outerClass.new SaveTaskFileLoader();
        testSelfFile = Files.createFile(tempDir.resolve("data.txt")); //под каждый тест свой файл датум
    }

    @Test
    void correctFileBecomesTask() throws IOException {
        Files.writeString(testSelfFile, validLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getValidLines().size());//принял стандартный шаблон
    }

    @Test
    void cyrillicSymbolsAndEmojis() throws IOException {
        Files.writeString(testSelfFile, unicodeLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getValidLines().size());//принимает с кириллицей и эмодзи
    }

    @Test
    void emptyFieldCaughtIn4K() throws IOException {
        Files.writeString(testSelfFile, emptyFieldLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getInvalidLines().size());//майкл ява джордан поймал мяч из пустого поля
    }

    @Test
    void incorrectNumberCaughtIn4K() throws IOException {
        Files.writeString(testSelfFile, invalidNumberLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getInvalidLines().size());//майкл ява джордан поймал мяч из неправильного поля estimatedHours
    }

    @Test
    void unknownStatusCaughtIn4K() throws IOException {
        Files.writeString(testSelfFile, unknownStatusLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getInvalidLines().size());//майкл ява джордан поймал мяч из неправильного TaskStatus
    }

    @Test
    void wrongFieldsNumberCaughtIn4K() throws IOException {
        Files.writeString(testSelfFile, wrongColumnsLine);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getInvalidLines().size());//майкл ява джордан поймал мяч из неправильного числа полей
    }

    @Test
    void CorrectAndIncorrectTogetherCaughtIn4K() throws IOException {
        Files.writeString(testSelfFile, mixedLines);

        LoadResult result = loader.load(testSelfFile);

        assertEquals(1, result.getInvalidLines().size());//майкл ява джордан поймал мяч из неправильных строк
        assertEquals(1, result.getValidLines().size());
    }

    @Test
    void MissingFileBringsUsDataLoadingError() throws IOException {
        Path missingFile = tempDir.resolve("nothing.txt");
        try {
            loader.load(missingFile);
        } catch(DataLoadingException dlexc) {
            assertNotNull(dlexc.getCause());
        }
    }

}
