package edu.eci.cvds.model.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ComputerHistoryMapper {

    @Transactional
    void addComputerHistoryById(
            @Param("computerId") long computerId, @Param("userId") long userId, @Param("title") String title);

    @Transactional
    void addComputerHistoryWithDetailById(
            @Param("computerId") long computerId, @Param("userId") long userId,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addComputerHistoryByReference(
            @Param("reference") String reference, @Param("userId") long userId, @Param("title") String title);

    @Transactional
    void addComputerHistoryByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username, @Param("title") String title);

    @Transactional
    void addComputerHistoryWithDetailByReference(
            @Param("reference") String reference, @Param("userId") long userId,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addComputerHistoryWithDetailByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username,
            @Param("title") String title, @Param("detail") String detail);
}
