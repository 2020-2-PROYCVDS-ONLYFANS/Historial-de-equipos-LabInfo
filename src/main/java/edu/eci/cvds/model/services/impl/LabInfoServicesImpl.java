package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerDAO;
import edu.eci.cvds.model.dao.ComputerHistoryDAO;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.ElementHistoryDAO;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.ElementHistory;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LabInfoServicesImpl implements LabInfoServices {

    @Inject
    ElementDAO elementDAO;

    @Inject
    ElementHistoryDAO elementHistoryDAO;

    @Inject
    ComputerDAO computerDAO;

    @Inject
    ComputerHistoryDAO computerHistoryDAO;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(LabInfoServicesImpl.class);

    @Override
    public void registerElement(
            ElementTypeName name, String reference, String username) throws LabInfoServicesException {
        try {
            elementDAO.registerElement(name, reference);
            elementHistoryDAO.addElementHistoryByReferenceAndUsername(reference, username, "Registered");
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Element loadElementByReference(String reference) throws LabInfoServicesException {
        try {
            return elementDAO.loadElementByReference(reference);
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
    public void addElementHistoryWithDetailById(long elementId, long userId, String title, String detail)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailById(elementId, userId, title, detail);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryByReference(String reference, long userId, String title)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByReference(reference, userId, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryByReferenceAndUsername(String reference, String username, String title)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByReferenceAndUsername(reference, username, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailByReference(
            String reference, long userId, String title, String detail) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailByReference(reference, userId, title, detail);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateComputerCaseByReferenceAndUsername(
            String reference, String username, String newComputer) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addComputerCaseHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Associated", "Computer: " + newComputer);
            elementDAO.setAvailableByReference(reference, false);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMonitorByReferenceAndUsername(
            String reference, String username, String newComputer) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addMonitorHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Associated", "Computer: " + newComputer);
            elementDAO.setAvailableByReference(reference, false);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateKeyboardByReferenceAndUsername(
            String reference, String username, String newComputer) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addKeyboardHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Associated", "Computer: " + newComputer);
            elementDAO.setAvailableByReference(reference, false);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMouseByReferenceAndUsername(
            String reference, String username, String newComputer) throws LabInfoServicesException {
        try {
            elementHistoryDAO.addMouseHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Associated", "Computer: " + newComputer);
            elementDAO.setAvailableByReference(reference, false);
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

    @Override
    public void registerComputerWithReferences(String computer, String computerCase, String monitor, String keyboard,
                                               String mouse, boolean existsComputerCase, boolean existsMonitor,
                                               boolean existsKeyboard, boolean existsMouse, String username)
            throws LabInfoServicesException {
        try {
            if (!existsComputerCase) {
                registerElement(ElementTypeName.ETN_COMPUTER_CASE, computerCase, username);
            } associateComputerCaseByReferenceAndUsername(computerCase, username, computer);

            if (!existsMonitor) {
                registerElement(ElementTypeName.ETN_MONITOR, monitor, username);
            } associateMonitorByReferenceAndUsername(monitor, username, computer);

            if (!existsKeyboard) {
                registerElement(ElementTypeName.ETN_KEYBOARD, keyboard, username);
            } associateKeyboardByReferenceAndUsername(keyboard, username, computer);

            if (!existsMouse) {
                registerElement(ElementTypeName.ETN_MOUSE, mouse, username);
            } associateMouseByReferenceAndUsername(mouse, username, computer);

            computerDAO.registerComputerWithReferences(
                    computer, computerCase, monitor, keyboard, mouse);
            computerHistoryDAO.addComputerHistoryByReferenceAndUsername(
                    computer, username, "Registered");
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }
}
