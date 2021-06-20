package dao;

import model.ChiTietHoaDon;

import java.sql.SQLException;

public interface ChiTietHoaDonDAO extends BaseDAO<ChiTietHoaDon> {
    ChiTietHoaDon findByPK(String maLoaiHD, String maHD) throws SQLException;

}
