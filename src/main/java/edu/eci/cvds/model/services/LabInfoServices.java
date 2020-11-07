package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.element.ElementHistory;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;

import java.util.List;

@SuppressWarnings("unused")
public interface LabInfoServices {

    void registerElement(ElementTypeName name, String reference) throws LabInfoServicesException;

    void addElementHistoryById(long elementId, long userId, String title) throws LabInfoServicesException;

    void addElementHistoryWithDetailById(long elementId, long userId, String title, String detail) throws LabInfoServicesException;

    void addElementHistoryByReference(String reference, long userId, String title) throws LabInfoServicesException;

    void addElementHistoryByReferenceAndUsername(String reference, String username, String title) throws LabInfoServicesException;

    void addElementHistoryWithDetailByReference(String reference, long userId, String title, String detail) throws LabInfoServicesException;

    List<ElementHistory> loadElementsHistory() throws LabInfoServicesException;

    List<ElementHistory> loadElementHistoryById(long elementId) throws LabInfoServicesException;
}
