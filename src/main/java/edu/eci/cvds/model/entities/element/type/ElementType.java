package edu.eci.cvds.model.entities.element.type;

import java.io.Serializable;

public class ElementType implements Serializable {

    private long id;
    private ElementTypeName name;

    public ElementType() {
    }

    public ElementType(ElementTypeName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ElementTypeName getName() {
        return name;
    }

    public void setName(ElementTypeName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ElementType{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
