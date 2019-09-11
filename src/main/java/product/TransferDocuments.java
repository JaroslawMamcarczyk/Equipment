package product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class TransferDocuments implements Serializable {
    @Id
    @Column
    @GeneratedValue
    private int id;
@Column
    private String number;
@Column
    private boolean isSignature;
@OneToMany(mappedBy = "transferDocument")
    private Set<ProductTransfer> productTransfer;

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

    public boolean isSignature() {
        return isSignature;
    }

    public void setSignature(boolean signature) {
        isSignature = signature;
    }

    public Set<ProductTransfer> getProductTransfer() {
        return productTransfer;
    }

    public void setProductTransfer(Set<ProductTransfer> productTransfer) {
        this.productTransfer = productTransfer;
    }

    public TransferDocuments(String number, boolean isSignature, Set<ProductTransfer> productTransfer) {
        this.number = number;
        this.isSignature = isSignature;
        this.productTransfer = productTransfer;
    }

    public TransferDocuments(){}
}
