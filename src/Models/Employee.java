package Models;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private boolean isSupervisor;
    private Employee supervisor;
    private boolean isCeo;
    private List<Assessment> assessments;

    public Employee(Integer id, String name, boolean isSupervisor, Employee supervisor, boolean isCeo) {
        this.id = id;
        this.name = name;
        this.supervisor = supervisor;
        this.isSupervisor = isSupervisor;
        this.isCeo = isCeo;
        this.assessments = new ArrayList<>();
    }

    public boolean isNotCeo() {
        return !isCeo;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSupervisorId() {
        return supervisor != null ? supervisor.id : null;
    }

    public void addAssessment(Assessment  assessmentToInsert) {
        this.assessments.add(assessmentToInsert);
    }

    @Override
    public String toString() {
        return "Employee{" + '\'' +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", isSupervisor=" + this.isSupervisor + '\'' +
                ", supervisor=" + (this.supervisor != null ? this.supervisor : "None") + '\'' +
                '}';
    }

    public boolean isCeo() {
        return this.isCeo;
    }
}
