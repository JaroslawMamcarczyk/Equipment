package policeman;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import product.ProductTransfer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int id;
    @Column
    private String departmentName;
    @Column
    private String departmentShort;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Rank> ranks;
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @OneToMany(mappedBy = "policemanDepartment")
    private Set<Worker> workers;
    @OneToMany(mappedBy = "departmentFrom")
    private Set<ProductTransfer> productTransfersFrom;
    @OneToMany(mappedBy = "departmentTo")
    private Set<ProductTransfer> productTransfersTo;


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
                getDepartmentName().equals(that.getDepartmentName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentName(), getRanks(), getWorkers());
    }

    @Override
    public String toString(){
        return departmentName;
    }
}
