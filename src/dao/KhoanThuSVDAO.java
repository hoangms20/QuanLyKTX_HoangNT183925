package dao;

import model.KhoanThuSV;

import java.sql.SQLException;
import java.util.List;

public interface KhoanThuSVDAO extends BaseDAO<KhoanThuSV> {
    List<KhoanThuSV> findDotDK(String s) throws SQLException;

    List<KhoanThuSV> findDotDKAndLikeMSSV(String s, String mssv) throws SQLException;

    List<KhoanThuSV> findDotDKAndLikeMSSVAndTT(String s, String mssv, int i) throws SQLException;

    List<KhoanThuSV> findDotDKAndThanhToan(String s, int tinhTrangThanhToan) throws SQLException;

    KhoanThuSV findByPK(String maKhoanThuSV) throws SQLException;
}
