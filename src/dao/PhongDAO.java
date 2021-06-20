package dao;

import model.Phong;

import java.sql.SQLException;
import java.util.List;

public interface PhongDAO extends BaseDAO<Phong> {


    List<Phong> findByKhuNha(String s) throws SQLException;

    Phong findByPK(String maP) throws SQLException;

    Integer countSLConTrong() throws SQLException;
}
