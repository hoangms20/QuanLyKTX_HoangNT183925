package dao_impl;

import dao.KhoanThuDotDKDAO;
import model.KhoanThuDotDK;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuDotDKDAOimpl implements KhoanThuDotDKDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public KhoanThuDotDK getObject(ResultSet resultSet) throws SQLException {
        KhoanThuDotDK khoanThuDotDK = null;
        khoanThuDotDK = new KhoanThuDotDK(resultSet.getInt("MaKhoanThu_DotDK"),
                resultSet.getString("MaKhoanThu"),
                resultSet.getString("MaDotDK"),
                resultSet.getInt("SoTienThu"));
        return khoanThuDotDK;
    }

    @Override
    public List<KhoanThuDotDK> getList(ResultSet resultSet) throws SQLException {
        List<KhoanThuDotDK> list = new ArrayList<>();
        while (resultSet.next()) {
            KhoanThuDotDK khoanThuDotDK = getObject(resultSet);
            if (khoanThuDotDK != null) list.add(khoanThuDotDK);
        }
        return list;
    }

    @Override
    public List<KhoanThuDotDK> findAll() throws SQLException {
        String sql = "select * from khoanthu_dotdk;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuDotDK> findByDDK(String s) throws SQLException {
        String sql = "select * from khoanthu_dotdk where MaDotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuDotDK> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public KhoanThuDotDK findByPK(Integer maKhoanThuDotDK) throws SQLException {
        KhoanThuDotDK khoanThuDotDK = null;
        String sql = "select * from khoanthu_dotdk where MaKhoanThuDotDK = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, maKhoanThuDotDK);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            khoanThuDotDK = getObject(resultSet);
        }
        return khoanThuDotDK;
    }

    @Override
    public boolean insert(KhoanThuDotDK khoanThuDotDK) throws SQLException {
        String sql = "insert into khoanthu_dotdk (MaKhoanThu, MaDotDK, SoTienThu) values (?, ?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoanThuDotDK.getMaKhoanThu());
        preparedStatement.setString(2, khoanThuDotDK.getMaDotDk());
        preparedStatement.setInt(3, khoanThuDotDK.getSoTienThu());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(KhoanThuDotDK khoanThuDotDK) throws SQLException {
        boolean result = false;
        String sql = "update khoanthu_dotdk set MaKhoanThu=?, MaDotDK=?, SoTienThu=? where MaKhoanThu_DotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(4, khoanThuDotDK.getMaKhoanThuDotDK());
        preparedStatement.setString(1, khoanThuDotDK.getMaKhoanThu());
        preparedStatement.setString(2, khoanThuDotDK.getMaDotDk());
        preparedStatement.setInt(3, khoanThuDotDK.getSoTienThu());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(KhoanThuDotDK khoanThuDotDK) throws SQLException {
        String sql = "delete from khoanthu_dotdk where MaKhoanThu_DotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, khoanThuDotDK.getMaKhoanThuDotDK());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
