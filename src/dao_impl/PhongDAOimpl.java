package dao_impl;

import dao.PhongDAO;
import model.MyConnection;
import model.Phong;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongDAOimpl implements PhongDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public Phong getObject(ResultSet resultSet) throws SQLException {
        Phong phong = null;
        phong = new Phong(resultSet.getString("MaP"),
                resultSet.getString("MaKhu"),
                resultSet.getInt("Tang"),
                resultSet.getString("TenP"),
                resultSet.getInt("SLToiDa"),
                resultSet.getInt("SLDangO"),
                resultSet.getString("GhiChuP"),
                resultSet.getString("TinhTrangPhong"),
                resultSet.getString("LoaiP"));
        return phong;
    }

    @Override
    public List<Phong> getList(ResultSet resultSet) throws SQLException {
        List<Phong> list = new ArrayList<>();
        while (resultSet.next()) {
            Phong phong = getObject(resultSet);
            if (phong != null) list.add(phong);
        }
        return list;
    }

    @Override
    public List<Phong> findAll() throws SQLException {
        String sql = "select * from phong;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Phong> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM phong where MaP like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Phong> findByKhuNha(String s) throws SQLException {
        String sql = "SELECT * FROM phong where MaKhu like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Phong findByPK(String maP) throws SQLException {
        Phong phong = null;
        String sql = "select * from phong where MaP = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maP);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            phong = getObject(resultSet);
        }
        return phong;
    }

    @Override
    public boolean insert(Phong phong) throws SQLException {
        String sql = "insert into phong (MaP, MaKhu, Tang, TenP, SLToiDa, SLDangO, GhiChuP, TinhTrangPhong, LoaiP) values (?, ?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, phong.getMaP());
        preparedStatement.setString(2, phong.getMaKhu());
        preparedStatement.setInt(3, phong.getTang());
        preparedStatement.setString(4, phong.getTenP());
        preparedStatement.setInt(5, phong.getSLToiDa());
        preparedStatement.setInt(6, phong.getSLDangO());
        preparedStatement.setString(7, phong.getGhiChuP());
        preparedStatement.setString(8, phong.getTinhTrangPhong());
        preparedStatement.setString(9, phong.getLoaiP());

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
    public boolean update(Phong phong) throws SQLException {
        String sql = "update phong set MaKhu=?, Tang=?, TenP=?, SLToiDa=?, SLDangO=?, GhiChuP=?, TinhTrangPhong=?, LoaiP=? where MaP = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(9, phong.getMaP());
        preparedStatement.setInt(2, phong.getTang());
        preparedStatement.setString(1, phong.getMaKhu());
        preparedStatement.setString(3, phong.getTenP());
        preparedStatement.setInt(4, phong.getSLToiDa());
        preparedStatement.setInt(5, phong.getSLDangO());
        preparedStatement.setString(6, phong.getGhiChuP());
        preparedStatement.setString(7, phong.getTinhTrangPhong());
        preparedStatement.setString(8, phong.getLoaiP());

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
    public boolean delete(Phong phong) throws SQLException {
        String sql = "delete from phong where MaP = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, phong.getMaP());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Integer countSLConTrong() throws SQLException{
        String sql = "select sum(SLToiDa) from phong ;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer a=0,b=0;
        if(resultSet.next()){
            a = resultSet.getInt(1);
        }

        sql = "select sum(SLDangO) from phong ;";
        preparedStatement = myConnection.prepareUpdate(sql);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            b = resultSet.getInt(1);
        }

        return a - b;
    }



}
