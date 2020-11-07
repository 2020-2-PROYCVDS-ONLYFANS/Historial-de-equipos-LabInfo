package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.element.type.ElementType;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;

@SuppressWarnings("unused")
public interface ElementTypeDAO {

    void registerElementType(ElementTypeName name) throws PersistenceException;

    ElementType loadElementTypeByName(ElementTypeName name) throws PersistenceException;
}
