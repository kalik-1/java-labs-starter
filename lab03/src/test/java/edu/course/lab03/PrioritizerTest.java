package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrioritizerTest {
    //Задачи
    ProjectTask task1 = new ProjectTask("1", "buy kolbasi", 1);
    ProjectTask task2 = new ProjectTask("2", "bake pizza", 48);
    ProjectTask task3 = new ProjectTask("3", "fork nginx form scratch", 1000);
    //Политика приоритизации
    EffortPriorityPolicy compByEffort = new EffortPriorityPolicy();
    StatusPriorityPolicy compByStatus = new StatusPriorityPolicy();
    //Приоритизатор
    TaskPrioritizer managerOleg = new TaskPrioritizer(compByEffort);
    
    @Test 
    void comparationByEffortWorks() {
        assertEquals(managerOleg.compareTasksByPriority(task1, task2), 2);
    }

    @Test 
    void comparationByEffortWorks2() {
        assertEquals(managerOleg.compareTasksByPriority(task3, task2), 1);
    }

    @Test 
    void comparationByStatusWorks() {
        task1.changeStatus(TaskStatus.DONE);
        task3.changeStatus(TaskStatus.IN_PROCESS);
        managerOleg.changePriorityPolicy(compByStatus);
        assertEquals(managerOleg.compareTasksByPriority(task1, task3), 2);
    }

    @Test 
    void comparationByStatusWorks2() {
        assertEquals(managerOleg.compareTasksByPriority(task2, task1), 1);
    }
}