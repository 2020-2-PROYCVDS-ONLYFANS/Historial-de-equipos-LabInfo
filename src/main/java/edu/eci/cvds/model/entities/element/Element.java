package edu.eci.cvds.model.entities.element;

import edu.eci.cvds.model.entities.element.type.ElementType;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Element implements Serializable {

    private long id;
    private String reference;
    private ElementType type;
    private boolean available;
    private boolean discarded;

    public Element() {
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
        return "Element{" +
                "id=" + id +
                ", type=" + type +
                ", available=" + available +
                ", discarded=" + discarded +
                '}';
    }
}
