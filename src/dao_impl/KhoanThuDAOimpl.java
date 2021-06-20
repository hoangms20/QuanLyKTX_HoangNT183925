package dao_impl;

import dao.KhoanThuDAO;
import model.KhoanThu;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuDAOimpl implements KhoanThuDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public KhoanThu getObject(ResultSet resultSet) throws SQLException {
        KhoanThu khoanThu = null;
        khoanThu = new KhoanThu(resultSet.getString("MaKhoanThu"),
                resultSet.getString("TenKhoanThu"));
        return khoanThu;
    }

    @Override
    public List<KhoanThu> getList(ResultSet resultSet) throws SQLException {
        List<KhoanThu> list = new ArrayList<>();
        while (resultSet.next()) {
            KhoanThu khoanThu = getObject(resultSet);
            if (khoanThu != null) list.add(khoanThu);
        }
        return list;
    }

    @Override
    public List<KhoanThu> findAll() throws SQLException {
        String sql = "select * from khoanthu;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThu> findLikeByPK(String s) throws SQLException {
        String sql = "select * from khoanthu where MaKhoanThu like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1,s +"%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public KhoanThu findByPK(String maKhoanThu) throws SQLException {
        KhoanThu khoanThu = null;
        String sql = "select * from khoanthu where MaKhoanThu = ?  ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maKhoanThu);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            khoanThu = getObject(resultSet);
        }
        return khoanThu;
    }

    @Override
    public boolean insert(KhoanThu khoanThu) throws SQLException {
        String sql = "insert into khoanthu (MaKhoanThu, TenKhoanThu) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoanThu.getMaKhoanThu());
        preparedStatement.setString(2, khoanThu.getTenKhoanThu());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(KhoanThu khoanThu) throws SQLException {
        boolean result = false;
        String sql = "update khoanthu set TenKhoanThu = ? where MaKhoanThu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(2, khoanThu.getMaKhoanThu());
        preparedStatement.setString(1, khoanThu.getTenKhoanThu());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(KhoanThu khoanThu) throws SQLException {
        String sql = "delete from khoanthu where MaKhoanThu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoanThu.getMaKhoanThu());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
