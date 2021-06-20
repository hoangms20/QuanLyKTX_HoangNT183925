package dao;

import model.SinhVien;
import model.ThuePhong;

import java.sql.SQLException;
import java.util.List;

public interface SinhVienDAO extends BaseDAO<SinhVien> {
    List<SinhVien> findAllPhong() throws SQLException;

    List<SinhVien> findByDotDK(String s) throws SQLException;

    List<SinhVien> findChoDuyet(String s) throws SQLException;

    List<SinhVien> findDaDuyet(String s) throws SQLException;

    List<SinhVien> findAllDaDuyet() throws SQLException;

    List<SinhVien> findByPhong(String s, ThuePhong thuePhong) throws SQLException;

    List<SinhVien> findByKhuNha(String s, ThuePhong thuePhong) throws SQLException;

    List<SinhVien> findLikeByPKDotDK(String s, String s2) throws SQLException;

    SinhVien findByPK(String MSSV) throws SQLException;
}
