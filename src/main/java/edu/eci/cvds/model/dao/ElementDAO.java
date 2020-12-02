package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.element.Element;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

@SuppressWarnings("java:S1130")
public interface ElementDAO {

    void registerElement(String reference, Long typeId) throws PersistenceException;

    Long getIdByReference(String reference) throws PersistenceException;

    Element getElementById(Long id) throws PersistenceException;

    Element getElementByReference(String reference) throws PersistenceException;

    List<Element> getActiveElements() throws PersistenceException;

    void setAvailableById(Long id, Boolean available) throws PersistenceException;

    void setDiscardedById(Long id, Boolean discarded) throws PersistenceException;
}
