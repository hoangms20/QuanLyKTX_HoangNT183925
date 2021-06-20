package dao_impl;

import dao.UuTienDAO;
import model.DotDangKy;
import model.MyConnection;
import model.UuTien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UuTienDAOimpl implements UuTienDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public UuTien getObject(ResultSet resultSet) throws SQLException {
        UuTien uuTien = null;
        uuTien = new UuTien(resultSet.getString("MaUuTien"),
                resultSet.getString("TenUuTien"),
                resultSet.getInt("MucDoUuTien"),
                resultSet.getString("GhiChu_UuTien"));
        return uuTien;
    }

    @Override
    public List<UuTien> getList(ResultSet resultSet) throws SQLException {
        List<UuTien> list = new ArrayList<>();
        while (resultSet.next()) {
            UuTien uuTien = getObject(resultSet);
            if (uuTien != null) list.add(uuTien);
        }
        return list;
    }

    @Override
    public List<UuTien> findAll() throws SQLException {
        String sql = "select * from uutien;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<UuTien> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM uutien where MaUuTien like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public UuTien findByPK(String maUuTien) throws SQLException {
        UuTien uuTien = null;
        String sql = "select * from uutien where MaUuTien = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maUuTien);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            uuTien = getObject(resultSet);
        }
        return uuTien;
    }

    @Override
    public boolean insert(UuTien uuTien) throws SQLException {
        String sql = "insert into uutien (MaUuTien, TenUuTien, MucDoUuTien, GhiChu_UuTien) values (?, ?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, uuTien.getMaUuTien());
        preparedStatement.setString(2, uuTien.getTenUuTien());
        preparedStatement.setInt(3, uuTien.getMucDoUuTien());
        preparedStatement.setString(4, uuTien.getGhiChuUuTien());

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
    public boolean update(UuTien uuTien) throws SQLException {
        String sql = "update uutien set TenUuTien=?, MucDoUuTien=?, GhiChu_UuTien=? where MaUuTien= ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(4, uuTien.getMaUuTien());
        preparedStatement.setString(1, uuTien.getTenUuTien());
        preparedStatement.setInt(2, uuTien.getMucDoUuTien());
        preparedStatement.setString(3, uuTien.getGhiChuUuTien());

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
    public boolean delete(UuTien uuTien) throws SQLException {
        String sql = "delete from uutien where MaUuTien = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, uuTien.getMaUuTien());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
