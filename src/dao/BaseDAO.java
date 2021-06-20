package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    T getObject(ResultSet resultSet) throws SQLException;

    List<T> getList(ResultSet resultSet) throws SQLException;

    List<T> findAll() throws SQLException;

    List<T> findLikeByPK(String s) throws SQLException;

    boolean insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(T t) throws SQLException;

}