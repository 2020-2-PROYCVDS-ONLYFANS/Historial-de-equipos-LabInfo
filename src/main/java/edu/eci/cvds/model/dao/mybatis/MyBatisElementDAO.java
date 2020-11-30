package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ElementMapper;
import edu.eci.cvds.model.entities.element.Element;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisElementDAO implements ElementDAO {

    @Inject
    private ElementMapper elementMapper;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(MyBatisElementDAO.class);

    @Override
    public void registerElement(String reference, Long typeId) throws PersistenceException {
        LOGGER.info("registerElement");
        try {
            LOGGER.info("registerElement - try");
            elementMapper.registerElement(reference, typeId);
        } catch (PersistenceException e) {
            LOGGER.info("registerElement - catch");
            throw new PersistenceException("Fail to register element.", e);
        }
    }

    @Override
    public Long getIdByReference(String reference) throws PersistenceException {
        LOGGER.info("loadIdByReference");
        try {
            LOGGER.info("loadIdByReference - try");
            return elementMapper.getIdByReference(reference);
        } catch (PersistenceException e) {
            LOGGER.info("loadIdByReference - catch");
            throw new PersistenceException("Fail to load id by reference.", e);
        }
    }

    public Element getElementById(Long id) throws PersistenceException {
        LOGGER.info("loadElementById");
        try {
            LOGGER.info("loadElementById - try");
            return elementMapper.getElementById(id);
        } catch (PersistenceException e) {
            LOGGER.info("loadElementById - catch");
            throw new PersistenceException("Fail to load element by id.", e);
        }
    }

    @Override
    public Element getElementByReference(String reference) throws PersistenceException {
        LOGGER.info("loadElementByReference");
        try {
            LOGGER.info("loadElementByReference - try");
            return elementMapper.getElementByReference(reference);
        } catch (PersistenceException e) {
            LOGGER.info("loadElementByReference - catch");
            throw new PersistenceException("Fail to load element by reference.", e);
        }
    }

    @Override
    public void setAvailableById(Long id, Boolean available) throws PersistenceException {
        LOGGER.info("setAvailableByReference");
        try {
            LOGGER.info("setAvailableByReference - try");
            elementMapper.setAvailableById(id, available);
        } catch (PersistenceException e) {
            LOGGER.info("setAvailableByReference - catch");
            throw new PersistenceException("Fail to set available by reference.", e);
        }
    }

    public void setDiscardedById(Long id, Boolean discarded) throws PersistenceException {
        LOGGER.info("setDiscardedById");
        try {
            LOGGER.info("setDiscardedById - try");
            elementMapper.setDiscardedById(id, discarded);
        } catch (PersistenceException e) {
            LOGGER.info("setDiscardedById - catch");
            throw new PersistenceException("Fail to set discarded by id.", e);
        }
    }
}
