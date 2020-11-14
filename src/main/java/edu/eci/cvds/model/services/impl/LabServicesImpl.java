package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.LabDAO;
import edu.eci.cvds.model.services.LabServices;
import edu.eci.cvds.model.services.ServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabServicesImpl implements LabServices {

    @Inject
    private LabDAO labDAO;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(LabServicesImpl.class);

    @Override
    public Long getLabIdByLinkedComputerId(Long id) throws ServicesException {
        try {
            LOGGER.info("getLabIdByLinkedComputerId - try");
            return labDAO.getLabIdByLinkedComputerId(id);
        } catch (PersistenceException e) {
            LOGGER.info("getLabIdByLinkedComputerId - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
