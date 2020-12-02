package edu.eci.cvds.model.entities.element;

import edu.eci.cvds.model.entities.element.type.ElementType;

import java.io.Serializable;
import java.util.Objects;

public class Element implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String reference;
    private ElementType type;
    private boolean available;
    private boolean discarded;

    public Element() {
    }

    public Element(Element another) {
        this.id = another.id;
        this.reference = another.reference;
        this.type = another.type;
        this.available = another.available;
        this.discarded = another.discarded;
    }

    public Element(ElementType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    @Override
    public String toString() {
        return "Element{" + "id=" + id + ", reference=" + reference + ", type=" + type
                + ", available=" + available + ", discarded=" + discarded + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Element element = (Element) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
