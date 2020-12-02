package edu.eci.cvds.model.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;
import edu.eci.cvds.model.entities.Novelty;

@SuppressWarnings("java:S1130")
public interface NoveltyMapper {

    List<Novelty> getByElementId(@Param("elementId") Long elementId);

    List<Novelty> getByComputerId(@Param("computerId") Long computerId);

    @Transactional
    void createElementNovelty(@Param("userId") Long userId, @Param("elementId") Long elementId,
            @Param("computerId") Long computerId, @Param("labId") Long labId,
            @Param("title") String title, @Param("detail") String detail);
}
