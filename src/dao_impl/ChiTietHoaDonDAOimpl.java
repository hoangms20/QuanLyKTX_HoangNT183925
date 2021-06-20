package dao_impl;

import dao.ChiTietHoaDonDAO;
import model.ChiTietHoaDon;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChiTietHoaDonDAOimpl implements ChiTietHoaDonDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public ChiTietHoaDon getObject(ResultSet resultSet) throws SQLException {
        ChiTietHoaDon chiTietHoaDon = null;
        chiTietHoaDon = new ChiTietHoaDon(resultSet.getString("MaLoaiHD"),
                resultSet.getString("MaHD"));
        return chiTietHoaDon;
    }

    @Override
    public List<ChiTietHoaDon> getList(ResultSet resultSet) throws SQLException {
        List<ChiTietHoaDon> list = new ArrayList<>();
        while (resultSet.next()) {
            ChiTietHoaDon chiTietHoaDon = getObject(resultSet);
            if (chiTietHoaDon != null) list.add(chiTietHoaDon);
        }
        return list;
    }

    @Override
    public List<ChiTietHoaDon> findAll() throws SQLException {
        String sql = "select * from chitiet_hoadon;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<ChiTietHoaDon> findLikeByPK(String s) throws SQLException {
        String sql = "select * from chitiet_hoadon where MaHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public ChiTietHoaDon findByPK(String maLoaiHD, String maHD) throws SQLException {
        ChiTietHoaDon chiTietHoaDon = null;
        String sql = "select * from chitiet_hoadon where maLoaiHD = ? AND maHD = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maLoaiHD);
        preparedStatement.setString(2, maHD);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            chiTietHoaDon = getObject(resultSet);
        }
        return chiTietHoaDon;
    }

    @Override
    public boolean insert(ChiTietHoaDon chiTietHoaDon) throws SQLException {
        String sql = "insert into chitiet_hoadon (MaLoaiHD, MaHD) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, chiTietHoaDon.getMaLoaiHD());
        preparedStatement.setString(2, chiTietHoaDon.getMaHD());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ChiTietHoaDon chiTietHoaDon) throws SQLException {
        boolean result = false;
        String sql = "update chitiet_hoadon set MaLoaiHD = ? where MaHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, chiTietHoaDon.getMaLoaiHD());
        preparedStatement.setString(2, chiTietHoaDon.getMaHD());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(ChiTietHoaDon chiTietHoaDon) throws SQLException {
        return false;
    }

}
