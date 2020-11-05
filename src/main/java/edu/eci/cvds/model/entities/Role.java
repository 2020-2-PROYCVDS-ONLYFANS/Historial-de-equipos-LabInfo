package edu.eci.cvds.model.entities;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Role implements Serializable {

    private long id;
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + roleName +
                '}';
    }
}
