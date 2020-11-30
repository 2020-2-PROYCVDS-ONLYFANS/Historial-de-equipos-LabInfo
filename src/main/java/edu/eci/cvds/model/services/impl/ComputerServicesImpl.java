package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.*;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerServicesImpl implements ComputerServices {

    @Inject
    private ElementServices elementServices;

    @Inject
    private ComputerDAO computerDAO;

    @Inject
    private NoveltyServices noveltyServices;

    @Inject
    private AuthServices authServices;

    @Inject
    private LabServices labServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(ComputerServicesImpl.class);

    @Override
    public void registerComputerByReferences(String username, String reference,
            Pair<Boolean, String> computerCasePair, Pair<Boolean, String> monitorPair,
            Pair<Boolean, String> keyboardPair, Pair<Boolean, String> mousePair)
            throws ServicesException {
        try {
            LOGGER.info("registerComputerByReferences - try");
            if (!computerCasePair.getValue0()) {
                elementServices.registerElement(ElementTypeName.ETN_COMPUTER_CASE,
                        computerCasePair.getValue1(), username);
            }
            if (!monitorPair.getValue0()) {
                elementServices.registerElement(ElementTypeName.ETN_MONITOR,
                        monitorPair.getValue1(), username);
            }
            if (!keyboardPair.getValue0()) {
                elementServices.registerElement(ElementTypeName.ETN_KEYBOARD,
                        keyboardPair.getValue1(), username);
            }
            if (!mousePair.getValue0()) {
                elementServices.registerElement(ElementTypeName.ETN_MOUSE, mousePair.getValue1(),
                        username);
            }

            Long userId = authServices.getUserIdByUsername(username);
            Long computerCaseId = elementServices.getIdByReference(computerCasePair.getValue1());
            Long monitorId = elementServices.getIdByReference(monitorPair.getValue1());
            Long keyboardId = elementServices.getIdByReference(keyboardPair.getValue1());
            Long mouseId = elementServices.getIdByReference(mousePair.getValue1());

            elementServices.prepareElementToLinkToNewComputer(userId, computerCaseId);
            elementServices.prepareElementToLinkToNewComputer(userId, monitorId);
            elementServices.prepareElementToLinkToNewComputer(userId, keyboardId);
            elementServices.prepareElementToLinkToNewComputer(userId, mouseId);

            computerDAO.registerComputer(reference, computerCaseId, monitorId, keyboardId, mouseId);
            noveltyServices.create(userId, null, getIdByReference(reference), null, "Registered",
                    null);
        } catch (PersistenceException e) {
            LOGGER.info("registerComputerByReferences - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByReference(String reference) throws ServicesException {
        try {
            LOGGER.info("getIdByReference - try");
            return computerDAO.getIdByReference(reference);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByReference - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByComputerCaseId(Long computerCaseId) throws ServicesException {
        try {
            return computerDAO.getIdByComputerCaseId(computerCaseId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByMonitorId(Long monitorId) throws ServicesException {
        try {
            return computerDAO.getIdByMonitorId(monitorId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByKeyboardId(Long keyboardId) throws ServicesException {
        try {
            return computerDAO.getIdByKeyboardId(keyboardId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByMouseId(Long mouseId) throws ServicesException {
        try {
            return computerDAO.getIdByMouseId(mouseId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Computer getComputerByReference(String reference) throws ServicesException {
        try {
            return computerDAO.getComputerByReference(reference);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void linkElementByIdsAndComputer(ElementTypeName typeName, Long userId, Long elementId,
            Computer computer) throws ServicesException {
        unlinkElement(typeName, userId, computer.getElement(typeName).getId(), computer.getId());

        elementServices.setAvailableById(elementId, false);
        setElementIdByIds(typeName, userId, elementId, computer);
    }

    @Override
    public void setElementIdByIds(ElementTypeName typeName, Long userId, Long elementId,
            Computer computer) throws ServicesException {
        switch (typeName) {
            case ETN_COMPUTER_CASE:
                setComputerCaseIdByIds(userId, elementId, computer.getId());
                break;

            case ETN_MONITOR:
                setMonitorIdByIds(userId, elementId, computer.getId());
                break;

            case ETN_KEYBOARD:
                setKeyboardIdByIds(userId, elementId, computer.getId());
                break;

            case ETN_MOUSE:
                setMouseIdByIds(userId, elementId, computer.getId());
                break;
        }
    }

    @Override
    public void setComputerCaseIdByIds(Long userId, Long computerCaseId, Long computerId)
            throws ServicesException {
        try {
            computerDAO.setComputerCaseIdByIds(computerId, computerCaseId);

            noveltyServices.create(userId, computerCaseId, computerId,
                    labServices.getLabIdByLinkedComputerId(computerId), "Linked computer case",
                    null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void setMonitorIdByIds(Long userId, Long monitorId, Long computerId)
            throws ServicesException {
        try {
            computerDAO.setMonitorIdByIds(computerId, monitorId);

            noveltyServices.create(userId, monitorId, computerId,
                    labServices.getLabIdByLinkedComputerId(computerId), "Linked monitor", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void setKeyboardIdByIds(Long userId, Long keyboardId, Long computerId)
            throws ServicesException {
        try {
            computerDAO.setKeyboardIdByIds(computerId, keyboardId);

            noveltyServices.create(userId, keyboardId, computerId,
                    labServices.getLabIdByLinkedComputerId(computerId), "Linked keyboard", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void setMouseIdByIds(Long userId, Long mouseId, Long computerId)
            throws ServicesException {
        try {
            computerDAO.setMouseIdByIds(computerId, mouseId);

            noveltyServices.create(userId, mouseId, computerId,
                    labServices.getLabIdByLinkedComputerId(computerId), "Linked mouse", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void unlinkElement(ElementTypeName typeName, Long userId, Long elementId,
            Long computerId) throws ServicesException {
        elementServices.setAvailableById(elementId, true);
        switch (typeName) {
            case ETN_COMPUTER_CASE:
                noveltyServices.create(userId, elementId, computerId,
                        labServices.getLabIdByLinkedComputerId(computerId),
                        "Unlinked computer case", null);
                break;

            case ETN_MONITOR:
                noveltyServices.create(userId, elementId, computerId,
                        labServices.getLabIdByLinkedComputerId(computerId), "Unlinked monitor",
                        null);
                break;

            case ETN_KEYBOARD:
                noveltyServices.create(userId, elementId, computerId,
                        labServices.getLabIdByLinkedComputerId(computerId), "Unlinked keyboard",
                        null);
                break;

            case ETN_MOUSE:
                noveltyServices.create(userId, elementId, computerId,
                        labServices.getLabIdByLinkedComputerId(computerId), "Unlinked mouse", null);
                break;
        }
    }

    @Override
    public void setDiscardedAndAvailableById(Long id, Boolean discarded, Boolean available)
            throws ServicesException {
        try {
            computerDAO.setDiscardedAndAvailableById(id, discarded, available);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void discard(Long userId, Computer computer, boolean discardComputerCase,
            boolean discardMonitor, boolean discardKeyboard, boolean discardMouse)
            throws ServicesException {
        try {
            if (discardComputerCase) {
                elementServices.discard(ElementTypeName.ETN_COMPUTER_CASE, userId,
                        computer.getComputerCase().getId(), computer.getId());
            } else {
                unlinkElement(ElementTypeName.ETN_COMPUTER_CASE, userId,
                        computer.getComputerCase().getId(), computer.getId());
            }

            if (discardMonitor) {
                elementServices.discard(ElementTypeName.ETN_MONITOR, userId,
                        computer.getMonitor().getId(), computer.getId());
            } else {
                unlinkElement(ElementTypeName.ETN_MONITOR, userId, computer.getMonitor().getId(),
                        computer.getId());
            }

            if (discardKeyboard) {
                elementServices.discard(ElementTypeName.ETN_KEYBOARD, userId,
                        computer.getKeyboard().getId(), computer.getId());
            } else {
                unlinkElement(ElementTypeName.ETN_KEYBOARD, userId, computer.getKeyboard().getId(),
                        computer.getId());
            }

            if (discardMouse) {
                elementServices.discard(ElementTypeName.ETN_MOUSE, userId,
                        computer.getMouse().getId(), computer.getId());
            } else {
                unlinkElement(ElementTypeName.ETN_MOUSE, userId, computer.getMouse().getId(),
                        computer.getId());
            }

            computerDAO.setDiscardedAndAvailableById(computer.getId(), true, false);
            noveltyServices.create(userId, null, computer.getId(),
                    labServices.getLabIdByLinkedComputerId(computer.getId()), "Discarded computer",
                    null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
