package dao;

import model.Lop;

import java.sql.SQLException;
import java.util.List;

public interface LopDAO extends BaseDAO<Lop> {

    Lop findByPK(String maLop) throws SQLException;
}
