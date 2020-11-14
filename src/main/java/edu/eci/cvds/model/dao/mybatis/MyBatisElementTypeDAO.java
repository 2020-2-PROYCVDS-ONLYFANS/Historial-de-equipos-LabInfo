package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ElementTypeDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.ElementTypeMapper;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisElementTypeDAO implements ElementTypeDAO {

    @Inject
    private ElementTypeMapper elementTypeMapper;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(MyBatisElementTypeDAO.class);

    @Override
    public long getElementTypeIdByName(ElementTypeName name)
            throws PersistenceException {
        LOGGER.info("loadElementTypeIdByName");
        try {
            LOGGER.info("loadElementTypeIdByName - try");
            return elementTypeMapper.getElementTypeIdByName(name);
        } catch (PersistenceException e) {
            LOGGER.info("loadElementTypeIdByName - catch");
            throw new PersistenceException("Fail to load element id by name.", e);
        }
    }
}
