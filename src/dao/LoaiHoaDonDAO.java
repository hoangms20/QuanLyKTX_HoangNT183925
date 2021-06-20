package dao;

import model.DichVuPhong;
import model.LoaiHoaDon;

import java.sql.SQLException;

public interface LoaiHoaDonDAO extends BaseDAO<LoaiHoaDon> {
    LoaiHoaDon findByPK(String maLoaiHD) throws SQLException;
}
