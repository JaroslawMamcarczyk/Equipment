package policeman;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Rank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int ranksId;
    @Column
    private String nameRanks;
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
    @OneToMany(mappedBy = "policemanRank")
    private Set<Worker> workers;

    public int getRanksId() {
        return ranksId;
    }

    public void setRanksId(int ranksId) {
        this.ranksId = ranksId;
    }

    public String getNameRanks() {
        return nameRanks;
    }

    public void setNameRanks(String nameRanks) {
        this.nameRanks = nameRanks;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Rank(String nameRanks, Department department) {
        this.nameRanks = nameRanks;
        this.department = department;
    }

    public Rank(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank)) return false;
        Rank rank = (Rank) o;
        return getRanksId() == rank.getRanksId() &&
                getNameRanks().equals(rank.getNameRanks()) &&
                Objects.equals(getDepartment(), rank.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRanksId(), getNameRanks(), getDepartment());
    }

    @Override
    public String toString(){
        return nameRanks+"("+department.getDepartmentShort()+")";
    }
}
