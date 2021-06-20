package dao_impl;

import dao.DonXinRaDAO;
import model.DonXinRa;
import model.DotDangKy;
import model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonXinRaDAOimpl implements DonXinRaDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public DonXinRa getObject(ResultSet resultSet) throws SQLException {
        DonXinRa donXinRa = null;
        donXinRa = new DonXinRa(resultSet.getString("MaDonXinRa"),
                resultSet.getString("MSSV"),
                resultSet.getDate("NgayLapDon"),
                resultSet.getString("LyDo"),
                resultSet.getInt("XetDuyet"));
        return donXinRa;
    }

    @Override
    public List<DonXinRa> getList(ResultSet resultSet) throws SQLException {
        List<DonXinRa> list = new ArrayList<>();
        while (resultSet.next()) {
            DonXinRa donXinRa = getObject(resultSet);
            if (donXinRa != null) list.add(donXinRa);
        }
        return list;
    }

    @Override
    public List<DonXinRa> findAll() throws SQLException {
        String sql = "select * from donxinra;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DonXinRa> findByXetDuyet(int i) throws SQLException {
        String sql = "select * from donxinra where XetDuyet = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,i);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DonXinRa> findByDotVaXetDuyet(DotDangKy dotDangKy) throws SQLException {
        String sql = "select * from donxinra where XetDuyet = 1 AND NgayLapDon >= ? AND NgayLapDon <= ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, dotDangKy.getNgayBatDauDK().toString());
        preparedStatement.setString(2, dotDangKy.getNgayKetThucDK().toString());
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DonXinRa> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM donxinra where MSSV like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public DonXinRa findByPK(String maDonXinRa, String MSSV) throws SQLException {
        DonXinRa donXinRa = null;
        String sql = "select * from donxinra where MaDonXinRa = ? AND MSSV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maDonXinRa);
        preparedStatement.setString(2, MSSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            donXinRa = getObject(resultSet);
        }
        return donXinRa;
    }

    @Override
    public boolean insert(DonXinRa donXinRa) throws SQLException {
        String sql = "insert into donxinra (MaDonXinRa, MSSV, NgayLapDon, LyDo, XetDuyet) values (?, ?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, donXinRa.getMaDonXinRa());
        preparedStatement.setString(2, donXinRa.getMSSV());
        preparedStatement.setDate(3, (Date) donXinRa.getNgayLapDon());
        preparedStatement.setString(4, donXinRa.getLyDo());
        preparedStatement.setInt(5, donXinRa.getXetDuyet());


        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(DonXinRa donXinRa) throws SQLException {
        boolean result = false;
        String sql = "update donxinra set NgayLapDon = ?, LyDo=?, XetDuyet=? where MaDonXinRa = ? AND MSSV = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setDate(1, (Date) donXinRa.getNgayLapDon());
        preparedStatement.setString(2, donXinRa.getLyDo());
        preparedStatement.setInt(3, donXinRa.getXetDuyet());
        preparedStatement.setString(4, donXinRa.getMaDonXinRa());
        preparedStatement.setString(5, donXinRa.getMSSV());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(DonXinRa donXinRa) throws SQLException {
        String sql = "delete from donxinra where MaDonXinRa = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, donXinRa.getMaDonXinRa());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
