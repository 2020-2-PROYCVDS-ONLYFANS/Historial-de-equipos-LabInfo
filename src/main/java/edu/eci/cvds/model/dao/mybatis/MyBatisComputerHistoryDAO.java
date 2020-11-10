package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerHistoryDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ComputerHistoryMapper;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBatisComputerHistoryDAO implements ComputerHistoryDAO {

    @Inject
    ComputerHistoryMapper computerHistoryMapper;

    @Override
    public void addComputerHistoryById(long computerId, long userId, String title) throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryById(computerId, userId, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history by id.", e);
        }
    }

    @Override
    public void addComputerHistoryWithDetailById(long computerId, long userId, String title, String detail)
            throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryWithDetailById(computerId, userId, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history with detail by id.", e);
        }
    }

    @Override
    public void addComputerHistoryByReference(String reference, long userId, String title)
            throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryByReference(reference, userId, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history by reference.", e);
        }
    }

    @Override
    public void addComputerHistoryByReferenceAndUsername(String reference, String username, String title)
            throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryByReferenceAndUsername(reference, username, title);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history by reference and username", e);
        }
    }

    @Override
    public void addComputerHistoryWithDetailByReference(String reference, long userId, String title, String detail)
            throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryWithDetailByReference(reference, userId, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history with detail by reference.", e);
        }
    }

    @Override
    public void addComputerHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException {
        try {
            computerHistoryMapper.addComputerHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to add computer history with detail by reference and username.", e);
        }
    }
}
