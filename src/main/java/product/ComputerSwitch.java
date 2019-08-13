package product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class ComputerSwitch implements Serializable {
@GeneratedValue
@Id
@Column(nullable = false,unique = true)
private int id;
@Column
private String switchName;
@Column
private String socket;
@OneToOne(mappedBy = "computerSwitch")
    private Product product;
@OneToOne
@JoinColumn
    private Product productOnSocket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProductOnSocket() {
        return productOnSocket;
    }

    public void setProductOnSocket(Product productOnSocket) {
        this.productOnSocket = productOnSocket;
    }

    public ComputerSwitch(String switchName, String socket, Product product) {
        this.switchName = switchName;
        this.socket = socket;
        this.product = product;
    }

    public  ComputerSwitch(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComputerSwitch)) return false;
        ComputerSwitch that = (ComputerSwitch) o;
        return getId() == that.getId() &&
                Objects.equals(getSwitchName(), that.getSwitchName()) &&
                Objects.equals(getSocket(), that.getSocket()) &&
                Objects.equals(getProduct(), that.getProduct()) &&
                Objects.equals(getProductOnSocket(), that.getProductOnSocket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSwitchName(), getSocket(), getProduct(), getProductOnSocket());
    }
}
