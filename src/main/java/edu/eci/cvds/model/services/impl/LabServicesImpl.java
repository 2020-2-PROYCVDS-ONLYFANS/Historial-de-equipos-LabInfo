package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.LabDAO;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.LabServices;
import edu.eci.cvds.model.services.NoveltyServices;
import edu.eci.cvds.model.services.ServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LabServicesImpl implements LabServices {

    @Inject
    private LabDAO labDAO;

    @Inject
    private ComputerServices computerServices;

    @Inject
    private NoveltyServices noveltyServices;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(LabServicesImpl.class);

    @Override
    public Lab getLabById(Long id) throws ServicesException {
        try {
            return labDAO.getLabById(id);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Lab getLabByName(String name) throws ServicesException {
        try {
            return labDAO.getLabByName(name);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Long getLabIdByLinkedComputerId(Long computerId) throws ServicesException {
        try {
            LOGGER.info("getLabIdByLinkedComputerId - try");
            return labDAO.getLabIdByLinkedComputerId(computerId);
        } catch (PersistenceException e) {
            LOGGER.info("getLabIdByLinkedComputerId - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void registerComputerToLabByIds(Long userId, Long computerId, Long labId)
            throws ServicesException {
        try {
            LOGGER.info("registerComputerToLabByIds - try");
            labDAO.registerComputerToLabByIds(computerId, labId);
            computerServices.setDiscardedAndAvailableById(computerId, false, false);
            noveltyServices.create(userId, null, computerId, labId, "Computer registered to lab",
                    null);
        } catch (PersistenceException e) {
            LOGGER.info("registerComputerToLabByIds - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void registerLab(Long userId, Lab lab) throws ServicesException {
        try {
            LOGGER.info("registerLab - try");
            labDAO.registerLab(lab);
            lab.setId(labDAO.getLabIdByName(lab.getName()));

            noveltyServices.create(userId, null, null, lab.getId(), "Registered", null);


            registerComputersToLabById(userId, lab.getId(), lab.getComputers());
        } catch (PersistenceException e) {
            LOGGER.info("registerLab - try");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void registerComputersToLabById(Long userId, Long labId, List<Computer> list)
            throws ServicesException {
        LOGGER.info("registerComputersToLabById");
        for (Computer computer : list) {
            if (!computer.isAvailable()) {
                unlinkLabComputerByIds(userId, computer.getId(),
                        getLabIdByLinkedComputerId(computer.getId()));
            }
            registerComputerToLabByIds(userId, computer.getId(), labId);
        }
    }

    @Override
    public void unlinkLabComputerByIds(Long userId, Long computerId, Long labId)
            throws ServicesException {
        try {
            LOGGER.info("unlinkLabComputerByIds");
            labDAO.unlinkLabComputerByIds(labId, computerId);
            computerServices.setDiscardedAndAvailableById(computerId, false, true);
            noveltyServices.create(userId, null, computerId, labId, "Unlinked lab computer", null);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
