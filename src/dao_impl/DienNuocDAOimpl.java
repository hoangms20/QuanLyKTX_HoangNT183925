package dao_impl;

import dao.DienNuocDAO;
import model.DienNuoc;
import model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DienNuocDAOimpl implements DienNuocDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public DienNuoc getObject(ResultSet resultSet) throws SQLException {
        DienNuoc dienNuoc = null;
        dienNuoc = new DienNuoc(resultSet.getDate("ThangNamGhiSoDN"),
                resultSet.getString("MaLoaiHD"),
                resultSet.getString("MaP"),
                resultSet.getInt("ChiSoCuDN"),
                resultSet.getInt("ChiSoMoiDN"),
                resultSet.getInt("SLTieuThu"),
                resultSet.getInt("ThanhTien"));
        return dienNuoc;
    }

    @Override
    public List<DienNuoc> getList(ResultSet resultSet) throws SQLException {
        List<DienNuoc> list = new ArrayList<>();
        while (resultSet.next()) {
            DienNuoc dienNuoc = getObject(resultSet);
            if (dienNuoc != null) list.add(dienNuoc);
        }
        return list;
    }

    @Override
    public List<DienNuoc> findAll() throws SQLException {
        String sql = "select * from diennuoc;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findAllDateGanNhat(DienNuoc dienNuoc) throws SQLException {
        String sql = "select * from diennuoc where ThangNamGhiSoDN = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setDate(1, dienNuoc.getThangNamGhiSoDN());
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findAllDate() throws SQLException {
        String sql = "select * from diennuoc where MaP = ? AND MaLoaiHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, "A101");
        preparedStatement.setString(2, "LHD01");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findKhuNhaDateGanNhat(DienNuoc dienNuoc, String s) throws SQLException {
        String sql = "select * from diennuoc inner join phong on phong.MaP = diennuoc.MaP inner join khunha on khunha.MaKhu = phong.MaKhu AND khunha.TenKhu like ? AND diennuoc.ThangNamGhiSoDN = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setDate(2, dienNuoc.getThangNamGhiSoDN());
        preparedStatement.setString(1, s );
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findPhongDateGanNhat(DienNuoc dienNuoc, String s) throws SQLException {
        String sql = "select * from diennuoc inner join phong on phong.MaP = diennuoc.MaP AND phong.TenP like ? AND diennuoc.ThangNamGhiSoDN = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setDate(2, dienNuoc.getThangNamGhiSoDN());
        preparedStatement.setString(1, s );
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findDienGanNhat() throws SQLException {
        String sql = "select * from diennuoc order by ThangNamGhiSoDN DESC LIMIT 1;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String s="";
        if(resultSet.next()){
            DienNuoc d = getObject(resultSet);
            s = d.getThangNamGhiSoDN().toString();
        }
        sql = "select * from diennuoc where ThangNamGhiSoDN = ? AND MaLoaiHD = ?;";
        preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, s);
        preparedStatement.setString(2,"LHD01");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findNuocGanNhat() throws SQLException {
        String sql = "select * from diennuoc order by ThangNamGhiSoDN DESC LIMIT 1;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String s="";
        if(resultSet.next()){
            DienNuoc d = getObject(resultSet);
            s = d.getThangNamGhiSoDN().toString();
        }
        sql = "select * from diennuoc where ThangNamGhiSoDN = ? AND MaLoaiHD = ?;";
        preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, s);
        preparedStatement.setString(2,"LHD02");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<DienNuoc> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public DienNuoc findDateGanNhat() throws SQLException {
        DienNuoc dienNuoc = null;
        String sql = "select * from diennuoc order by ThangNamGhiSoDN DESC LIMIT 1;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            dienNuoc = getObject(resultSet);
        }
        return dienNuoc;
    }

    @Override
    public DienNuoc findByPK(Date thangNamGhiSoDN, String maLoaiHD, String maP) throws SQLException {
        DienNuoc dienNuoc = null;
        String sql = "select * from diennuoc where ThangNamGhiSoDN =? AND maLoaiHD = ? AND maP = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setDate(1, thangNamGhiSoDN);
        preparedStatement.setString(2, maLoaiHD);
        preparedStatement.setString(3, maP);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            dienNuoc = getObject(resultSet);
        }
        return dienNuoc;
    }

    @Override
    public boolean insert(DienNuoc dienNuoc) throws SQLException {
        String sql = "insert into diennuoc (ThangNamGhiSoDN, MaLoaiHD, MaP, ChiSoCuDN, ChiSoMoiDN, SLTieuThu,ThanhTien) values (?, ?,?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setDate(1, (Date) dienNuoc.getThangNamGhiSoDN());
        preparedStatement.setString(2, dienNuoc.getMaLoaiHD());
        preparedStatement.setString(3, dienNuoc.getMaP());
        preparedStatement.setInt(4, dienNuoc.getChiSoCuDN());
        preparedStatement.setInt(5, dienNuoc.getChiSoMoiDN());
        preparedStatement.setInt(6, dienNuoc.getSLTieuThu());
        preparedStatement.setInt(7, dienNuoc.getThanhTien());


        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(DienNuoc dienNuoc) throws SQLException {
        boolean result = false;
        String sql = "update diennuoc set ChiSoCuDN = ?,ChiSoMoiDN=?, SLTieuThu=?,ThanhTien=?,ThanhTienDN=? where ThangNamGhiSoDN = ? AND MaP = ? AND MaLoaiHD = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setDate(5, (Date) dienNuoc.getThangNamGhiSoDN());
        preparedStatement.setString(6, dienNuoc.getMaP());
        preparedStatement.setString(7, dienNuoc.getMaLoaiHD());
        preparedStatement.setInt(1, dienNuoc.getChiSoCuDN());
        preparedStatement.setInt(2, dienNuoc.getChiSoMoiDN());
        preparedStatement.setInt(3, dienNuoc.getSLTieuThu());
        preparedStatement.setInt(4, dienNuoc.getThanhTien());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(DienNuoc dienNuoc) throws SQLException {
        return false;
    }

}
