package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ElementMapper;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBatisElementDAO implements ElementDAO {
    
    @Inject
    ElementMapper elementMapper;
    
    @Override
    public void registerElement(ElementTypeName name, String reference)
            throws PersistenceException {
        try {
            elementMapper.registerElement(name, reference);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register element.", e);
        }
    }

    @Override
    public Element loadElementById(long id)
            throws PersistenceException {
        try {
            return elementMapper.loadElementById(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load element by id.", e);
        }
    }

    @Override
    public Element loadElementByReference(String reference)
            throws PersistenceException {
        try {
            return elementMapper.loadElementByReference(reference);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load element by reference.", e);
        }
    }

    @Override
    public void setAvailableByReference(String reference, boolean available)
            throws PersistenceException {
        try {
            elementMapper.setAvailableByReference(reference, available);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to set available by reference.", e);
        }
    }
}
