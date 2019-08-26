package building;

import policeman.Department;
import product.ProductTransfer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
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
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private Floor floor;
    @Column
    @Enumerated(EnumType.STRING)
    private KindOfRoom kindOfRoom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

    @ManyToOne (fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(mappedBy="roomFrom")
    private Set<ProductTransfer> productTransfersOut;

    @OneToMany(mappedBy = "roomTo")
    private Set<ProductTransfer> productTransfersTo;
    public enum Floor{
        PARTER, PIWNICA, PIERWSZE, DRUGIE, TRZECIE
    }

    public enum KindOfRoom{
        MAGAZYN, POKÓJ, KORYTARZ, SERWEROWNIA
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<ProductTransfer> getProductTransfersOut() {
        return productTransfersOut;
    }

    public void setProductTransfersOut(Set<ProductTransfer> productTransfersOut) {
        this.productTransfersOut = productTransfersOut;
    }

    public Set<ProductTransfer> getProductTransfersTo() {
        return productTransfersTo;
    }

    public void setProductTransfersTo(Set<ProductTransfer> productTransfersTo) {
        this.productTransfersTo = productTransfersTo;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public KindOfRoom getKindOfRoom() {
        return kindOfRoom;
    }

    public void setKindOfRoom(KindOfRoom kindOfRoom) {
        this.kindOfRoom = kindOfRoom;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Room(String number, Floor floor, Building building, int x, int y, KindOfRoom kindOfRoom) {
        this.number = number;
        this.floor = floor;
        this.building = building;
        this.positionX = x;
        this.positionY = y;
        this.kindOfRoom = kindOfRoom;
    }
    public Room(String number, Floor floor, Building building, int x, int y,String description,KindOfRoom kindOfRoom) {
        this.number = number;
        this.floor = floor;
        this.building = building;
        this.positionX = x;
        this.positionY = y;
        this.kindOfRoom = kindOfRoom;
        this.description = description;
    }
    public Room(){

    }
@Override
    public String toString(){
        if(description!=null)
        return number+" "+description;
        else
            return number;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getId() == room.getId() &&
                Objects.equals(getNumber(), room.getNumber());
        //        Objects.equals(getDescription(), room.getDescription()) &&
       //         getFloor() == room.getFloor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getDescription(), getFloor());
    }
}
