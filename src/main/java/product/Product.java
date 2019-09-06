package product;




import building.Room;
import policeman.Department;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private int ProductId;
    @Column
    private ProductKind kind;
    @Column
    private String productName;
    @Column
    private String serialNumber;
    @Column
    private String inventoryNumber;
    @Column
    private String evidentialNumber;
    @Column
    private BigDecimal price;
    @Column
    private int productionYear;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ProductGroup productGroup;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
      private Room roomNumber;
    @Column
    private String comments;
    @ManyToMany(mappedBy="products")
    private Set<ProductTransfer> productTransfers;
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private ComputerDetails computerDetails;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private ComputerSwitch computerSwitch;
    @OneToOne(mappedBy = "productOnSocket")
    private ComputerSwitch socketFromSwitch;
    @Column
    private Integer positionX;
    @Column
    private Integer PositionY;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Department department;
    public Product() {
    }

    public enum ProductKind {
        ŁĄCZNOŚĆ, INFORMATYKA;
@Override
        public String toString(){
            return super.toString().toLowerCase();
       }
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getEvidentialNumber() {
        return evidentialNumber;
    }

    public void setEvidentialNumber(String evidentialNumber) {
        this.evidentialNumber = evidentialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Room getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ProductKind getKind() {
        return kind;
    }

    public void setKind(ProductKind kind) {
        this.kind = kind;
    }

    public ComputerSwitch getSocketFromSwitch() {
        return socketFromSwitch;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSocketFromSwitch(ComputerSwitch socketFromSwitch) {
        this.socketFromSwitch = socketFromSwitch;
    }

    public Set<ProductTransfer> getProductTransfers() {
        return productTransfers;
    }

    public void setProductTransfers(Set<ProductTransfer> productTransfers) {
        this.productTransfers = productTransfers;
    }

    public ComputerDetails getComputerDetails() {
        return computerDetails;
    }

    public void setComputerDetails(ComputerDetails computerDetails) {
        this.computerDetails = computerDetails;
    }

    public ComputerSwitch getComputerSwitch() {
        return computerSwitch;
    }

    public void setComputerSwitch(ComputerSwitch computerSwitch) {
        this.computerSwitch = computerSwitch;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return PositionY;
    }

    public void setPositionY(Integer positionY) {
        PositionY = positionY;
    }

    public Product(String productName, String serialNumber, ProductKind productKind, String inventoryNumber, String evidentialNumber, BigDecimal price, int productionYear, ProductGroup productGroup, String comments, Room room) {
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.evidentialNumber = evidentialNumber;
        this.price = price;
        this.productionYear = productionYear;
        this.productGroup = productGroup;
        this.comments = comments;
        this.roomNumber = room;
        this.kind = productKind;
        this.positionX = null;
        this.PositionY = null;
    }

    @Override
    public String toString() {
        return productName+" "+evidentialNumber;
    }
}
