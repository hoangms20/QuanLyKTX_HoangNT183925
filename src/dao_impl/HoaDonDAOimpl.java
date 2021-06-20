package dao_impl;

import dao.HoaDonDAO;
import model.HoaDon;
import model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAOimpl implements HoaDonDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public HoaDon getObject(ResultSet resultSet) throws SQLException {
        HoaDon hoaDon = null;
        hoaDon = new HoaDon(resultSet.getString("MaHD"),
                resultSet.getString("MaP"),
                resultSet.getDate("NgayLap"),
                resultSet.getInt("TongTien"),
                resultSet.getString("TinhTrangHD"));
        return hoaDon;
    }

    @Override
    public List<HoaDon> getList(ResultSet resultSet) throws SQLException {
        List<HoaDon> list = new ArrayList<>();
        while (resultSet.next()) {
            HoaDon hoaDon = getObject(resultSet);
            if (hoaDon != null) list.add(hoaDon);
        }
        return list;
    }

    @Override
    public List<HoaDon> findAll() throws SQLException {
        String sql = "select * from hoadon;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<HoaDon> findAllByNgayLap(Date ngayLap) throws SQLException {
        String sql = "select * from hoadon where NgayLap = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setDate(1, ngayLap);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<HoaDon> findByKhu(String tenKhu, Date ngayLap) throws SQLException {
        String sql = "select * from hoadon inner join phong on phong.MaP = hoadon.MaP inner join khunha on phong.MaKhu = khunha.MaKhu AND khunha.TenKhu = ? AND hoadon.NgayLap = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, tenKhu);
        preparedStatement.setDate(2, ngayLap);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<HoaDon> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public HoaDon findByPK(String maHD) throws SQLException {
        HoaDon hoaDon = null;
        String sql = "select * from hoadon where MaHD = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maHD);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            hoaDon = getObject(resultSet);
        }
        return hoaDon;
    }

    @Override
    public boolean insert(HoaDon hoaDon) throws SQLException {
        String sql = "insert into hoadon (MaHD, MaP, NgayLap, TongTien, TinhTrangHD) values (?, ?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, hoaDon.getMaHD());
        preparedStatement.setString(2, hoaDon.getMaP());
        preparedStatement.setDate(3, (Date) hoaDon.getNgayLap());
        preparedStatement.setInt(4, hoaDon.getTongTien());
        preparedStatement.setString(5, hoaDon.getTinhTrangHD());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(HoaDon hoaDon) throws SQLException {
        boolean result = false;
        String sql = "update hoadon set MaP=?, NgayLap=?, TongTien=?, TinhTrangHD=? where MaHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(5, hoaDon.getMaHD());
        preparedStatement.setString(1, hoaDon.getMaP());
        preparedStatement.setDate(2, (Date) hoaDon.getNgayLap());
        preparedStatement.setInt(3, hoaDon.getTongTien());
        preparedStatement.setString(4, hoaDon.getTinhTrangHD());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(HoaDon hoaDon) throws SQLException {
        String sql = "delete from hoadon where MaHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, hoaDon.getMaHD());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
