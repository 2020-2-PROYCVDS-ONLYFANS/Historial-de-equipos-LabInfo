package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface LabDAO {

    Lab getLabById(Long id) throws PersistenceException;

    Lab getLabByName(String name) throws PersistenceException;

    Long getLabIdByName(String name) throws PersistenceException;

    Long getLabIdByLinkedComputerId(Long computerId) throws PersistenceException;

    void registerLab(Lab lab) throws PersistenceException;

    void registerComputerToLabByIds(Long computerId, Long labId) throws PersistenceException;

    // with dynamic SQL <foreach ...
    void registerComputersToLabByIdAndList(Long labId, List<Computer> list)
            throws PersistenceException;

    void unlinkLabComputerByIds(Long labId, Long computerId) throws PersistenceException;
}
