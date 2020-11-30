package edu.eci.cvds.model.entities.element.type;

import java.io.Serializable;
import java.util.Objects;

public class ElementType implements Serializable {

    private static final long serialVersionUID = 1L;

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
        return "ElementType{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ElementType type = (ElementType) o;
        return id == type.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
