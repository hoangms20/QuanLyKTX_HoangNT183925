package dao;

import model.HoaDon;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface HoaDonDAO extends BaseDAO<HoaDon> {
    List<HoaDon> findByKhu(String tenKhu, Date ngayLap) throws SQLException;

    List<HoaDon> findAllByNgayLap(Date ngayLap) throws SQLException;

    HoaDon findByPK(String maHD) throws SQLException;
}
