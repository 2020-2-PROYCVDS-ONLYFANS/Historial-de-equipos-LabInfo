package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.LabDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.LabMapper;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public class MyBatisLabDAO implements LabDAO {

    @Inject
    private LabMapper labMapper;

    @Override
    public Lab getLabById(Long id) throws PersistenceException {
        try {
            return labMapper.getLabById(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get lab by id.", e);
        }
    }

    @Override
    public Lab getLabByName(String name) throws PersistenceException {
        try {
            return labMapper.getLabByName(name);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get lab by name.", e);
        }
    }

    @Override
    public Long getLabIdByName(String name) throws PersistenceException {
        try {
            return labMapper.getLabIdByName(name);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get lab id by name", e);
        }
    }

    @Override
    public Long getLabIdByLinkedComputerId(Long computerId) throws PersistenceException {
        try {
            return labMapper.getLabIdByLinkedComputerId(computerId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get lab id by linked computer id.", e);
        }
    }

    @Override
    public void registerLab(Lab lab) throws PersistenceException {
        try {
            labMapper.registerLab(lab);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register lab.", e);
        }
    }

    @Override
    public void registerComputerToLabByIds(Long computerId, Long labId)
            throws PersistenceException {
        try {
            labMapper.registerComputerToLabByIds(computerId, labId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register computer to lab by ids.", e);
        }
    }

    @Override
    public void registerComputersToLabByIdAndList(Long labId, List<Computer> list)
            throws PersistenceException {
        try {
            labMapper.registerComputersToLabByIdAndList(labId, list);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register computers to lab by id.", e);
        }
    }

    @Override
    public void unlinkLabComputerByIds(Long labId, Long computerId) throws PersistenceException {
        try {
            labMapper.unlinkLabComputerByIds(labId, computerId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to unlink lab computer by ids.", e);
        }
    }
}
