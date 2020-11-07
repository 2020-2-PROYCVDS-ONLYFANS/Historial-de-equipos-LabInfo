package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;

@SuppressWarnings("unused")
public interface ElementDAO {

    void registerElement(ElementTypeName name, String reference) throws PersistenceException;

    Element loadElementById(long id) throws PersistenceException;
}
