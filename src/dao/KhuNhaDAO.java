package dao;

import model.HoaDon;
import model.KhuNha;

import java.sql.SQLException;
import java.util.List;

public interface KhuNhaDAO extends BaseDAO<KhuNha> {
    KhuNha findByPK(String maKhu) throws SQLException;

    Integer countSLPhong(KhuNha khuNha) throws SQLException;

    Integer countSLConTrongKhuNha(KhuNha khuNha) throws SQLException;
}
