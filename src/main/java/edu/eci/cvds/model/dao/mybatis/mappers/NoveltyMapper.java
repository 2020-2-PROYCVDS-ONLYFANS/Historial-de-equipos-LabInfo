package edu.eci.cvds.model.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface NoveltyMapper {

    @Transactional
    void createElementNovelty(@Param("userId") Long userId, @Param("elementId") Long elementId,
            @Param("computerId") Long computerId, @Param("labId") Long labId,
            @Param("title") String title, @Param("detail") String detail);
}
