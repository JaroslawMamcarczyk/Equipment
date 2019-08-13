package product;

import policeman.Worker;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class ComputerDetails implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false,unique = true)
    private int id;
    @Column
    private ComputerType type;
    @Column
    private String ip;
    @Column
    private String mask;
    @Column
    private String gateway;
    @Column
    private String socket;
    @OneToOne(mappedBy = "computerDetails")
    private Product product;
    @OneToOne
    @JoinColumn
    private Worker worker;

    public enum ComputerType {
        PLATFORMA,KSIP,SNP,ODN,MASZYNA,OPERACYJNY;
    }
}
