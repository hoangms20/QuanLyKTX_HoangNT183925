package dao;

import model.UuTien;

import java.sql.SQLException;
import java.util.List;

public interface UuTienDAO extends BaseDAO<UuTien> {

    UuTien findByPK(String maUuTien) throws SQLException;
}
