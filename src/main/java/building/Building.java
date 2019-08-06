package building;

import javax.persistence.*;
import java.io.Serializable;
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
}
