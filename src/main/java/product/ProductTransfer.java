package product;

import building.Room;
import policeman.Department;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Set;

@Entity
@Table
public class ProductTransfer implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
    private Year year;
@Column
private String elements;
@ManyToOne
private Product product;
@ManyToOne
private Room roomFrom;
@ManyToOne
private Room roomTo;
@ManyToOne
    private Department departmentFrom;
@ManyToOne
    private Department departmentTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Room getRoomFrom() {
        return roomFrom;
    }

    public void setRoomFrom(Room roomFrom) {
        this.roomFrom = roomFrom;
    }

    public Room getRoomTo() {
        return roomTo;
    }

    public void setRoomTo(Room roomTo) {
        this.roomTo = roomTo;
    }

    public Department getDepartmentFrom() {
        return departmentFrom;
    }

    public void setDepartmentFrom(Department departmentFrom) {
        this.departmentFrom = departmentFrom;
    }

    public Department getDepartmentTo() {
        return departmentTo;
    }

    public void setDepartmentTo(Department departmentTo) {
        this.departmentTo = departmentTo;
    }
}
