package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.ElementTypeDAO;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementServicesImpl implements ElementServices {

    @Inject
    private ElementTypeDAO elementTypeDAO;

    @Inject
    private ElementDAO elementDAO;

    @Inject
    private AuthServices authServices;

    @Inject
    private NoveltyServices noveltyServices;

    @Inject
    private LabServices labServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(ElementServicesImpl.class);

    @Override
    public void registerElement(
            ElementTypeName typeName, String reference, String username)
            throws ServicesException {
        LOGGER.info("registerElement");
        try {
            LOGGER.info("registerElement - try");
            Long elementTypeId = elementTypeDAO.getElementTypeIdByName(typeName);
            Long userId = authServices.getUserIdByUsername(username);

            elementDAO.registerElement(reference, elementTypeId);

            Long elementId = elementDAO.getIdByReference(reference);
            noveltyServices.create(
                    userId, elementId, null, null,
                    "Registered", null);
        } catch (PersistenceException e) {
            LOGGER.info("registerElement - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Element getElementByReference(String reference)
            throws ServicesException {
        try {
            return elementDAO.getElementByReference(reference);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void prepareElementToLinkToNewComputer(
            Long userId, Long elementId)
            throws ServicesException {
        try {
            elementDAO.setAvailableById(elementId, false);
            noveltyServices.create(
                    userId, elementId, null,
                    null, "Linked to new computer", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getIdByReference(String reference) throws ServicesException {
        LOGGER.info("getIdByReference");
        try {
            LOGGER.info("getIdByReference - try");
            return elementDAO.getIdByReference(reference);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByReference - try");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void unlink(ElementTypeName typeName, Long userId, Long elementId, Long computerId)
            throws ServicesException {
        try {
            elementDAO.setAvailableById(elementId, true);
            switch (typeName) {
                case ETN_COMPUTER_CASE:
                    noveltyServices.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId),
                            "Unlinked computer case", null);
                    break;

                case ETN_MONITOR:
                    noveltyServices.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId),
                            "Unlinked monitor", null);
                    break;

                case ETN_KEYBOARD:
                    noveltyServices.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId),
                            "Unlinked keyboard", null);
                    break;

                case ETN_MOUSE:
                    noveltyServices.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId),
                            "Unlinked mouse", null);
                    break;
            }
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void discard(ElementTypeName typeName, Long userId, Long elementId, Long computerId)
            throws ServicesException {
        try {
            elementDAO.setAvailableById(elementId, false);
            elementDAO.setDiscardedById(elementId, true);
            noveltyServices.create(userId, elementId, null,
                    null, "Discarded", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
