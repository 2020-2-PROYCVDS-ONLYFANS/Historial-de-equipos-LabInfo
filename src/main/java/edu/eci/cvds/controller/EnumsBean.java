package edu.eci.cvds.controller;

import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.entities.role.RoleName;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "enumsBean", eager = true)
@RequestScoped
public class EnumsBean extends BasePageBean {

    private final RoleName roleUser = RoleName.ROLE_USER;
    private final RoleName roleAdmin = RoleName.ROLE_ADMIN;

    private ElementTypeName computerCase = ElementTypeName.ETN_COMPUTER_CASE;
    private ElementTypeName monitor = ElementTypeName.ETN_MONITOR;
    private ElementTypeName keyboard = ElementTypeName.ETN_KEYBOARD;
    private ElementTypeName mouse = ElementTypeName.ETN_MOUSE;

    public RoleName getRoleUser() {
        return roleUser;
    }

    public RoleName getRoleAdmin() {
        return roleAdmin;
    }

    public ElementTypeName getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(ElementTypeName computerCase) {
        this.computerCase = computerCase;
    }

    public ElementTypeName getMonitor() {
        return monitor;
    }

    public void setMonitor(ElementTypeName monitor) {
        this.monitor = monitor;
    }

    public ElementTypeName getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(ElementTypeName keyboard) {
        this.keyboard = keyboard;
    }

    public ElementTypeName getMouse() {
        return mouse;
    }

    public void setMouse(ElementTypeName mouse) {
        this.mouse = mouse;
    }
}
