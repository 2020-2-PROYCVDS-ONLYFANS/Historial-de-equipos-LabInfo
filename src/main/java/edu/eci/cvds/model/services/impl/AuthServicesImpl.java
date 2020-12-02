package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthServicesImpl implements AuthServices {

    @Inject
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServicesImpl.class);

    @Override
    public Long getUserIdByUsername(String username) throws ServicesException {
        try {
            LOGGER.info("loadIdByUsername - try");
            return userDAO.getIdByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadIdByUsername - catch");
            throw new ServicesException(e.getMessage(), e);
        }
    }

    @Override
    public String getUsernameById(Long id) throws ServicesException {
        try {
            return userDAO.getUsernameById(id);
        } catch (PersistenceException e) {
            throw new ServicesException(e.getMessage(), e);
        }
    }
}
