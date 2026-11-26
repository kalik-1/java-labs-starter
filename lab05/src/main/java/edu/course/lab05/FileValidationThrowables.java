package edu.course.lab05;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileValidationThrowables {

    public record ValidationError(int lineNumber, String field, String message) {} 
    //берем рекорд, потому что на переопр записи нам начхать, а сама она компактна и генерирует все что нужно для класса-лога

    public final class LoadResult {
        // данный класс необходим чтобы отделить нормально считанные данные от данных считанных с ошибкой
        List<Task> validLines;
        List<ValidationError> invalidLines;
        public LoadResult (List<Task> validLoad, List<ValidationError> invalidLoad) {
            this.validLines = validLoad;
            this.invalidLines = invalidLoad;
        }

        public List<Task> getValidLines() {
            return validLines;
        }

        public List<ValidationError> getInvalidLines() {
            return invalidLines;
        }
    }

    public final class DataLoadingException extends RuntimeException {
        public DataLoadingException(String message, Throwable cause) {
            super(message, cause);
        }
    } //тут вообще все готово

    public class SaveTaskFileLoader {
        // данный класс необходим чтобы считывать файл, обрабатывать возникающие ошибки и проводить валидации

        public SaveTaskFileLoader() {

        }

        public LoadResult load(Path path) throws IOException{

            LoadResult resultLoad;//святой грааль данного метода хранящий список тасок и список ошибок

            int lineNum = 0;//указатель строки для лога

            List<Task> valids = new ArrayList<>();//список прочитанных тасок
            List<ValidationError> oguzki = new ArrayList<>();//список ошибок
            List<String> rawLoad; //сырое чтение по строкам, в отличие от буферного чтения загружает в память всю строку сразу и расхродует много памяти 

            try {
                rawLoad = Files.readAllLines(path, StandardCharsets.UTF_8);
                } catch (IOException ioexc) {
                throw new DataLoadingException(String.format("Cannot open or read file at: %s", path.toString()), ioexc);
            }
            if(rawLoad.isEmpty()) {
                oguzki.add(new ValidationError(1, "any", "Empty file"));
                resultLoad = new LoadResult(valids, oguzki);
                return resultLoad;
            }
            for(String line : rawLoad) {
                lineNum++;
                parseLine(line, lineNum, oguzki, valids);
            }
            resultLoad = new LoadResult(valids, oguzki);
            return resultLoad;

        }

        public void parseLine(String line, int lineNum, List<ValidationError> errors, List<Task> valids) {
            List<String> resultPack = new ArrayList<>();
            boolean quotationMarkOpen = false;
            StringBuilder currentField = new StringBuilder();

            String id;
            String title;
            String category;
            String estimatedHours;
            String status;
            int intEstimatedHours;
            TaskStatus enumStatus;

            for(int i = 0; i < line.length(); i++) {
                char cur = line.charAt(i);
                if (cur == '"') {
                    quotationMarkOpen = !quotationMarkOpen; //прям как в дереве лср кэша ;)
                } 
                else if (cur == ';' && !quotationMarkOpen) {
                    resultPack.add(currentField.toString());
                    currentField.setLength(0);
                } 
                else {
                    currentField.append(cur);
                }
            }
            if(quotationMarkOpen) {
                errors.add(new ValidationError(lineNum, String.format("%d", resultPack.size() + 1), 
                String.format("Unclosed brackets at line %d", lineNum)));
                return;
            }
            resultPack.add(currentField.toString());
            if (resultPack.size() != 5) {
                errors.add(new ValidationError(lineNum, "columns", "Expected 5 columns, got " + resultPack.size()));
                return;
            }

            id = resultPack.get(0);
            title = resultPack.get(1);
            category = resultPack.get(2);
            estimatedHours = resultPack.get(3);
            status = resultPack.get(4);

            if(id.length() <= 0) {
                errors.add(new ValidationError(lineNum, "id", "Task id must be of length greater than 0"));
                return;
            }
            if(title.length() <= 0) {
                errors.add(new ValidationError(lineNum, "title", "Task title must be of length greater than 0"));
                return;
            }
            if(category.length() <= 0) {
                errors.add(new ValidationError(lineNum, "category", "Task category must be of length greater than 0"));
                return;
            }
            try {
                intEstimatedHours = Integer.parseInt(estimatedHours);
                if(intEstimatedHours <= 0) {
                    errors.add(new ValidationError(lineNum, "estimatedHours", "Variable estimatedHours cannot be negative"));
                    return;
                }
            } catch (NumberFormatException nfexc) {
                errors.add(new ValidationError(lineNum, "estimatedHours", "Not an integer value for estimatedHours"));
                return;
            }
            try {
                enumStatus = TaskStatus.valueOf(status);
            } catch (IllegalArgumentException iaexc) {
                errors.add(new ValidationError(lineNum, "status", "TaskStatus value like this is not allowed or listed in TaskStatus"));
                return;
            }
            valids.add(new Task(id, title, category, intEstimatedHours, enumStatus));
            return;
        }
            
    }
    

    public enum TaskStatus {
        READY, IN_PROGRESS, DONE 
    }

    public final class Task {
        private final String id;
        private final String title;
        private final String category;
        private final int estimatedHours;
        private final TaskStatus status;

        public Task(String id, String title, String category, int estimatedHours, TaskStatus status) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.estimatedHours = estimatedHours;
            this.status = status;
        }

        public String getId() {
            return id; 
        }
        public String getTitle() {
            return title; 
        }
        public String getCategory() { 
            return category; 
        }
        public int getEstimatedHours() { 
            return estimatedHours; 
        }
        public TaskStatus getStatus() { 
            return status; 
        }
    }

}

