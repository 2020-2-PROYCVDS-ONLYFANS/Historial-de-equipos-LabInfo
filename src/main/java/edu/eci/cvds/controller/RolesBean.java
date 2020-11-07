package edu.eci.cvds.controller;

import edu.eci.cvds.model.entities.role.RoleName;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "rolesBean", eager = true)
@RequestScoped
public class RolesBean extends BasePageBean implements Serializable {

    private final RoleName roleUser = RoleName.ROLE_USER;
    private final RoleName roleAdmin = RoleName.ROLE_ADMIN;

    public RoleName getRoleUser() {
        return roleUser;
    }

    public RoleName getRoleAdmin() {
        return roleAdmin;
    }
}
