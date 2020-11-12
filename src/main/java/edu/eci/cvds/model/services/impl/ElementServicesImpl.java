package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.ElementHistoryDAO;
import edu.eci.cvds.model.entities.computer.Computer;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.ElementHistory;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ElementServicesImpl implements ElementServices {

    @Inject
    ElementDAO elementDAO;

    @Inject
    ElementHistoryDAO elementHistoryDAO;

    @Inject
    ComputerServices computerServices;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(ElementServicesImpl.class);

    @Override
    public void registerElement(
            ElementTypeName name, String reference, String username)
            throws LabInfoServicesException {
        try {
            elementDAO.registerElement(name, reference);
            elementHistoryDAO.addElementHistoryByReferenceAndUsername(
                    reference, username, "Registered");
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Element loadElementByReference(String reference)
            throws LabInfoServicesException {
        try {
            return elementDAO.loadElementByReference(reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryByIdAndUsername(long elementId, String username, String title)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByIdAndUsername(elementId, username, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailById(
            long elementId, long userId, String title, String detail)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailById(
                    elementId, userId, title, detail);
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
    public void addElementHistoryByReferenceAndUsername(
            String reference, String username, String title)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryByReferenceAndUsername(
                    reference, username, title);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailByReference(
            String reference, long userId, String title, String detail)
            throws LabInfoServicesException {
        try {
            elementHistoryDAO.addElementHistoryWithDetailByReference(
                    reference, userId, title, detail);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addElementHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail, ElementTypeName type)
            throws LabInfoServicesException {
        LOGGER.info("addElementHistoryWithDetailByReferenceAndUsername");
        try {
            switch (type) {
                case ETN_COMPUTER_CASE:
                    LOGGER.info("ETN_COMPUTER_CASE");
                    elementHistoryDAO
                            .addComputerCaseHistoryWithDetailByReferenceAndUsername(
                                    reference, username, title, detail);
                    break;

                case ETN_MONITOR:
                    LOGGER.info("ETN_MONITOR");
                    elementHistoryDAO
                            .addMonitorHistoryWithDetailByReferenceAndUsername(
                                    reference, username, title, detail);
                    break;

                case ETN_KEYBOARD:
                    LOGGER.info("ETN_KEYBOARD");
                    elementHistoryDAO
                            .addKeyboardHistoryWithDetailByReferenceAndUsername(
                                    reference, username, title, detail);
                    break;

                case ETN_MOUSE:
                    LOGGER.info("ETN_MOUSE");
                    elementHistoryDAO
                            .addMouseHistoryWithDetailByReferenceAndUsername(
                                    reference, username, title, detail);
                    break;
            }
        } catch (PersistenceException e) {
            LOGGER.info("addElementHistoryWithDetailByReferenceAndUsername - catch");
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateComputerCaseByReferenceAndUsername(
            String reference, String username, Computer newComputer)
            throws LabInfoServicesException {
        try {
            if (newComputer.getComputerCase() != null) {
                elementDAO.setAvailableByReference(
                        newComputer.getComputerCase().getReference(), true);
                elementHistoryDAO.addComputerCaseHistoryWithDetailByReferenceAndUsername(
                        newComputer.getComputerCase().getReference(), username,
                        "Unlinked", "Computer: " + newComputer.getReference());
                computerServices.addComputerHistoryByReferenceAndUsername(
                        newComputer.getReference(), username,
                        "Computer case unlinked", "Computer case: " + reference);
            }

            elementDAO.setAvailableByReference(reference, false);
            elementHistoryDAO.addComputerCaseHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Linked", "Computer: " + newComputer.getReference());

            computerServices.associateComputerCaseByReference(newComputer.getReference(), reference);
            computerServices.addComputerHistoryByReferenceAndUsername(
                    newComputer.getReference(), username,
                    "Computer case linked", "Computer case: " + reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMonitorByReferenceAndUsername(
            String reference, String username, Computer newComputer)
            throws LabInfoServicesException {
        try {
            if (newComputer.getMonitor() != null) {
                elementDAO.setAvailableByReference(
                        newComputer.getMonitor().getReference(), true);
                elementHistoryDAO.addMonitorHistoryWithDetailByReferenceAndUsername(
                        newComputer.getMonitor().getReference(), username,
                        "Unlinked", "Computer: " + newComputer.getReference());
                computerServices.addComputerHistoryByReferenceAndUsername(
                        newComputer.getReference(), username,
                        "Monitor linked", "Monitor: " + reference);
            }

            elementDAO.setAvailableByReference(reference, false);
            elementHistoryDAO.addMonitorHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Linked", "Computer: " + newComputer.getReference());

            computerServices.associateMonitorByReference(newComputer.getReference(), reference);
            computerServices.addComputerHistoryByReferenceAndUsername(
                    newComputer.getReference(), username,
                    "Monitor linked", "Monitor: " + reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateKeyboardByReferenceAndUsername(
            String reference, String username, Computer newComputer)
            throws LabInfoServicesException {
        try {
            if (newComputer.getKeyboard() != null) {
                elementDAO.setAvailableByReference(
                        newComputer.getKeyboard().getReference(), true);
                elementHistoryDAO.addKeyboardHistoryWithDetailByReferenceAndUsername(
                        newComputer.getKeyboard().getReference(), username,
                        "Unlinked", "Computer: " + newComputer.getReference());
                computerServices.addComputerHistoryByReferenceAndUsername(
                        newComputer.getReference(), username,
                        "Keyboard linked", "Keyboard: " + reference);
            }

            elementDAO.setAvailableByReference(reference, false);
            elementHistoryDAO.addKeyboardHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Linked", "Computer: " + newComputer.getReference());

            computerServices.associateKeyboardByReference(newComputer.getReference(), reference);
            computerServices.addComputerHistoryByReferenceAndUsername(
                    newComputer.getReference(), username,
                    "Keyboard linked", "Keyboard: " + reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMouseByReferenceAndUsername(
            String reference, String username, Computer newComputer)
            throws LabInfoServicesException {
        try {
            if (newComputer.getMouse() != null) {
                elementDAO.setAvailableByReference(
                        newComputer.getMouse().getReference(), true);
                elementHistoryDAO.addMouseHistoryWithDetailByReferenceAndUsername(
                        newComputer.getMouse().getReference(), username,
                        "Unlinked", "Computer: " + newComputer.getReference());
            }

            elementDAO.setAvailableByReference(reference, false);
            elementHistoryDAO.addMouseHistoryWithDetailByReferenceAndUsername(
                    reference, username, "Linked", "Computer: " + newComputer.getReference());

            computerServices.associateMouseByReference(newComputer.getReference(), reference);
            computerServices.addComputerHistoryByReferenceAndUsername(
                    newComputer.getReference(), username,
                    "Mouse linked", "Mouse: " + reference);
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
    public List<ElementHistory> loadElementHistoryById(long elementId)
            throws LabInfoServicesException {
        try {
            return elementHistoryDAO.loadElementHistoryById(elementId);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void discardElement(long elementId, String username) throws LabInfoServicesException {
        try {
            elementDAO.setDiscardedById(elementId, true);
            elementHistoryDAO.addElementHistoryByIdAndUsername(elementId, username, "Discarded");
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }
}
