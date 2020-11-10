package edu.eci.cvds.model.entities.element;

import edu.eci.cvds.model.entities.computer.Computer;
import edu.eci.cvds.model.entities.User;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class ElementHistory implements Serializable {

    private long id;
    private Element element;
    private Computer computer;
    private User user;
    private Date timestamp;
    private String title;
    private String detail;

    public ElementHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Computer getTeam() {
        return computer;
    }

    public void setTeam(Computer computer) {
        this.computer = computer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "ElementHistory{" +
                "id=" + id +
                ", element=" + element +
                ", team=" + computer +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
