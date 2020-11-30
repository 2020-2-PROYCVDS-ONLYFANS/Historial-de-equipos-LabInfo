package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;

import java.util.List;

public interface LabServices {

    Lab getLabById(Long id) throws ServicesException;

    Lab getLabByName(String name) throws ServicesException;

    Long getLabIdByLinkedComputerId(Long computerId) throws ServicesException;

    void registerComputerToLabByIds(Long userId, Long computerId, Long labId)
            throws ServicesException;

    void registerLab(Long userId, Lab lab) throws ServicesException;

    void registerComputersToLabById(Long userId, Long labId, List<Computer> list)
            throws ServicesException;

    void unlinkLabComputerByIds(Long userId, Long computerId, Long labId) throws ServicesException;
}
