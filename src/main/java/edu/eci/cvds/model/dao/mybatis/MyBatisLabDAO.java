package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.LabDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.LabMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisLabDAO implements LabDAO {

    @Inject
    private LabMapper labMapper;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(MyBatisLabDAO.class);

    @Override
    public Long getLabIdByLinkedComputerId(Long id) throws PersistenceException {
        try {
            LOGGER.info("getLabIdByLinkedComputerId - try");
            return labMapper.getLabIdByLinkedComputerId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getLabIdByLinkedComputerId - catch");
            throw new PersistenceException("Fail to get lab id by linked computer id.", e);
        }
    }
}
