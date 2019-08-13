package policeman;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Range implements Serializable {
@Id
@GeneratedValue
@Column(nullable = false,unique = true)
private int id;
@Column
private String rangeName;
@Column
private String path = null;
@OneToMany(mappedBy = "policemanRange")
private Set<Worker> workers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public Range(String rangeName, String path) {
        this.rangeName = rangeName;
        this.path = path;
    }

    public Range(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Range)) return false;
        Range range = (Range) o;
        return getId() == range.getId() &&
                getRangeName().equals(range.getRangeName()) &&
                Objects.equals(getPath(), range.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRangeName(), getPath());
    }

    @Override
    public String toString() {
        return "Range{" +
                "rangeName='" + rangeName + '\'' +
                '}';
    }
}