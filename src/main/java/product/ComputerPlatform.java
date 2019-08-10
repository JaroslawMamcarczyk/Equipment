package product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class ComputerPlatform implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private int id;
    @Column
    private String ip;
    @Column
    private String mask;
    @Column
    private String gateway;
    @Column
    private String user;
    @Column
    private String socket;
    @OneToOne(mappedBy = "computerPlatform")
    private Product product;
}
