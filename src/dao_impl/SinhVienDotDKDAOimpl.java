package dao_impl;

import dao.SinhVienDotDKDAO;
import model.MyConnection;
import model.SinhVienDotDK;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDotDKDAOimpl implements SinhVienDotDKDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public SinhVienDotDK getObject(ResultSet resultSet) throws SQLException {
        SinhVienDotDK sinhVienDotDK = null;
        sinhVienDotDK = new SinhVienDotDK(resultSet.getString("MSSV"),
                resultSet.getString("MaDotDK"));
        return sinhVienDotDK;
    }

    @Override
    public List<SinhVienDotDK> getList(ResultSet resultSet) throws SQLException {
        List<SinhVienDotDK> list = new ArrayList<>();
        while (resultSet.next()) {
            SinhVienDotDK sinhVienDotDK = getObject(resultSet);
            if (sinhVienDotDK != null) list.add(sinhVienDotDK);
        }
        return list;
    }

    @Override
    public List<SinhVienDotDK> findAll() throws SQLException {
        String sql = "select * from sinhvien_dotdk;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVienDotDK> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public List<SinhVienDotDK> findByDotDK(String maDotDK) throws SQLException {
        String sql = "select * from sinhvien_dotdk where MaDotDK = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maDotDK);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public SinhVienDotDK findByMSSV(String MSSV) throws SQLException {
        SinhVienDotDK sinhVienDotDK = null;
        String sql = "select * from sinhvien_dotdk where MSSV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, MSSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            sinhVienDotDK = getObject(resultSet);
        }
        return sinhVienDotDK;
    }

    @Override
    public SinhVienDotDK findByPK(String MSSV, String maDotDK) throws SQLException {
        SinhVienDotDK sinhVienDotDK = null;
        String sql = "select * from sinhvien_dotdk where MaDotDK = ? AND MSSV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maDotDK);
        preparedStatement.setString(2, MSSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            sinhVienDotDK = getObject(resultSet);
        }
        return sinhVienDotDK;
    }

    @Override
    public boolean insert(SinhVienDotDK sinhVienDotDK) throws SQLException {
        String sql = "insert into sinhvien_dotdk (MSSV, MaDotDK) values (?, ?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, sinhVienDotDK.getMSSV());
        preparedStatement.setString(2, sinhVienDotDK.getMaDotDK());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(SinhVienDotDK sinhVienDotDK) throws SQLException {
        boolean result = false;
        String sql = "update sinhvien_dotdk set MaDotDK=? where MSSV = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(2, sinhVienDotDK.getMSSV());
        preparedStatement.setString(1, sinhVienDotDK.getMaDotDK());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(SinhVienDotDK sinhVienDotDK) throws SQLException {
        String sql = "delete from sinhvien_dotdk where MSSV = ? AND MaDotDK = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(2, sinhVienDotDK.getMaDotDK());
        preparedStatement.setString(1, sinhVienDotDK.getMSSV());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

    @Override
    public Integer countSLDK(String s) throws SQLException {
        String sql = "select count(MSSV) from sinhvien_dotdk where MaDotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, s);
        ResultSet resultSet = preparedStatement.executeQuery();
        Integer a=0;
        if(resultSet.next()){
            a = resultSet.getInt(1);
        }
        return a;
    }

}
