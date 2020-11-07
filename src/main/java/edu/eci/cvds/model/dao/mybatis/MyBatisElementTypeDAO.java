package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementTypeDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ElementTypeMapper;
import edu.eci.cvds.model.entities.element.type.ElementType;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBatisElementTypeDAO implements ElementTypeDAO {

    @Inject
    ElementTypeMapper elementTypeMapper;

    @Override
    public void registerElementType(ElementTypeName name) throws PersistenceException {
        try {
            elementTypeMapper.registerElementType(name);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register element type.", e);
        }
    }

    @Override
    public ElementType loadElementTypeByName(ElementTypeName name) throws PersistenceException {
        try {
            return elementTypeMapper.loadElementTypeByName(name);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load element type by name.", e);
        }
    }
}
