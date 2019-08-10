package product;




import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class ProductGroup {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
    @Column (nullable=false)
    private String groupName;

    @OneToMany(mappedBy = "productGroup")
    private Set<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ProductGroup(String groupName) {
        this.groupName = groupName;
    }
    public ProductGroup(){

    }

    @Override
    public String toString(){
        return groupName;
    }
}
