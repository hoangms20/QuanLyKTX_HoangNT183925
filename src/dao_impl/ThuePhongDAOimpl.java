package dao_impl;

import dao.ThuePhongDAO;
import model.MyConnection;
import model.ThuePhong;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThuePhongDAOimpl implements ThuePhongDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public ThuePhong getObject(ResultSet resultSet) throws SQLException {
        ThuePhong thuePhong = null;
        thuePhong = new ThuePhong(resultSet.getString("MaThue"),
                resultSet.getString("MSSV"),
                resultSet.getString("MaP"),
                resultSet.getDate("NgayBatDau"),
                resultSet.getDate("NgayKetThuc"));
        return thuePhong;
    }

    @Override
    public List<ThuePhong> getList(ResultSet resultSet) throws SQLException {
        List<ThuePhong> list = new ArrayList<>();
        while (resultSet.next()) {
            ThuePhong thuePhong = getObject(resultSet);
            if (thuePhong != null) list.add(thuePhong);
        }
        return list;
    }

    @Override
    public List<ThuePhong> findAll() throws SQLException {
        String sql = "select * from thuephong;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<ThuePhong> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public ThuePhong findByPK(String maThue) throws SQLException {
        ThuePhong thuePhong = null;
        String sql = "select * from thuephong where MaThue = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maThue);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            thuePhong = getObject(resultSet);
        }
        return thuePhong;
    }

    @Override
    public ThuePhong findNgayKetThucGanNhat() throws SQLException {
        ThuePhong thuePhong = null;
        String sql = "select * from thuephong order by NgayKetThuc DESC LIMIT 1 ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            thuePhong = getObject(resultSet);
        }
        return thuePhong;
    }

    @Override
    public ThuePhong findByMSSV(String mssv) throws SQLException {
        ThuePhong thuePhong = null;
        String sql = "select * from thuephong where MSSV like ? order by NgayKetThuc DESC LIMIT 1 ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, mssv);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            thuePhong = getObject(resultSet);
        }
        return thuePhong;
    }

    @Override
    public boolean insert(ThuePhong thuePhong) throws SQLException {
        String sql = "insert into thuephong (MaThue, MSSV, MaP, NgayBatDau, NgayKetThuc) values (?, ?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, thuePhong.getMaThue());
        preparedStatement.setString(2, thuePhong.getMSSV());
        preparedStatement.setString(3, thuePhong.getMaP());
        preparedStatement.setDate(4, (Date) thuePhong.getNgayBatDau());
        preparedStatement.setDate(5, (Date) thuePhong.getNgayKetThuc());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ThuePhong thuePhong) throws SQLException {
        boolean result = false;
        String sql = "update thuephong set MSSV=?, MaP=?, NgayBatDau=?, NgayKetThuc=? where MaThue= ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(5, thuePhong.getMaThue());
        preparedStatement.setString(1, thuePhong.getMSSV());
        preparedStatement.setString(2, thuePhong.getMaP());
        preparedStatement.setDate(3, (Date) thuePhong.getNgayBatDau());
        preparedStatement.setDate(4, (Date) thuePhong.getNgayKetThuc());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(ThuePhong thuePhong) throws SQLException {
        return false;
    }

}
