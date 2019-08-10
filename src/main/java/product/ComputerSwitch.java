package product;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table
public class ComputerSwitch implements Serializable {
@GeneratedValue
@Id
@Column(nullable = false,unique = true)
private int id;
@OneToOne(mappedBy = "computerSwitch")
    private Product product;
}
