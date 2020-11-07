package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.ElementHistoryDAO;
import edu.eci.cvds.model.entities.element.ElementHistory;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public class LabInfoServicesImpl implements LabInfoServices {

    @Inject
    ElementDAO elementDAO;

    @Inject
    ElementHistoryDAO elementHistoryDAO;

    @Override
    public void registerElement(ElementTypeName name, String reference) throws LabInfoServicesException {
        try {
            elementDAO.registerElement(name, reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryById(long elementId, long userId, String title) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryById(elementId, userId, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailById(long elementId, long userId, String title, String detail) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailById(elementId, userId, title, detail);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryByReference(String reference, long userId, String title) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByReference(reference, userId, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryByReferenceAndUsername(String reference, String username, String title) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByReferenceAndUsername(reference, username, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailByReference(String reference, long userId, String title, String detail) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailByReference(reference, userId, title, detail);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public List<ElementHistory> loadElementsHistory() throws LabInfoServicesException {
        try {
            return elementHistoryDAO.loadElementsHistory();
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public List<ElementHistory> loadElementHistoryById(long elementId) throws LabInfoServicesException {
        try {
            return elementHistoryDAO.loadElementHistoryById(elementId);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }
}
