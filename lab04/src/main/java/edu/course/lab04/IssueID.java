package edu.course.lab04;
import java.util.Map;
import java.util.HashMap;

public record IssueID (String projectCode, Integer projectNumber){

    public IssueID(String projectCode, Integer projectNumber) {
        if(projectNumber <= 0) {
            throw new IllegalArgumentException("This field cannot be negative");
        }
        else {this.projectNumber = projectNumber;}
        if(projectCode.length() == 0) {
            throw new IllegalArgumentException("You need to input some code to assign to a task");
        }
        else {this.projectCode = projectCode;}
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((projectCode == null) ? 0 : projectCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IssueID other = (IssueID) obj;
        if (projectCode == null) {
            if (other.projectCode != null)
                return false;
        } else if (!projectCode.equals(other.projectCode))
            return false;
        return true;
    } 

}

class IssueRegistry {
    private final Map<IssueID, ProjectTask> registry = new HashMap<>();

    public void add(IssueID id, ProjectTask task) {
        if(id == null || task == null) {
            throw new IllegalArgumentException("Task or id cannot be null");
        }
        else {registry.put(id, task);}
    }
    
    public ProjectTask get(IssueID id) {
        return registry.get(id);
    }

    public boolean contains(IssueID id) {
        return registry.containsKey(id);
    }

    public ProjectTask remove(IssueID id) {
        return registry.remove(id);
    }

    public int size() {
        return registry.size();
    }
}

class ProjectTask {
    private String title;
    private Integer compHours;

    public ProjectTask(String title, Integer compHours) {
        if(title.length() > 2) {
            this.title = title;
        }
        else {throw new IllegalArgumentException("Title must be lnoger than two symbols!");}
        if(compHours > 0) {
            this.compHours = compHours;
        }
        else{throw new IllegalArgumentException("Input correct positive number of hours");}
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getCompHours() {
        return this.compHours;
    }

    public void setTitle(String newTitle) {
        if(newTitle.length() > 2) {
            this.title = newTitle;
        }
        else {throw new IllegalArgumentException("Boo! Only real text permitted");}
    }

    public void setCompHours(Integer newCompHours) {
        if(newCompHours > 0) {
            this.compHours = newCompHours;
        }
        else {throw new IllegalArgumentException("Foo! Only positive permitted");}
    }
}