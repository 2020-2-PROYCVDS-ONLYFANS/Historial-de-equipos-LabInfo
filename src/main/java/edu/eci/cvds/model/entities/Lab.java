package edu.eci.cvds.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Lab implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private List<Computer> computers;
    private boolean active;
    private Date openingDate;
    private Date closingDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    @Override
    public String toString() {
        return "Lab{" + "id=" + id + ", name='" + name + '\'' + ", computers=" + computers
                + ", active=" + active + ", openingDate=" + openingDate + ", closingDate="
                + closingDate + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lab lab = (Lab) o;
        return id == lab.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
