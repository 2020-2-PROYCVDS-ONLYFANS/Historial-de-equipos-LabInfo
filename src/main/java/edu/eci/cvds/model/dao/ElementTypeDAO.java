package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ElementTypeDAO {

    long getElementTypeIdByName(ElementTypeName name) throws PersistenceException;
}
