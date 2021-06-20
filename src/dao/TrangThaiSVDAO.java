package dao;

import model.TrangThaiSV;

import java.sql.SQLException;

public interface TrangThaiSVDAO extends BaseDAO<TrangThaiSV> {
    TrangThaiSV findByPK(Integer maTrangThai) throws SQLException;
}
