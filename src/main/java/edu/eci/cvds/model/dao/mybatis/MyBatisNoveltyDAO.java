package edu.eci.cvds.model.dao.mybatis;

import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.model.dao.NoveltyDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.NoveltyMapper;
import edu.eci.cvds.model.entities.Novelty;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S1130")
public class MyBatisNoveltyDAO implements NoveltyDAO {

    @Inject
    private NoveltyMapper noveltyMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisNoveltyDAO.class);

    @Override
    public List<Novelty> getByElementId(Long elementId) throws PersistenceException {
        try {
            LOGGER.info("getByElementId - try");
            return noveltyMapper.getByElementId(elementId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get by element id.", e);
        }
    }

    @Override
    public List<Novelty> getByComputerId(Long computerId) throws PersistenceException {
        try {
            LOGGER.info("getByComputerId - try");
            return noveltyMapper.getByComputerId(computerId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get by computer id.", e);
        }
    }

    @Override
    public void create(Long userId, Long elementId, Long computerId, Long labId, String title,
            String detail) throws PersistenceException {
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
