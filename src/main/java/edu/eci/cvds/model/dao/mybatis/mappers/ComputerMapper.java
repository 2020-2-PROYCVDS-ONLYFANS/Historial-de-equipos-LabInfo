package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.computer.Computer;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ComputerMapper {

    @Transactional
    void registerComputerWithReferences(
            @Param("computer") String computer, @Param("computerCase") String computerCase,
            @Param("monitor") String monitor, @Param("keyboard") String keyboard,
            @Param("mouse") String mouse);

    Computer loadComputerByReference(@Param("reference") String reference);

    @Transactional
    void associateComputerCaseByReference(
            @Param("computer") String computer, @Param("computerCase") String computerCase);

    @Transactional
    void associateMonitorByReference(
            @Param("computer") String computer, @Param("monitor") String monitor);

    @Transactional
    void associateKeyboardByReference(
            @Param("computer") String computer, @Param("keyboard") String keyboard);
    @Transactional
    void associateMouseByReference(
            @Param("computer") String computer, @Param("mouse") String mouse);

}
