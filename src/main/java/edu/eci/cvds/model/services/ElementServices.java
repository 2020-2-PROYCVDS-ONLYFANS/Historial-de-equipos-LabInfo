package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;

public interface ElementServices {

    void registerElement(ElementTypeName typeName, String reference, Long userId)
            throws ServicesException;

    void registerElement(ElementTypeName typeName, String reference, String username)
            throws ServicesException;

    Element getElementByReference(String reference) throws ServicesException;

    void prepareElementToLinkToNewComputer(Long userId, Long elementId) throws ServicesException;

    Long getIdByReference(String reference) throws ServicesException;

    void setAvailableById(Long elementId, Boolean available) throws ServicesException;

    void discard(ElementTypeName typeName, Long userId, Long elementId, Long computerId)
            throws ServicesException;
}
