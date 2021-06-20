package dao;

import model.DienNuoc;
import model.Phong;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface DienNuocDAO extends BaseDAO<DienNuoc> {

    List<DienNuoc> findAllDateGanNhat(DienNuoc dienNuoc) throws SQLException;

    List<DienNuoc> findAllDate() throws SQLException;

    List<DienNuoc> findKhuNhaDateGanNhat(DienNuoc dienNuoc, String s) throws SQLException;

    List<DienNuoc> findPhongDateGanNhat(DienNuoc dienNuoc, String s) throws SQLException;

    List<DienNuoc> findDienGanNhat() throws SQLException;

    List<DienNuoc> findNuocGanNhat() throws SQLException;

    DienNuoc findDateGanNhat() throws SQLException;

    DienNuoc findByPK(Date thangNamGhiSoDN, String maLoaiHD, String maP) throws SQLException;
}
