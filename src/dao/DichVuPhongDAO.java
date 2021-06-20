package dao;

import model.DichVuPhong;

import java.sql.SQLException;
import java.util.List;

public interface DichVuPhongDAO extends BaseDAO<DichVuPhong> {
    List<DichVuPhong> findByPhong(String maP) throws SQLException;

    DichVuPhong findByPK(String maP, String maLoaiHD) throws SQLException;
}
