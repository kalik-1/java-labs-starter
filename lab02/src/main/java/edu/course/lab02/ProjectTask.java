package edu.course.lab02;

public class ProjectTask {
    public enum TaskStatus {
        CREATED,
        IN_PROCESS,
        DONE
    }
    public String id;
    public String title;
    private TaskStatus status;
    public int estimatedHours;

    public ProjectTask(String taskId, String taskTitle, int estimatedCompletionHours) {
        if((taskId == "") || (taskTitle == "")) {
            throw new IllegalArgumentException("Specify task Identificator and Title");
        }
        else {
            this.id = taskId;
            this.title = taskTitle;
        }
        if(estimatedCompletionHours < 0) {
            throw new IllegalArgumentException("Task completion time cannot be negative");
        }
        if(estimatedCompletionHours == 0) {
            this.estimatedHours = 24;
        }
        else {
            this.estimatedHours = estimatedCompletionHours;
        }
        this.status = TaskStatus.CREATED;
    }

    public String changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
        String message;
        if (this.status != TaskStatus.DONE) {
        message = "Changed task status to: " + newStatus;
        }
        else {
            message = "Task is done!";
        }
        return message;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public boolean getCompletion() {
        if (this.status == TaskStatus.DONE) {
            return true;
        }
        else {
            return false;
        }
    }

    public void reEstimateCompletionHours(int newEstimationHours) {
        if (newEstimationHours > 0) {
            this.estimatedHours = newEstimationHours;
        }
        else {
            throw new IllegalArgumentException("Estimation can not be zero or negative. Did you complete your task?");
        }
    }
    
    public String getTaskInfo() {
        String taskInfo = String.format("Task information. \n Id: %s \n Title: %s \n Completion Status: %s \n Estimated Hours: %d", this.id, this.title, this.status, this.estimatedHours);
        return taskInfo;
    }
}
