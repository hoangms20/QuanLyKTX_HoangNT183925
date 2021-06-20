package dao;

import model.DonXinRa;
import model.DotDangKy;

import java.sql.SQLException;
import java.util.List;

public interface DonXinRaDAO extends BaseDAO<DonXinRa> {
    List<DonXinRa> findByXetDuyet(int i) throws SQLException;

    List<DonXinRa> findByDotVaXetDuyet(DotDangKy dotDangKy) throws SQLException;

    DonXinRa findByPK(String maDonXinRa, String MSSV) throws SQLException;
}
