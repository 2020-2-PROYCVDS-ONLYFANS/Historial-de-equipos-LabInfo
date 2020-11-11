package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ComputerMapper;
import edu.eci.cvds.model.entities.computer.Computer;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBatisComputerDAO implements ComputerDAO {

    @Inject
    ComputerMapper computerMapper;

    @Override
    public void registerComputerWithReferences(
            String computer, String computerCase, String monitor, String keyboard, String mouse)
            throws PersistenceException {
        try {
            computerMapper.registerComputerWithReferences(
                    computer, computerCase, monitor, keyboard, mouse);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register computer.", e);
        }
    }

    @Override
    public Computer loadComputerByReference(String reference) throws PersistenceException {
        try {
            return computerMapper.loadComputerByReference(reference);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get computer by reference.", e);
        }
    }

    @Override
    public void associateComputerCaseByReference(
            String computer, String computerCase) throws PersistenceException {
        try {
            computerMapper.associateComputerCaseByReference(computer, computerCase);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate computer case by reference.", e);
        }
    }

    @Override
    public void associateMonitorByReference(
            String computer, String monitor) throws PersistenceException {
        try {
            computerMapper.associateMonitorByReference(computer, monitor);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate monitor by reference.", e);
        }
    }

    @Override
    public void associateKeyboardByReference(
            String computer, String keyboard) throws PersistenceException {
        try {
            computerMapper.associateKeyboardByReference(computer, keyboard);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate keyboard by reference.", e);
        }
    }

    @Override
    public void associateMouseByReference(
            String computer, String mouse) throws PersistenceException {
        try {
            computerMapper.associateMouseByReference(computer, mouse);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to associate mouse by reference.", e);
        }
    }
}
