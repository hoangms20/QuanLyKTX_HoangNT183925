package dao_impl;

import dao.DotDangKyDAO;
import model.DotDangKy;
import model.MyConnection;
import model.Phong;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DotDangKyDAOimpl implements DotDangKyDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public DotDangKy getObject(ResultSet resultSet) throws SQLException {
        DotDangKy dotDangKy = null;
        dotDangKy = new DotDangKy(resultSet.getString("MaDotDK"),
                resultSet.getString("TenDotDK"),
                resultSet.getDate("NgayBatDauDK"),
                resultSet.getDate("NgayKetThucDK"),
                resultSet.getInt("ChiTieu"),
                resultSet.getInt("SLDangKy"));
        return dotDangKy;
    }

    @Override
    public List<DotDangKy> getList(ResultSet resultSet) throws SQLException {
        List<DotDangKy> list = new ArrayList<>();
        while (resultSet.next()) {
            DotDangKy dotDangKy = getObject(resultSet);
            if (dotDangKy != null) list.add(dotDangKy);
        }
        return list;
    }

    @Override
    public List<DotDangKy> findAll() throws SQLException {
        String sql = "select * from dotdangky;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }


    @Override
    public List<DotDangKy> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM dotdangky where MaDotDK like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, "%" + s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public DotDangKy findByPK(String maDotDK) throws SQLException {
        DotDangKy dotDangKy = null;
        String sql = "select * from dotdangky where MaDotDK = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maDotDK);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            dotDangKy = getObject(resultSet);
        }
        return dotDangKy;
    }

    @Override
    public boolean insert(DotDangKy dotDangKy) throws SQLException {
        String sql = "insert into dotdangky (MaDotDK, TenDotDK, NgayBatDauDK, NgayKetThucDK, ChiTieu, SLDangKy) values (?, ?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dotDangKy.getMaDotDK());
        preparedStatement.setString(2, dotDangKy.getTenDotDK());
        preparedStatement.setDate(3, (Date) dotDangKy.getNgayBatDauDK());
        preparedStatement.setDate(4, (Date) dotDangKy.getNgayKetThucDK());
        preparedStatement.setInt(5, dotDangKy.getChiTieu());
        preparedStatement.setInt(6, dotDangKy.getSLDangKi());

        try {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }

    @Override
    public boolean update(DotDangKy dotDangKy) throws SQLException {
        String sql = "update dotdangky set TenDotDK = ?, NgayBatDauDK=?,NgayKetThucDK=?,ChiTieu=?,SLDangKy=? where MaDotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(6, dotDangKy.getMaDotDK());
        preparedStatement.setString(1, dotDangKy.getTenDotDK());
        preparedStatement.setDate(2, (Date) dotDangKy.getNgayBatDauDK());
        preparedStatement.setDate(3, (Date) dotDangKy.getNgayKetThucDK());
        preparedStatement.setInt(4, dotDangKy.getChiTieu());
        preparedStatement.setInt(5, dotDangKy.getSLDangKi());

        try {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(DotDangKy dotDangKy) throws SQLException {
        String sql = "delete from dotdangky where MaDotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dotDangKy.getMaDotDK());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
