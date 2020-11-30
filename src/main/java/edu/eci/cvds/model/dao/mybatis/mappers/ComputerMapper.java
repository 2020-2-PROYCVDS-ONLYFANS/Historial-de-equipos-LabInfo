package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.Computer;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ComputerMapper {

    @Transactional
    void registerComputer(@Param("reference") String reference,
            @Param("computerCaseId") Long computerCaseId, @Param("monitorId") Long monitorId,
            @Param("keyboardId") Long keyboardId, @Param("mouseId") Long mouseId);

    Long getIdByReference(@Param("reference") String reference);

    Long getIdByComputerCaseId(@Param("id") Long id);

    Long getIdByMonitorId(@Param("id") Long id);

    Long getIdByKeyboardId(@Param("id") Long id);

    Long getIdByMouseId(@Param("id") Long id);

    Computer getComputerByReference(@Param("reference") String reference);

    @Transactional
    void setComputerCaseIdByIds(@Param("computerId") Long id,
            @Param("computerCaseId") Long computerCaseId);

    @Transactional
    void setMonitorIdByIds(@Param("computerId") Long id, @Param("monitorId") Long monitorId);

    @Transactional
    void setKeyboardIdByIds(@Param("computerId") Long id, @Param("keyboardId") Long monitorId);

    @Transactional
    void setMouseIdByIds(@Param("computerId") Long id, @Param("mouseId") Long mouseId);

    @Transactional
    void setDiscardedAndAvailableById(@Param("id") Long id, @Param("discarded") Boolean discarded,
            @Param("available") Boolean available);
}
