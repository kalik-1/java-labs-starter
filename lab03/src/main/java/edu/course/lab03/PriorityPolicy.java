package edu.course.lab03;

//В расчет приоритетности принимаются только задачи со статусом "В работе", чем больше часов требует задача тем выше приоритет
public interface PriorityPolicy {
    int calculatePriority(ProjectTask task);
}

class EffortPriorityPolicy implements PriorityPolicy{
    private int priority;

    EffortPriorityPolicy() {
        ;
    }

    @Override 
    public int calculatePriority(ProjectTask task) {
        if(task.getEstHours() >= 720) {
            priority = 5;
        }
        else if(task.getEstHours() >= 240) {
            priority = 4;
        }
        else if(task.getEstHours() >= 72) {
            priority = 3;
        }
        else if(task.getEstHours() >= 24) {
            priority = 2;
        }
        else {
            priority = 1;
        }
        return priority;
    }

}

class StatusPriorityPolicy implements PriorityPolicy{
    private int priority;

    StatusPriorityPolicy () {
        ;
    }

    @Override 
    public int calculatePriority(ProjectTask task) {
        switch (task.getStatus()) {
            case CREATED:
                priority = 2;
                break;
            case IN_PROCESS:
                priority = 3;
                break;
            case DONE:
                priority = 1;
                break;
        }
        return priority;
    }
}

class TaskPrioritizer {
    private PriorityPolicy currentPolicy;

    TaskPrioritizer(PriorityPolicy policy) {
        this.currentPolicy = policy;
    }

    public int compareTasksByPriority(ProjectTask taskUno, ProjectTask taskDos) {
        int comparatorCase;
        if(this.currentPolicy.calculatePriority(taskUno) > this.currentPolicy.calculatePriority(taskDos)) {
            comparatorCase = 1;
        }
        else if(this.currentPolicy.calculatePriority(taskUno) < this.currentPolicy.calculatePriority(taskDos)) {
            comparatorCase = 2;
        }
        else {
            comparatorCase = 0;
        }
        return comparatorCase;
    }

    public void changePriorityPolicy(PriorityPolicy newPolicy) {
        this.currentPolicy = newPolicy;
    }
}

enum TaskStatus {
        CREATED,
        IN_PROCESS,
        DONE
    }

class ProjectTask {
    
    private String id;
    private String title;
    private TaskStatus status;
    private int estimatedHours;

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

    public int getEstHours() {
        return estimatedHours;
    }
}

