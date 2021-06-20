package dao;

import model.SinhVienDotDK;

import java.sql.SQLException;
import java.util.List;

public interface SinhVienDotDKDAO extends BaseDAO<SinhVienDotDK> {
    List<SinhVienDotDK> findByDotDK(String maDotDK) throws SQLException;

    SinhVienDotDK findByMSSV(String MSSV) throws SQLException;

    SinhVienDotDK findByPK(String MSSV, String maDotDK) throws SQLException;

    Integer countSLDK(String s) throws SQLException;
}
