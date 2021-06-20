package dao;

import model.HoaDon;
import model.Khoa;

import java.sql.SQLException;
import java.util.List;

public interface KhoaDAO extends BaseDAO<Khoa> {
    Khoa findByPK(String maKhoa) throws SQLException;

}
