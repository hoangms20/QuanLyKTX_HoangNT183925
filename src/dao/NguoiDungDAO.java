package dao;

import model.NguoiDung;

import java.sql.SQLException;
import java.util.List;

public interface NguoiDungDAO extends BaseDAO<NguoiDung> {

    List<NguoiDung> findNhomNguoiDung(String s) throws SQLException;

    NguoiDung findByPK(String tenDangNhap) throws SQLException;
}
