package dao;

import model.KhoanThu;

import java.sql.SQLException;

public interface KhoanThuDAO extends BaseDAO<KhoanThu> {
    KhoanThu findByPK(String maKhoanThu) throws SQLException;
}
