package dao_impl;

import dao.NguoiDungDAO;
import model.MyConnection;
import model.NguoiDung;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAOimpl implements NguoiDungDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public NguoiDung getObject(ResultSet resultSet) throws SQLException {
        NguoiDung nguoiDung = null;
        nguoiDung = new NguoiDung(resultSet.getString("TenDangNhap"),
                resultSet.getString("HoTen"),
                resultSet.getString("MatKhau"),
                resultSet.getString("NhomNguoiDung"));
        return nguoiDung;
    }

    @Override
    public List<NguoiDung> getList(ResultSet resultSet) throws SQLException {
        List<NguoiDung> list = new ArrayList<>();
        while (resultSet.next()){
            NguoiDung nguoiDung = getObject(resultSet);
            if(nguoiDung != null) list.add(nguoiDung);
        }
        return list;
    }

    @Override
    public List<NguoiDung> findAll() throws SQLException {
        String sql ="select * from nguoidung;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<NguoiDung> findNhomNguoiDung(String s) throws SQLException {
        String sql ="select * from nguoidung where NhomNguoiDung = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<NguoiDung> findLikeByPK(String s) throws SQLException {
        String sql = "select * from nguoidung where TenDangNhap like ? AND NhomNguoiDung = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        preparedStatement.setString(2, "Sinh viên");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public NguoiDung findByPK(String tenDangNhap) throws SQLException {
        NguoiDung nguoiDung = null;
        String sql = "select * from nguoidung where TenDangNhap = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, tenDangNhap);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            nguoiDung = getObject(resultSet);
        }
        return nguoiDung;
    }

    @Override
    public boolean insert(NguoiDung nguoiDung) throws SQLException {
        String sql = "insert into nguoidung (TenDangNhap, HoTen, MatKhau, NhomNguoiDung) values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,nguoiDung.getTenDangNhap());
        preparedStatement.setString(2,nguoiDung.getHoTen());
        preparedStatement.setString(3,nguoiDung.getMatKhau());
        preparedStatement.setString(4,nguoiDung.getNhomNguoiDung());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(NguoiDung nguoiDung) throws SQLException {
        boolean result = false;
        String sql = "update nguoidung set MatKhau = ? where TenDangNhap = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, nguoiDung.getMatKhau());
        preparedStatement.setString(2, nguoiDung.getTenDangNhap());
        if (preparedStatement.executeUpdate() > 0){
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(NguoiDung nguoiDung) throws SQLException {
        String sql = "delete from nguoidung where TenDangNhap = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, nguoiDung.getTenDangNhap());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
