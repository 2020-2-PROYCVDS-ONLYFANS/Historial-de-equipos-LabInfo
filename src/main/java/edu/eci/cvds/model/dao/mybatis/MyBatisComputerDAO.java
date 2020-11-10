package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ComputerMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            e.printStackTrace();
            throw new PersistenceException("Fail to register computer.", e);
        }
    }
}
