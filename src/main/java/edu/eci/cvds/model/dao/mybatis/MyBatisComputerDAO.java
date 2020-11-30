package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ComputerMapper;
import edu.eci.cvds.model.entities.Computer;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisComputerDAO implements ComputerDAO {

    @Inject
    private ComputerMapper computerMapper;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(MyBatisComputerDAO.class);

    @Override
    public void registerComputer(String reference, Long computerCaseId, Long monitorId,
            Long keyboardId, Long mouseId) throws PersistenceException {
        try {
            LOGGER.info("registerComputer - try");
            computerMapper.registerComputer(reference, computerCaseId, monitorId, keyboardId,
                    mouseId);
        } catch (PersistenceException e) {
            LOGGER.info("registerComputer - catch");
            throw new PersistenceException("Fail to register computer.", e);
        }
    }

    @Override
    public Long getIdByReference(String reference) throws PersistenceException {
        try {
            LOGGER.info("getIdByReference - try");
            return computerMapper.getIdByReference(reference);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByReference - try");
            throw new PersistenceException("Fail to get id by reference.", e);
        }
    }

    @Override
    public Long getIdByComputerCaseId(Long id) throws PersistenceException {
        try {
            LOGGER.info("getIdByComputerCaseId - try");
            return computerMapper.getIdByComputerCaseId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByComputerCaseId - catch");
            throw new PersistenceException("Fail to get id by computer case id.", e);
        }
    }

    @Override
    public Long getIdByMonitorId(Long id) throws PersistenceException {
        try {
            LOGGER.info("getIdByMonitorId - try");
            return computerMapper.getIdByMonitorId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByMonitorId - catch");
            throw new PersistenceException("Fail to get id by monitor id.", e);
        }
    }

    @Override
    public Long getIdByKeyboardId(Long id) throws PersistenceException {
        try {
            LOGGER.info("getIdByKeyboardId - try");
            return computerMapper.getIdByKeyboardId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByKeyboardId - catch");
            throw new PersistenceException("Fail to get id by keyboard id.", e);
        }
    }

    @Override
    public Long getIdByMouseId(Long id) throws PersistenceException {
        try {
            LOGGER.info("getIdByMouseId - try");
            return computerMapper.getIdByMouseId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getIdByMouseId - catch");
            throw new PersistenceException("Fail to get id by mouse id.", e);
        }
    }

    @Override
    public Computer getComputerByReference(String reference) throws PersistenceException {
        try {
            return computerMapper.getComputerByReference(reference);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get computer by reference.", e);
        }
    }

    @Override
    public void setComputerCaseIdByIds(Long computerId, Long computerCaseId)
            throws PersistenceException {
        try {
            computerMapper.setComputerCaseIdByIds(computerId, computerCaseId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate computer case by reference.", e);
        }
    }

    @Override
    public void setMonitorIdByIds(Long computerId, Long monitorId) throws PersistenceException {
        try {
            computerMapper.setMonitorIdByIds(computerId, monitorId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate monitor by reference.", e);
        }
    }

    @Override
    public void setKeyboardIdByIds(Long computerId, Long keyboardId) throws PersistenceException {
        try {
            computerMapper.setKeyboardIdByIds(computerId, keyboardId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate keyboard by reference.", e);
        }
    }

    @Override
    public void setMouseIdByIds(Long computerId, Long mouseId) throws PersistenceException {
        try {
            computerMapper.setMouseIdByIds(computerId, mouseId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate mouse by reference.", e);
        }
    }

    @Override
    public void setDiscardedAndAvailableById(Long id, Boolean discarded, Boolean available)
            throws PersistenceException {
        try {
            computerMapper.setDiscardedAndAvailableById(id, discarded, available);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to set discarded and available by id.", e);
        }
    }
}
