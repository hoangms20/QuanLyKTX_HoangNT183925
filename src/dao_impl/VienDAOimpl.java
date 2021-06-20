package dao_impl;

import dao.VienDAO;
import model.DotDangKy;
import model.MyConnection;
import model.Vien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VienDAOimpl implements VienDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public Vien getObject(ResultSet resultSet) throws SQLException {
        Vien vien = null;
        vien = new Vien(resultSet.getString("MaVien"),
                resultSet.getString("TenVien"));
        return vien;
    }

    @Override
    public List<Vien> getList(ResultSet resultSet) throws SQLException {
        List<Vien> list = new ArrayList<>();
        while (resultSet.next()) {
            Vien vien = getObject(resultSet);
            if (vien != null) list.add(vien);
        }
        return list;
    }

    @Override
    public List<Vien> findAll() throws SQLException {
        String sql = "select * from vien;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Vien> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM vien where MaVien like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Vien findByPK(String maVien) throws SQLException {
        Vien vien = null;
        String sql = "select * from vien where MaVien = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maVien);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            vien = getObject(resultSet);
        }
        return vien;
    }

    @Override
    public boolean insert(Vien vien) throws SQLException {
        String sql = "insert into vien (MaVien, TenVien) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, vien.getMaVien());
        preparedStatement.setString(2, vien.getTenVien());

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
    public boolean update(Vien vien) throws SQLException {
        String sql = "update vien set TenVien=? where MaVien = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(2, vien.getMaVien());
        preparedStatement.setString(1, vien.getTenVien());

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
    public boolean delete(Vien vien) throws SQLException {
        String sql = "delete from vien where MaVien = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, vien.getMaVien());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
