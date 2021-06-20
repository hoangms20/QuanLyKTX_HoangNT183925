package dao_impl;

import dao.KhoaDAO;
import model.DotDangKy;
import model.Khoa;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaDAOimpl implements KhoaDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public Khoa getObject(ResultSet resultSet) throws SQLException {
        Khoa khoa = null;
        khoa = new Khoa(resultSet.getString("MaKhoa"),
                resultSet.getString("TenKhoa"),
                resultSet.getInt("NamVaoHoc"));
        return khoa;
    }

    @Override
    public List<Khoa> getList(ResultSet resultSet) throws SQLException {
        List<Khoa> list = new ArrayList<>();
        while (resultSet.next()) {
            Khoa khoa = getObject(resultSet);
            if (khoa != null) list.add(khoa);
        }
        return list;
    }

    @Override
    public List<Khoa> findAll() throws SQLException {
        String sql = "select * from khoa;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Khoa> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM khoa where MaDotDK like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Khoa findByPK(String maKhoa) throws SQLException {
        Khoa khoa = null;
        String sql = "select * from khoa where MaKhoa = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maKhoa);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            khoa = getObject(resultSet);
        }
        return khoa;
    }

    @Override
    public boolean insert(Khoa khoa) throws SQLException {
        String sql = "insert into khoa (MaKhoa, TenKhoa, NamVaoHoc) values (?, ?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoa.getMaKhoa());
        preparedStatement.setString(2, khoa.getTenKhoa());
        preparedStatement.setInt(3, khoa.getNamVaoHoc());

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
    public boolean update(Khoa khoa) throws SQLException {
        String sql = "update khoa set TenKhoa=?, NamVaoHoc=? where MaKhoa = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(3, khoa.getMaKhoa());
        preparedStatement.setString(1, khoa.getTenKhoa());
        preparedStatement.setInt(2, khoa.getNamVaoHoc());

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
    public boolean delete(Khoa khoa) throws SQLException {
        String sql = "delete from khoa where MaKhoa = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoa.getMaKhoa());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
