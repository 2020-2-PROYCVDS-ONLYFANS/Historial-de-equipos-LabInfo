package edu.eci.cvds.model.entities;

import edu.eci.cvds.model.entities.element.Element;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Novelty implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private User user;
    private Element element;
    private Computer computer;
    private Lab lab;
    private Date timestamp;
    private String title;
    private String detail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Novelty{" + "id=" + id + ", user=" + user + ", element=" + element + ", computer="
                + computer + ", lab=" + lab + ", timestamp=" + timestamp + ", title='" + title
                + '\'' + ", detail='" + detail + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Novelty novelty = (Novelty) o;
        return id == novelty.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
