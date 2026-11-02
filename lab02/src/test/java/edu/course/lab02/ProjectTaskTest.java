package edu.course.lab02;

import org.junit.jupiter.api.Test;

import edu.course.lab02.ProjectTask.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;

import static edu.course.lab02.ProjectTask.TaskStatus;


public class ProjectTaskTest {
    ProjectTask exampleTask0 = new ProjectTask("res0", "Fetch relevant information on topic", 4);
    ProjectTask exampleTask1 = new ProjectTask("dev0", "Setup GitHub repository for the TaskManager project", 12);
    ProjectTask exampleTask2 = new ProjectTask("des0", "Create 3 different logo and front page designs", 36);

    @Test
    void StatusChangesCorrectly() {
        exampleTask0.changeStatus(TaskStatus.IN_PROCESS);
        assertEquals(exampleTask0.getStatus(), TaskStatus.IN_PROCESS);
    }

    @Test
    void TaskIsMarkedDone() {
        exampleTask0.changeStatus(TaskStatus.DONE);
        assertEquals(exampleTask0.getCompletion(), true);
    }

    @Test
    void ChangesEstimationHoursToPositiveNumbers() {
        exampleTask1.reEstimateCompletionHours(24);
        assertEquals(exampleTask1.estimatedHours, 24);
    }

    @Disabled 
    @Test
    void ThrowsErrorOnNegativeCompletionHours() {
        exampleTask2.reEstimateCompletionHours(-16);
    }
    

    @Test
    void TaskInfoChangesCorrectly() {
        exampleTask0.changeStatus(TaskStatus.IN_PROCESS);
        exampleTask0.reEstimateCompletionHours(8);
        exampleTask0.getTaskInfo();
        assertEquals(exampleTask0.getTaskInfo(), String.format("Task information. \n Id: %s \n Title: %s \n Completion Status: %s \n Estimated Hours: %d", "res0", "Fetch relevant information on topic", TaskStatus.IN_PROCESS, 8));
    }
}
