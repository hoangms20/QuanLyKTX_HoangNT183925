package dao_impl;

import dao.KhoanThuSVDAO;
import model.KhoanThuSV;
import model.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoanThuSVDAOimpl implements KhoanThuSVDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public KhoanThuSV getObject(ResultSet resultSet) throws SQLException {
        KhoanThuSV khoanThuSV = null;
        khoanThuSV = new KhoanThuSV(resultSet.getString("MaKhoanThu_SV"),
                resultSet.getString("MSSV"),
                resultSet.getDate("NgayThu"),
                resultSet.getInt("TongTienThu"),
                resultSet.getInt("TinhTrangThanhToan"));
        return khoanThuSV;
    }

    @Override
    public List<KhoanThuSV> getList(ResultSet resultSet) throws SQLException {
        List<KhoanThuSV> list = new ArrayList<>();
        while (resultSet.next()) {
            KhoanThuSV khoanThuSV = getObject(resultSet);
            if (khoanThuSV != null) list.add(khoanThuSV);
        }
        return list;
    }

    @Override
    public List<KhoanThuSV> findAll() throws SQLException {
        String sql = "select * from khoanthu_sv;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuSV> findDotDK(String s) throws SQLException {
        String sql = "select * from khoanthu_sv where MaKhoanThu_SV like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuSV> findDotDKAndLikeMSSV(String s, String mssv) throws SQLException {
        String sql = "select * from khoanthu_sv where MaKhoanThu_SV like ? AND MSSV like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        preparedStatement.setString(2, mssv + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuSV> findDotDKAndLikeMSSVAndTT(String s, String mssv, int i) throws SQLException {
        String sql = "select * from khoanthu_sv where MaKhoanThu_SV like ? AND MSSV like ? AND TinhTrangThanhToan = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        preparedStatement.setString(2, mssv + "%");
        preparedStatement.setInt(3, i);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuSV> findDotDKAndThanhToan(String s, int tinhTrangThanhToan) throws SQLException {
        String sql = "select * from khoanthu_sv where MaKhoanThu_SV like ? AND TinhTrangThanhToan = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        preparedStatement.setInt(2, tinhTrangThanhToan);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<KhoanThuSV> findLikeByPK(String s) throws SQLException {
        return null;
    }

    @Override
    public KhoanThuSV findByPK(String maKhoanThuSV) throws SQLException {
        KhoanThuSV khoanThuSV = null;
        String sql = "select * from khoanthu_sv where MaKhoanThu_SV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, maKhoanThuSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            khoanThuSV = getObject(resultSet);
        }
        return khoanThuSV;
    }

    @Override
    public boolean insert(KhoanThuSV khoanThuSV) throws SQLException {
        String sql = "insert into khoanthu_sv (MaKhoanThu_SV, MSSV, NgayThu, TongTienThu, TinhTrangThanhToan) values (?, ?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoanThuSV.getMaKhoanThuSV());
        preparedStatement.setString(2, khoanThuSV.getMSSV());
        preparedStatement.setDate(3, (Date) khoanThuSV.getNgayThu());
        preparedStatement.setInt(4, khoanThuSV.getTongTienThu());
        preparedStatement.setInt(5, khoanThuSV.getTinhTrangThanhToan());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(KhoanThuSV khoanThuSV) throws SQLException {
        boolean result = false;
        String sql = "update khoanthu_sv set MSSV=?, NgayThu=?, TongTienThu=?, TinhTrangThanhToan=? where MaKhoanThu_SV= ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(5, khoanThuSV.getMaKhoanThuSV());
        preparedStatement.setString(1, khoanThuSV.getMSSV());
        preparedStatement.setDate(2, (Date) khoanThuSV.getNgayThu());
        preparedStatement.setInt(3, khoanThuSV.getTongTienThu());
        preparedStatement.setInt(4, khoanThuSV.getTinhTrangThanhToan());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(KhoanThuSV khoanThuSV) throws SQLException {
        String sql = "delete from khoanthu_sv where MaKhoanThu_SV= ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, khoanThuSV.getMaKhoanThuSV());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
