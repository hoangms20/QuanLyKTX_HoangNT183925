package dao;

import model.Vien;

import java.sql.SQLException;

public interface VienDAO extends BaseDAO<Vien> {
    Vien findByPK(String maVien) throws SQLException;
}
