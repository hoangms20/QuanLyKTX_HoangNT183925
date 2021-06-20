package dao;

import model.ThuePhong;

import java.sql.SQLException;

public interface ThuePhongDAO extends BaseDAO<ThuePhong> {
    ThuePhong findByPK(String maThue) throws SQLException;

    ThuePhong findNgayKetThucGanNhat() throws SQLException;

    ThuePhong findByMSSV(String mssv) throws SQLException;
}
