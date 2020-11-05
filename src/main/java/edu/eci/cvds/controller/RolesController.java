package edu.eci.cvds.controller;

import edu.eci.cvds.model.dao.auth.AuthDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "rolesController", eager = true)
@RequestScoped
public class RolesController {

    private final String roleUser = AuthDAO.getRoleUser();
    private final String roleAdmin = AuthDAO.getRoleAdmin();

    public String getRoleUser() {
        return roleUser;
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }
}
