package dao_impl;

import dao.KhuNhaDAO;
import model.DotDangKy;
import model.KhuNha;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhuNhaDAOimpl implements KhuNhaDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public KhuNha getObject(ResultSet resultSet) throws SQLException {
        KhuNha khuNha = null;
        khuNha = new KhuNha(resultSet.getString("MaKhu"),
                resultSet.getString("TenKhu"));
        return khuNha;
    }

    @Override
    public List<KhuNha> getList(ResultSet resultSet) throws SQLException {
        List<KhuNha> list = new ArrayList<>();
        while (resultSet.next()) {
            KhuNha khuNha = getObject(resultSet);
            if (khuNha != null) list.add(khuNha);
        }
        return list;
    }

    @Override
    public List<KhuNha> findAll() throws SQLException {
        String sql = "select * from khunha;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhuNha> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM khunha where MaKhu like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public KhuNha findByPK(String maKhu) throws SQLException {
        KhuNha khuNha = null;
        String sql = "select * from khunha where MaKhu = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maKhu);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            khuNha = getObject(resultSet);
        }
        return khuNha;
    }

    @Override
    public boolean insert(KhuNha khuNha) throws SQLException {
        String sql = "insert into khunha (MaKhu, TenKhu) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khuNha.getMaKhu());
        preparedStatement.setString(2, khuNha.getTenKhu());

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
    public boolean update(KhuNha khuNha) throws SQLException {
        String sql = "update khunha set TenKhu=? where MaKhu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(2, khuNha.getMaKhu());
        preparedStatement.setString(1, khuNha.getTenKhu());

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
    public boolean delete(KhuNha khuNha) throws SQLException {
        String sql = "delete from khunha where MaKhu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khuNha.getMaKhu());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Integer countSLPhong(KhuNha khuNha) throws SQLException{
        String sql = "select count(MaP) from phong where MaKhu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khuNha.getMaKhu());
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer a=0;
        if(resultSet.next()){
            a = resultSet.getInt(1);
        }
        return a;
    }

    @Override
    public Integer countSLConTrongKhuNha(KhuNha khuNha) throws SQLException{
        String sql = "select sum(SLToiDa) from phong where MaKhu = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khuNha.getMaKhu());
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer a=0,b=0;
        if(resultSet.next()){
            a = resultSet.getInt(1);
        }

        sql = "select sum(SLDangO) from phong where MaKhu = ?;";
        preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khuNha.getMaKhu());
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            b = resultSet.getInt(1);
        }

        return a - b;
    }

}
