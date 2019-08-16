package policeman;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Department implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private int id;
    @Column
    private String departmentName;
    @Column
    private String departmentShort;
    @OneToMany(mappedBy = "department")
    private Set<Rank> ranks;
    @OneToMany(mappedBy = "policemanDepartment")
    private Set<Worker> workers;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public String getDepartmentShort() {
        return departmentShort;
    }

    public void setDepartmentShort(String departmentShort) {
        this.departmentShort = departmentShort;
    }

    public Department(String departmentName, String departmentShort) {
        this.departmentShort = departmentShort;
        this.departmentName = departmentName;
    }

    public Department() {
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId() &&
                getDepartmentName().equals(that.getDepartmentName()) &&
                Objects.equals(getRanks(), that.getRanks()) &&
                Objects.equals(getWorkers(), that.getWorkers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentName(), getRanks(), getWorkers());
    }
}
