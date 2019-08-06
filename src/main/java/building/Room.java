package building;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class Room implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private int id;
    @Column
    private String number;
    @Column
    private int positionX;
    @Column
    private int positionY;
    @Column
    @Enumerated(EnumType.STRING)
    private Floor floor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Building building;


//    @OneToMany(mappedBy = "roomNumber")
 //   private Set<Room> room;

    public enum Floor{
        PARTER, PIWNICA, PIERWSZE, DRUGIE, TRZECIE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

 //   public Building getBuilding() {
  //      return building;
  //  }

 //   public void setBuilding(Building building) {
  //      this.building = building;
  //  }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room(String number, Floor floor, Building building, int x, int y) {
        this.number = number;
        this.floor = floor;
        this.building = building;
        this.positionX = x;
        this.positionY = y;
    }
    public Room(){

    }
}
