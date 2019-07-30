package product;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private int ProductId;
  //  private ProductKind kind;
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
    //  private Building roomNumber;
    //private Departament department;
    //private StringProperty category = new SimpleStringProperty();
    @Column
    private String comments;

    public Product() {
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

    public Product(String productName, String serialNumber, String inventoryNumber, String evidentialNumber, BigDecimal price, int productionYear, ProductGroup productGroup, String comments) {
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.evidentialNumber = evidentialNumber;
        this.price = price;
        this.productionYear = productionYear;
        this.productGroup = productGroup;
        this.comments = comments;
    }

}
