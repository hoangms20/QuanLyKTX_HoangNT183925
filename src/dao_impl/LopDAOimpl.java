package dao_impl;

import dao.LopDAO;
import model.DotDangKy;
import model.Lop;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LopDAOimpl implements LopDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public Lop getObject(ResultSet resultSet) throws SQLException {
        Lop lop = null;
        lop = new Lop(resultSet.getString("MaLop"),
                resultSet.getString("TenLop"),
                resultSet.getString("MaVien"));
        return lop;
    }

    @Override
    public List<Lop> getList(ResultSet resultSet) throws SQLException {
        List<Lop> list = new ArrayList<>();
        while (resultSet.next()) {
            Lop lop = getObject(resultSet);
            if (lop != null) list.add(lop);
        }
        return list;
    }

    @Override
    public List<Lop> findAll() throws SQLException {
        String sql = "select * from lop;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Lop> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM lop where MaLop like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Lop findByPK(String maLop) throws SQLException {
        Lop lop = null;
        String sql = "select * from lop where MaLop = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maLop);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            lop = getObject(resultSet);
        }
        return lop;
    }

    @Override
    public boolean insert(Lop lop) throws SQLException {
        String sql = "insert into lop (MaLop, TenLop, MaVien) values (?, ?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, lop.getMaLop());
        preparedStatement.setString(2, lop.getTenLop());
        preparedStatement.setString(3, lop.getMaVien());

        try {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;

    }

    @Override
    public boolean update(Lop lop) throws SQLException {
        String sql = "update lop set TenLop=?, MaVien=? where MaLop = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(3, lop.getMaLop());
        preparedStatement.setString(1, lop.getTenLop());
        preparedStatement.setString(2, lop.getMaVien());

        try {
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(Lop lop) throws SQLException {
        String sql = "delete from lop where MaLop = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, lop.getMaLop());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
