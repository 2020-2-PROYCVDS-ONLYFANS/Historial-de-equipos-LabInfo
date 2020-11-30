package edu.eci.cvds.controller;

import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.entities.role.RoleName;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "enumsBean", eager = true)
@RequestScoped
@SuppressWarnings("deprecation")
public class EnumsBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private RoleName roleUser;
    private RoleName roleAdmin;

    private ElementTypeName computerCase;
    private ElementTypeName monitor;
    private ElementTypeName keyboard;
    private ElementTypeName mouse;

    @PostConstruct
    public void init() {
        roleUser = RoleName.ROLE_USER;
        roleAdmin = RoleName.ROLE_ADMIN;

        computerCase = ElementTypeName.ETN_COMPUTER_CASE;
        monitor = ElementTypeName.ETN_MONITOR;
        keyboard = ElementTypeName.ETN_KEYBOARD;
        mouse = ElementTypeName.ETN_MOUSE;
    }

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
