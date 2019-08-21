package building;

import product.ProductTransfer;

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
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private Floor floor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Building building;

    @OneToMany(mappedBy="roomFrom")
    private Set<ProductTransfer> productTransfersOut;

    @OneToMany(mappedBy = "roomTo")
    private Set<ProductTransfer> productTransfersTo;
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
    public Room(String number, Floor floor, Building building, int x, int y,String description) {
        this.number = number;
        this.floor = floor;
        this.building = building;
        this.positionX = x;
        this.positionY = y;
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
}
