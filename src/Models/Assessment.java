package Models;

public class Assessment {
    private Integer id;
    private Integer year;
    private Employee employee;
    private Employee supervisor;

    public Assessment(Integer year, Employee employee, Employee supervisor, Integer id) {
        this.id = id;
        this.year = year;
        this.employee = employee;
        this.supervisor = supervisor;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String toString() {
        return "{" + '\'' +
                "Employee ref=" + this.employee + '\'' +
                ", year=" + this.year + '\''  +
                ", supervisor=" + this.supervisor + '\'' +
                ", id=" + this.id + '\'' +
                "}";

    }
}
