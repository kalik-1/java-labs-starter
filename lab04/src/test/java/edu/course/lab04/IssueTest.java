package edu.course.lab04;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class IssueTest {
    ProjectTask task1 = new ProjectTask("Write Hello", 1);
    ProjectTask task2 = new ProjectTask("Write Hello 5 times", 5);

    IssueID newIssue1 = new IssueID("Hello", 1);
    IssueID newIssue2 = new IssueID("Hello", 1);
    IssueID newIssue3 = new IssueID("Hello Guten Tag Bonjour Hola Salve", 2);
    IssueID newIssue4 = new IssueID("Aa", 2);
    IssueID newIssue5 = new IssueID("BB", 2);


    IssueRegistry registry = new IssueRegistry();

    @Test
    void differentObjectsSameHash() {
        registry.add(newIssue1, task1);
        
        ProjectTask keyTest = registry.get(newIssue2);

        assertEquals(keyTest, task1);
    }

    @Test
    void reAddingDoesNoEffectOnSize() {
        registry.add(newIssue2, task1);
        
        Integer regSize = registry.size();

        assertEquals(regSize, 1);
    }

    @Test
    void diffHashDiffValues() {
        //Пара Aа и ББ дает одинаковый хэш 2143, значения айдишников при этом отличаются по equals, т.к. хэши одинаковые, то оба ишьюс в одной корзине-бакете
        assertEquals(newIssue4.hashCode(), newIssue5.hashCode());
        assertNotEquals(newIssue4, newIssue5);
        //Добавляем в хэщ-таблицу issue 
        registry.add(newIssue4, task1);
        registry.add(newIssue5, task2);
        //Убеждаемся, что по причине разных значений они лежат в разных ячейках таблицы (если бы были одинаковыми то ячейка переопределилась и size был бы равен 1)
        assertEquals(2, registry.size());
    }

    @Test 
    void secondRowOfEqualsCode() {
        IssueID newIssue6 = new IssueID("Halo", 2);
        String otherO = "Halo";

        assertFalse(newIssue6.equals(null));
        assertFalse(newIssue6.equals(otherO));
    }

    class MutableIssueID {
        private String projectCode; private int projectNumber;

        public MutableIssueID(String projectCode, int projectNumber) {
            this.projectCode = projectCode;
            this.projectNumber = projectNumber;
        }

        public void setNumber(int projectNumber) {
            this.projectNumber = projectNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MutableIssueID that = (MutableIssueID) o;
            return projectNumber == that.projectNumber && projectCode.equals(that.projectCode);
        }

        @Override
        public int hashCode() {
            return 31 * projectCode.hashCode() + projectNumber;
        }
    }

    @Test
    void mutableKeyHashMapPolomkaPolomka() {
        Map<MutableIssueID, ProjectTask> map = new HashMap<>();
        MutableIssueID key = new MutableIssueID("Flip done", 1);
        ProjectTask task = new ProjectTask("Do a flip", 1);

        map.put(key, task);

        assertEquals(task, map.get(key));

        key.setNumber(3);

        assertNull(map.get(key));

        MutableIssueID oldKey = new MutableIssueID("Flip done", 1);
        assertNull(map.get(oldKey));

        assertEquals(1, map.size());
    }
}