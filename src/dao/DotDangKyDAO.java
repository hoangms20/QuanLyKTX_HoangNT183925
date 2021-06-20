package dao;

import model.DotDangKy;
import model.HoaDon;

import java.sql.SQLException;
import java.util.List;

public interface DotDangKyDAO extends BaseDAO<DotDangKy> {

    DotDangKy findByPK(String maDotDK) throws SQLException;

}
