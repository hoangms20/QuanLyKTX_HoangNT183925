package dao;

import model.ChiTietHoaDon;
import model.ChiTietKhoanThu;

import java.sql.SQLException;

public interface ChiTietKhoanThuDAO extends BaseDAO<ChiTietKhoanThu> {
    ChiTietKhoanThu findByPK(String maLoaiHD, String maHD) throws SQLException;
}
