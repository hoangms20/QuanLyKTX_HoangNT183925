package dao_impl;

import dao.TrangThaiSVDAO;
import model.MyConnection;
import model.TrangThaiSV;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrangThaiSVDAOimpl implements TrangThaiSVDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public TrangThaiSV getObject(ResultSet resultSet) throws SQLException {
        TrangThaiSV trangThaiSV = null;
        trangThaiSV = new TrangThaiSV(resultSet.getInt("MaTrangThai"),
                resultSet.getString("TenTrangThai"));
        return trangThaiSV;
    }

    @Override
    public List<TrangThaiSV> getList(ResultSet resultSet) throws SQLException {
        List<TrangThaiSV> list = new ArrayList<>();
        while (resultSet.next()) {
            TrangThaiSV trangThaiSV = getObject(resultSet);
            if (trangThaiSV != null) list.add(trangThaiSV);
        }
        return list;
    }

    @Override
    public List<TrangThaiSV> findAll() throws SQLException {
        String sql = "select * from trangthai_sv;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<TrangThaiSV> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public TrangThaiSV findByPK(Integer maTrangThai) throws SQLException {
        TrangThaiSV trangThaiSV = null;
        String sql = "select * from trangthai_sv where MaTrangThai = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, maTrangThai);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            trangThaiSV = getObject(resultSet);
        }
        return trangThaiSV;
    }

    @Override
    public boolean insert(TrangThaiSV trangThaiSV) throws SQLException {
        String sql = "insert into trangthai_sv (MaTrangThai, TenTrangThai) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, trangThaiSV.getMaTrangThai());
        preparedStatement.setString(2, trangThaiSV.getTenTrangThai());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(TrangThaiSV trangThaiSV) throws SQLException {
        boolean result = false;
        String sql = "update trangthai_sv set TenTrangThai = ? where MaTrangThai = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(2, trangThaiSV.getMaTrangThai());
        preparedStatement.setString(1, trangThaiSV.getTenTrangThai());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(TrangThaiSV trangThaiSV) throws SQLException {
        return false;
    }

}
