package dao_impl;

import dao.DichVuPhongDAO;
import model.DichVuPhong;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuPhongDAOimpl implements DichVuPhongDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public DichVuPhong getObject(ResultSet resultSet) throws SQLException {
        DichVuPhong dichVuPhong = null;
        dichVuPhong = new DichVuPhong(resultSet.getString("MaP"),
                resultSet.getString("MaLoaiHD"),
                resultSet.getInt("SoLuongSuDung"),
                resultSet.getInt("ThanhTienDV"));
        return dichVuPhong;
    }

    @Override
    public List<DichVuPhong> getList(ResultSet resultSet) throws SQLException {
        List<DichVuPhong> list = new ArrayList<>();
        while (resultSet.next()) {
            DichVuPhong dichVuPhong = getObject(resultSet);
            if (dichVuPhong != null) list.add(dichVuPhong);
        }
        return list;
    }

    @Override
    public List<DichVuPhong> findAll() throws SQLException {
        String sql = "select * from dichvu_phong;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DichVuPhong> findByPhong(String maP) throws SQLException {
        String sql = "select * from dichvu_phong inner join phong on dichvu_phong.MaP = phong.MaP AND phong.TenP = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maP);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DichVuPhong> findLikeByPK(String s) throws SQLException {
        String sql = "select * from dichvu_phong where MaP = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public DichVuPhong findByPK(String maP, String maLoaiHD) throws SQLException {
        DichVuPhong dichVuPhong = null;
        String sql = "select * from dichvu_phong where MaLoaiHD = ? AND MaP = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maLoaiHD);
        preparedStatement.setString(2, maP);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            dichVuPhong = getObject(resultSet);
        }
        return dichVuPhong;
    }

    @Override
    public boolean insert(DichVuPhong dichVuPhong) throws SQLException {
        String sql = "insert into dichvu_phong (MaP, MaLoaiHD, SoLuongSuDung, ThanhTienDV) values (?, ?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, dichVuPhong.getMaP());
        preparedStatement.setString(2, dichVuPhong.getMaLoaiHD());
        preparedStatement.setInt(3, dichVuPhong.getSoLuongSuDung());
        preparedStatement.setInt(4, dichVuPhong.getThanhTienDV());


        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(DichVuPhong dichVuPhong) throws SQLException {
        boolean result = false;
        String sql = "update dichvu_phong set SoLuongSuDung = ?, ThanhTienDV=? where MaP = ? AND MaLoaiHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, dichVuPhong.getSoLuongSuDung());
        preparedStatement.setInt(2, dichVuPhong.getThanhTienDV());
        preparedStatement.setString(3, dichVuPhong.getMaP());
        preparedStatement.setString(4, dichVuPhong.getMaLoaiHD());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(DichVuPhong dichVuPhong) throws SQLException {
        return false;
    }

}
