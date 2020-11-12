package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementHistoryDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ElementHistoryMapper;
import edu.eci.cvds.model.entities.element.ElementHistory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyBatisElementHistoryDAO implements ElementHistoryDAO {

    @Inject
    ElementHistoryMapper elementHistoryMapper;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(MyBatisElementHistoryDAO.class);

    @Override
    public void addElementHistoryByIdAndUsername(
            long elementId, String username, String title)
            throws PersistenceException {
        try {
            LOGGER.info("elementId = " + elementId);
            LOGGER.info("username = " + username);
            LOGGER.info("title = " + title);
            elementHistoryMapper
                    .addElementHistoryByIdAndUsername(
                            elementId, username, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add element history by id and username.", e);
        }
    }

    @Override
    public void addElementHistoryWithDetailById(long elementId, long userId, String title, String detail)
            throws PersistenceException {
        try {
            elementHistoryMapper.addElementHistoryWithDetailById(elementId, userId, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add element history with detail by id.", e);
        }
    }

    @Override
    public void addElementHistoryByReference(String reference, long userId, String title)
            throws PersistenceException {
        try {
            elementHistoryMapper.addElementHistoryByReference(reference, userId, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add element history by reference.", e);
        }
    }

    @Override
    public void addElementHistoryByReferenceAndUsername(String reference, String username, String title)
            throws PersistenceException {
        try {
            elementHistoryMapper.addElementHistoryByReferenceAndUsername(reference, username, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add element history by reference and username", e);
        }
    }

    @Override
    public void addElementHistoryWithDetailByReference(String reference, long userId, String title, String detail)
            throws PersistenceException {
        try {
            elementHistoryMapper.addElementHistoryWithDetailByReference(reference, userId, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add element history with detail by reference.", e);
        }
    }

    @Override
    public void addComputerCaseHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException {
        try {
            elementHistoryMapper.addComputerCaseHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer case history with detail by reference and username.", e);
        }
    }

    @Override
    public void addMonitorHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException {
        try {
            elementHistoryMapper.addMonitorHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer case history with detail by reference and username.", e);
        }
    }

    @Override
    public void addKeyboardHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException {
        try {
            elementHistoryMapper.addKeyboardHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer case history with detail by reference and username.", e);
        }
    }

    @Override
    public void addMouseHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException {
        try {
            elementHistoryMapper.addMouseHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer case history with detail by reference and username.", e);
        }
    }

    @Override
    public List<ElementHistory> loadElementsHistory() throws PersistenceException {
        try {
            return elementHistoryMapper.loadElementsHistory();
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load elements history.", e);
        }
    }

    @Override
    public List<ElementHistory> loadElementHistoryById(long elementId) throws PersistenceException {
        try {
            return elementHistoryMapper.loadElementHistoryById(elementId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load element history by id.", e);
        }
    }
}
