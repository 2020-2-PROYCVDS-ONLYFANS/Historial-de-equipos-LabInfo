package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LabMapper {

    Lab getLabById(@Param("id") Long id);

    Lab getLabByName(@Param("name") String name);

    Long getLabIdByName(@Param("name") String name);

    Long getLabIdByLinkedComputerId(@Param("computerId") Long computerId);

    void registerLab(@Param("lab") Lab lab);

    void registerComputerToLabByIds(@Param("computerId") Long computerId,
            @Param("labId") Long labId);

    // with dynamic SQL <foreach ...
    void registerComputersToLabByIdAndList(@Param("labId") Long labId,
            @Param("list") List<Computer> list);

    void unlinkLabComputerByIds(@Param("labId") Long labId, @Param("computerId") Long computerId);
}
