package edu.eci.cvds.model.entities.team;

import edu.eci.cvds.model.entities.element.Element;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Team implements Serializable {

    private long id;
    private Element computerCase;
    private Element monitor;
    private Element keyboard;
    private Element mouse;

    public Team() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Element getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(Element computerCase) {
        this.computerCase = computerCase;
    }

    public Element getMonitor() {
        return monitor;
    }

    public void setMonitor(Element monitor) {
        this.monitor = monitor;
    }

    public Element getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Element keyboard) {
        this.keyboard = keyboard;
    }

    public Element getMouse() {
        return mouse;
    }

    public void setMouse(Element mouse) {
        this.mouse = mouse;
    }
}
