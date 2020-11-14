package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.NoveltyDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.NoveltyMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisNoveltyDAO implements NoveltyDAO {

    @Inject
    private NoveltyMapper noveltyMapper;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(MyBatisNoveltyDAO.class);

    @Override
    public void create(
            Long userId, Long elementId, Long computerId, Long labId, String title, String detail)
            throws PersistenceException {
        LOGGER.info("addUnlinkedElementNovelty");
        try {
            LOGGER.info("addUnlinkedElementNovelty - try");
            noveltyMapper.createElementNovelty(userId, elementId, computerId, labId, title, detail);
        } catch (PersistenceException e) {
            LOGGER.info("addUnlinkedElementNovelty - catch");
            throw new PersistenceException("Fail to add unlinked element novelty.", e);
        }
    }
}
