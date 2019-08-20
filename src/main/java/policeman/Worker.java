package policeman;

import product.ComputerDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Worker implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(unique = true, nullable = false)
    private int id;
@Column
    private String name;
@Column
    private String surrname;
@Column
    private String ewidential;
@Column
    private String pesel;
@ManyToOne
private Range policemanRange;
@ManyToOne
private Department policemanDepartment;
@ManyToOne(fetch = FetchType.EAGER)
private Rank policemanRank;
@OneToOne(mappedBy = "worker")
private ComputerDetails computer;
@Column
private  boolean policemanIntranet;
@Column
private  boolean policemanIntradok;
@Column
private  boolean policemanLotus;
@Column
private  boolean policemanExchange;
@Column
private  boolean policemanCryptomail;
@Column
private boolean policemanSWD;
@Column
private boolean isActiv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public String getEwidential() {
        return ewidential;
    }

    public void setEwidential(String ewidential) {
        this.ewidential = ewidential;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Range getPolicemanRange() {
        return policemanRange;
    }

    public void setPolicemanRange(Range policemanRange) {
        this.policemanRange = policemanRange;
    }

    public Department getPolicemanDepartment() {
        return policemanDepartment;
    }

    public void setPolicemanDepartment(Department policemanDepartment) {
        this.policemanDepartment = policemanDepartment;
    }

    public Rank getPolicemanRank() {
        return policemanRank;
    }

    public void setPolicemanRank(Rank policemanRank) {
        this.policemanRank = policemanRank;
    }

    public boolean isPolicemanIntranet() {
        return policemanIntranet;
    }

    public void setPolicemanIntranet(boolean policemanIntranet) {
        this.policemanIntranet = policemanIntranet;
    }

    public boolean isPolicemanIntradok() {
        return policemanIntradok;
    }

    public void setPolicemanIntradok(boolean policemanIntradok) {
        this.policemanIntradok = policemanIntradok;
    }

    public boolean isPolicemanLotus() {
        return policemanLotus;
    }

    public void setPolicemanLotus(boolean policemanLotus) {
        this.policemanLotus = policemanLotus;
    }

    public boolean isPolicemanExchange() {
        return policemanExchange;
    }

    public void setPolicemanExchange(boolean policemanExchange) {
        this.policemanExchange = policemanExchange;
    }

    public boolean isPolicemanCryptomail() {
        return policemanCryptomail;
    }

    public void setPolicemanCryptomail(boolean policemanCryptomail) {
        this.policemanCryptomail = policemanCryptomail;
    }

    public boolean isPolicemanSWD() {
        return policemanSWD;
    }

    public void setPolicemanSWD(boolean policemanSWD) {
        this.policemanSWD = policemanSWD;
    }

    public ComputerDetails getComputer() {
        return computer;
    }

    public void setComputer(ComputerDetails computer) {
        this.computer = computer;
    }

    public boolean isActiv() {
        return isActiv;
    }

    public void setActiv(boolean activ) {
        isActiv = activ;
    }

    public Worker(String name, String surrname, String ewidential, String pesel, Range policemanRange, Department policemanDepartment, Rank policemanRank, boolean policemanIntranet, boolean policemanIntradok, boolean policemanLotus, boolean policemanExchange, boolean policemanCryptomail, boolean policemanSWD, boolean isActiv) {
        this.name = name;
        this.surrname = surrname;
        this.ewidential = ewidential;
        this.pesel = pesel;
        this.policemanRange = policemanRange;
        this.policemanDepartment = policemanDepartment;
        this.policemanRank = policemanRank;
        this.policemanIntranet = policemanIntranet;
        this.policemanIntradok = policemanIntradok;
        this.policemanLotus = policemanLotus;
        this.policemanExchange = policemanExchange;
        this.policemanCryptomail = policemanCryptomail;
        this.policemanSWD = policemanSWD;
        this.isActiv = isActiv;
    }

    public Worker(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return getId() == worker.getId() &&
                isPolicemanIntranet() == worker.isPolicemanIntranet() &&
                isPolicemanIntradok() == worker.isPolicemanIntradok() &&
                isPolicemanLotus() == worker.isPolicemanLotus() &&
                isPolicemanExchange() == worker.isPolicemanExchange() &&
                isPolicemanCryptomail() == worker.isPolicemanCryptomail() &&
                isPolicemanSWD() == worker.isPolicemanSWD() &&
                getName().equals(worker.getName()) &&
                getSurrname().equals(worker.getSurrname()) &&
                Objects.equals(getEwidential(), worker.getEwidential()) &&
                Objects.equals(getPesel(), worker.getPesel())&&
                isActiv()==worker.isActiv();
    }

    public Worker(String name, String surrname, String ewidential, String pesel) {
        this.name = name;
        this.surrname = surrname;
        this.ewidential = ewidential;
        this.pesel = pesel;
        this.isActiv = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurrname(), getEwidential(), getPesel(), getPolicemanRange(), getPolicemanDepartment(), getPolicemanRank(), isPolicemanIntranet(), isPolicemanIntradok(), isPolicemanLotus(), isPolicemanExchange(), isPolicemanCryptomail(), isPolicemanSWD(), isActiv());
    }
}
