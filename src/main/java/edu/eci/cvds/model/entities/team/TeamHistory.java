package edu.eci.cvds.model.entities.team;

import edu.eci.cvds.model.entities.User;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("unused")
public class TeamHistory implements Serializable {

    private long id;
    private Team team;
    private User user;
    private Date timestamp;
    private String title;
    private String detail;

    public TeamHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
        return "TeamHistory{" +
                "id=" + id +
                ", team=" + team +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
