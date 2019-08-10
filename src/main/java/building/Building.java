package building;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Building implements Serializable {
    @Id
    @GeneratedValue
    @Column (nullable = false, unique = true)
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 //   public Set<Room> getRooms() {
 //       return rooms;
 //   }

  //  public void setRooms(Set<Room> rooms) {
 //       this.rooms = rooms;
  //  }

    public Building(String name) {
        this.name = name;
    }

    public Building(){
    }
    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building)) return false;
        Building building = (Building) o;
        return getId() == building.getId() &&
                getName().equals(building.getName()) &&
                Objects.equals(rooms, building.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), rooms);
    }
}
