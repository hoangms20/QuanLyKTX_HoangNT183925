package dao_impl;

import dao.LoaiHoaDonDAO;
import model.LoaiHoaDon;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiHoaDonDAOimpl implements LoaiHoaDonDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public LoaiHoaDon getObject(ResultSet resultSet) throws SQLException {
        LoaiHoaDon loaiHoaDon = null;
        loaiHoaDon = new LoaiHoaDon(resultSet.getString("MaLoaiHD"),
                resultSet.getString("TenLoaiHD"),
                resultSet.getInt("GiaTri"),
                resultSet.getInt("tienVuotDM"),
                resultSet.getInt("mucHoTro"),
                resultSet.getInt("BatBuoc"));
        return loaiHoaDon;
    }

    @Override
    public List<LoaiHoaDon> getList(ResultSet resultSet) throws SQLException {
        List<LoaiHoaDon> list = new ArrayList<>();
        while (resultSet.next()) {
            LoaiHoaDon loaiHoaDon = getObject(resultSet);
            if (loaiHoaDon != null) list.add(loaiHoaDon);
        }
        return list;
    }

    @Override
    public List<LoaiHoaDon> findAll() throws SQLException {
        String sql = "select * from loaihoadon;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<LoaiHoaDon> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM loaihoadon where MaLoaiHD like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, "%" + s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public LoaiHoaDon findByPK(String maLoaiHD) throws SQLException {
        LoaiHoaDon loaiHoaDon = null;
        String sql = "select * from loaihoadon where MaLoaiHD = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maLoaiHD);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            loaiHoaDon = getObject(resultSet);
        }
        return loaiHoaDon;
    }

    @Override
    public boolean insert(LoaiHoaDon loaiHoaDon) throws SQLException {
        String sql = "insert into loaihoadon (MaLoaiHD, TenLoaiHD, GiaTri, TienVuotDM, MucHoTro, BatBuoc) values (?, ?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, loaiHoaDon.getMaLoaiHD());
        preparedStatement.setString(2, loaiHoaDon.getTenLoaiHD());
        preparedStatement.setInt(3, loaiHoaDon.getGiaTri());
        preparedStatement.setInt(4, loaiHoaDon.getTienVuotDM());
        preparedStatement.setInt(5, loaiHoaDon.getMucHoTro());
        preparedStatement.setInt(6, loaiHoaDon.getBatBuoc());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(LoaiHoaDon loaiHoaDon) throws SQLException {
        boolean result = false;
        String sql = "update loaihoadon set TenLoaiHD=?, GiaTri=?, TienVuotDM=?, MucHoTro=?, BatBuoc=? where MaLoaiHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(6, loaiHoaDon.getMaLoaiHD());
        preparedStatement.setString(1, loaiHoaDon.getTenLoaiHD());
        preparedStatement.setInt(2, loaiHoaDon.getGiaTri());
        preparedStatement.setInt(3, loaiHoaDon.getTienVuotDM());
        preparedStatement.setInt(4, loaiHoaDon.getMucHoTro());
        preparedStatement.setInt(5, loaiHoaDon.getBatBuoc());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(LoaiHoaDon loaiHoaDon) throws SQLException {
        String sql = "delete from loaihoadon where MaLoaiHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, loaiHoaDon.getMaLoaiHD());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
