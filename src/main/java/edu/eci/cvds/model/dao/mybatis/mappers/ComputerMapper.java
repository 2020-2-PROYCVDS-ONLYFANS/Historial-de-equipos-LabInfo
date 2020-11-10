package edu.eci.cvds.model.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ComputerMapper {

    @Transactional
    void registerComputerWithReferences(
            @Param("computer") String computer, @Param("computerCase") String computerCase,
            @Param("monitor") String monitor, @Param("keyboard") String keyboard, @Param("mouse") String mouse);
}
