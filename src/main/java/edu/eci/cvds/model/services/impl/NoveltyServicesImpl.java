package edu.eci.cvds.model.services.impl;

import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.model.dao.NoveltyDAO;
import edu.eci.cvds.model.entities.Novelty;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoveltyServicesImpl implements NoveltyServices {

    @Inject
    private NoveltyDAO noveltyDAO;

    @Inject
    private AuthServices authServices;

    @Inject
    private ElementServices elementServices;

    @Inject
    private ComputerServices computerServices;

    @Inject
    LabServices labServices;

    private static final Logger LOGGER = LoggerFactory.getLogger(NoveltyServicesImpl.class);

    @Override
    public List<Novelty> getByElementId(Long elementId) throws ServicesException {
        try {
            LOGGER.info("getByElementId");
            return noveltyDAO.getByElementId(elementId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public List<Novelty> getByComputerId(Long computerId) throws ServicesException {
        try {
            LOGGER.info("getByComputerId");
            return noveltyDAO.getByComputerId(computerId);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void create(Long userId, Long elementId, Long computerId, Long labId, String title,
            String detail) throws ServicesException {
        try {
            LOGGER.info("create - try");
            noveltyDAO.create(userId, elementId, computerId, labId, title, detail);
        } catch (PersistenceException e) {
            LOGGER.info("create - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void createByComputerReferenceAndUsername(String username, String computerRef,
            String title, String detail) throws ServicesException {
        try {
            LOGGER.info("createByComputerReferenceAndUsername - try");
            Long computerId = computerServices.getIdByReference(computerRef);

            noveltyDAO.create(authServices.getUserIdByUsername(username), null, computerId,
                    labServices.getLabIdByLinkedComputerId(computerId), title, detail);
        } catch (ServicesException e) {
            LOGGER.info("createByComputerReferenceAndUsername - catch 1");
            throw e;
        } catch (PersistenceException e) {
            LOGGER.info("createByComputerReferenceAndUsername - catch 2");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void createByElementReferenceAndUsername(ElementTypeName typeName, String username,
            String elementRef, String title, String detail) throws ServicesException {
        try {
            LOGGER.info("createByElementReferenceAndUsername - try");
            Long userId = authServices.getUserIdByUsername(username);
            Long elementId = elementServices.getIdByReference(elementRef);
            Long computerId;

            switch (typeName) {
                case ETN_COMPUTER_CASE:
                    computerId = computerServices.getIdByComputerCaseId(elementId);
                    noveltyDAO.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId), title, detail);
                    break;

                case ETN_MONITOR:
                    computerId = computerServices.getIdByMonitorId(elementId);
                    noveltyDAO.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId), title, detail);
                    break;

                case ETN_KEYBOARD:
                    computerId = computerServices.getIdByKeyboardId(elementId);
                    noveltyDAO.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId), title, detail);
                    break;

                case ETN_MOUSE:
                    computerId = computerServices.getIdByMouseId(elementId);
                    noveltyDAO.create(userId, elementId, computerId,
                            labServices.getLabIdByLinkedComputerId(computerId), title, detail);
                    break;
            }
        } catch (ServicesException e) {
            LOGGER.info("createByComputerReferenceAndUsername - catch 1");
            throw e;
        } catch (PersistenceException e) {
            LOGGER.info("createByComputerReferenceAndUsername - catch 2");
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
