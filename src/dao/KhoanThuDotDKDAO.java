package dao;

import model.KhoanThuDotDK;

import java.sql.SQLException;
import java.util.List;

public interface KhoanThuDotDKDAO extends BaseDAO<KhoanThuDotDK> {
    List<KhoanThuDotDK> findByDDK(String s) throws SQLException;

    KhoanThuDotDK findByPK(Integer maKhoanThuDotDK) throws SQLException;
}
