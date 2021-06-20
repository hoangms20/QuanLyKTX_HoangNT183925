package dao_impl;

import dao.ChiTietKhoanThuDAO;
import model.ChiTietKhoanThu;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietKhoanThuDAOimpl implements ChiTietKhoanThuDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public ChiTietKhoanThu getObject(ResultSet resultSet) throws SQLException {
        ChiTietKhoanThu chiTietKhoanThu = null;
        chiTietKhoanThu = new ChiTietKhoanThu(resultSet.getInt("MaKhoanThu_DotDK"),
                resultSet.getString("MaKhoanThu_SV"),
                resultSet.getInt("TinhTrang_KhoanThu"),
                resultSet.getInt("ThanhTien"),
                resultSet.getString("LyDoThu"));
        return chiTietKhoanThu;
    }

    @Override
    public List<ChiTietKhoanThu> getList(ResultSet resultSet) throws SQLException {
        List<ChiTietKhoanThu> list = new ArrayList<>();
        while (resultSet.next()) {
            ChiTietKhoanThu chiTietKhoanThu = getObject(resultSet);
            if (chiTietKhoanThu != null) list.add(chiTietKhoanThu);
        }
        return list;
    }

    @Override
    public List<ChiTietKhoanThu> findAll() throws SQLException {
        String sql = "select * from chitietkhoanthu;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<ChiTietKhoanThu> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public ChiTietKhoanThu findByPK(String maKhoanThuDotDK, String maKhoanThuSV) throws SQLException {
        ChiTietKhoanThu chiTietKhoanThu = null;
        String sql = "select * from chitietkhoanthu where MaKhoanThu_DotDK = ? AND MaKhoanThu_SV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maKhoanThuDotDK);
        preparedStatement.setString(2, maKhoanThuSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            chiTietKhoanThu = getObject(resultSet);
        }
        return chiTietKhoanThu;
    }

    @Override
    public boolean insert(ChiTietKhoanThu chiTietKhoanThu) throws SQLException {
        String sql = "insert into chitietkhoanthu (MaKhoanThu_DotDK, MaKhoanThu_SV, TinhTrang_KhoanThu, ThanhTien, LyDoThu) values (?, ?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, chiTietKhoanThu.getMaKhoanThuDotDK());
        preparedStatement.setString(2, chiTietKhoanThu.getMaKhoanThuSV());
        preparedStatement.setInt(3, chiTietKhoanThu.getTinhTrangKhoanThu());
        preparedStatement.setInt(4, chiTietKhoanThu.getThanhTien());
        preparedStatement.setString(5, chiTietKhoanThu.getLyDoThuTien());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ChiTietKhoanThu chiTietKhoanThu) throws SQLException {
        boolean result = false;
        String sql = "update chitietkhoanthu set TinhTrang_KhoanThu = ?, ThanhTien =?,LyDoThu=? where MaKhoanThu_DotDK = ? AND MaKhoanThu_SV = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, chiTietKhoanThu.getTinhTrangKhoanThu());
        preparedStatement.setInt(2, chiTietKhoanThu.getThanhTien());
        preparedStatement.setString(3, chiTietKhoanThu.getLyDoThuTien());
        preparedStatement.setInt(4, chiTietKhoanThu.getMaKhoanThuDotDK());
        preparedStatement.setString(5, chiTietKhoanThu.getMaKhoanThuSV());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(ChiTietKhoanThu chiTietKhoanThu) throws SQLException {
        return false;
    }

}
