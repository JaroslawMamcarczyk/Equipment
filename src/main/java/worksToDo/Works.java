package worksToDo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table
public class Works implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String work;
    @Column
    private boolean isDone;
    @Column
    private LocalDate date;
    @Column
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description){this.description=description; }

    public String getDescription(){return description;}

    public Works(String work, boolean isDone, LocalDate date, String description) {
        this.work = work;
        this.isDone = isDone;
        this.date = date;
        this.description = description;
    }

    public  Works(){

    }
}
