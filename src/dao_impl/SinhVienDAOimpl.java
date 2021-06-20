package dao_impl;

import dao.SinhVienDAO;
import model.MyConnection;
import model.SinhVien;
import model.ThuePhong;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAOimpl implements SinhVienDAO {
    MyConnection myConnection = new MyConnection();

    @Override
    public SinhVien getObject(ResultSet resultSet) throws SQLException {
        SinhVien sinhVien = null;
        sinhVien = new SinhVien(resultSet.getString("MSSV"),
                resultSet.getString("MaUuTien"),
                resultSet.getString("MaLop"),
                resultSet.getString("MaKhoa"),
                resultSet.getInt("MaTrangThai"),
                resultSet.getString("TenSV"),
                resultSet.getDate("NgaySinh"),
                resultSet.getString("GioiTinh"),
                resultSet.getString("CMND"),
                resultSet.getString("SDT"),
                resultSet.getString("Email"),
                resultSet.getString("QueQuan"),
                resultSet.getDate("NgayDangKy"),
                resultSet.getDate("NgayRoiKhoi"),
                resultSet.getString("GhiChuSV"));
        return sinhVien;
    }

    @Override
    public List<SinhVien> getList(ResultSet resultSet) throws SQLException {
        List<SinhVien> list = new ArrayList<>();
        while (resultSet.next()) {
            SinhVien sinhVien = getObject(resultSet);
            if (sinhVien != null) list.add(sinhVien);
        }
        return list;
    }

    @Override
    public List<SinhVien> findAll() throws SQLException {
        String sql = "select * from sinhvien;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findAllPhong() throws SQLException {
        String sql = "select * from sinhvien where MaTrangThai = 2;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findByDotDK(String s) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join sinhvien_dotdk on sinhvien.MSSV = sinhvien_dotdk.MSSV AND sinhvien_dotdk.MaDotDK = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findChoDuyet(String s) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join sinhvien_dotdk on sinhvien.MSSV = sinhvien_dotdk.MSSV AND sinhvien_dotdk.MaDotDK = ? AND sinhvien.MaTrangThai = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        preparedStatement.setInt(2, 0);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findDaDuyet(String s) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join sinhvien_dotdk on sinhvien.MSSV = sinhvien_dotdk.MSSV AND sinhvien_dotdk.MaDotDK = ? AND sinhvien.MaTrangThai = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        preparedStatement.setInt(2, 1);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findAllDaDuyet() throws SQLException {
        String sql = "SELECT * FROM sinhvien where MaTrangThai = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, 1);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findLikeByPK(String s) throws SQLException {
        String sql = "SELECT * FROM sinhvien where MSSV like ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s + "%");
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findByPhong(String s, ThuePhong thuePhong) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join thuephong on thuephong.MSSV = sinhvien.MSSV inner join phong on phong.MaP = thuephong.MaP AND phong.TenP = ? AND sinhvien.MaTrangThai = 2 AND thuephong.NgayKetThuc = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        preparedStatement.setString(2, thuePhong.getNgayKetThuc().toString());
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findByKhuNha(String s, ThuePhong thuePhong) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join thuephong on thuephong.MSSV = sinhvien.MSSV inner join phong on phong.MaP = thuephong.MaP inner join khunha on phong.MaKhu = khunha.MaKhu AND khunha.TenKhu = ? AND sinhvien.MaTrangThai = 2 AND thuephong.NgayKetThuc = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, s );
        preparedStatement.setString(2, thuePhong.getNgayKetThuc().toString());
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<SinhVien> findLikeByPKDotDK(String s, String s2) throws SQLException {
        String sql = "SELECT * FROM sinhvien inner join sinhvien_dotdk on sinhvien.MSSV = sinhvien_dotdk.MSSV AND sinhvien_dotdk.MaDotDK like ? where sinhvien.MSSV like ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(2, s + "%");
        preparedStatement.setString(1, s2);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public SinhVien findByPK(String MSSV) throws SQLException {
        SinhVien sinhVien = null;
        String sql = "select * from sinhvien where MSSV = ? ;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, MSSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            sinhVien = getObject(resultSet);
        }
        return sinhVien;
    }

    @Override
    public boolean insert(SinhVien sinhVien) throws SQLException {
        String sql = "insert into sinhvien (MSSV, MaUuTien, MaLop, MaKhoa, MaTrangThai, TenSV, NgaySinh, GioiTinh, CMND, SDT, Email, QueQuan, NgayDangKy, NgayRoiKhoi, GhiChuSV) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, sinhVien.getMSSV());
        preparedStatement.setString(2, sinhVien.getMaUuTien());
        preparedStatement.setString(3, sinhVien.getMaLop());
        preparedStatement.setString(4, sinhVien.getMaKhoa());
        preparedStatement.setInt(5, sinhVien.getMaTrangThai());
        preparedStatement.setString(6, sinhVien.getTenSV());
        preparedStatement.setDate(7, (Date) sinhVien.getNgaySinh());
        preparedStatement.setString(8, sinhVien.getGioiTinh());
        preparedStatement.setString(9, sinhVien.getCMND());
        preparedStatement.setString(10, sinhVien.getSDT());
        preparedStatement.setString(11, sinhVien.getEmail());
        preparedStatement.setString(12, sinhVien.getQueQuan());
        preparedStatement.setDate(13, (Date) sinhVien.getNgayDangKy());
        preparedStatement.setDate(14, (Date) sinhVien.getNgayRoiKhoi());
        preparedStatement.setString(15, sinhVien.getGhiChuSV());

        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(SinhVien sinhVien) throws SQLException {
        boolean result = false;
        String sql = "update sinhvien set MaUuTien=?, MaLop=?, MaKhoa=?, MaTrangThai=?, TenSV=?, NgaySinh=?, GioiTinh=?, CMND=?, SDT=?, Email=?, QueQuan=?, NgayDangKy=?, NgayRoiKhoi=?, GhiChuSV=? where MSSV = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(15, sinhVien.getMSSV());
        preparedStatement.setString(1, sinhVien.getMaUuTien());
        preparedStatement.setString(2, sinhVien.getMaLop());
        preparedStatement.setString(3, sinhVien.getMaKhoa());
        preparedStatement.setInt(4, sinhVien.getMaTrangThai());
        preparedStatement.setString(5, sinhVien.getTenSV());
        preparedStatement.setDate(6, (Date) sinhVien.getNgaySinh());
        preparedStatement.setString(7, sinhVien.getGioiTinh());
        preparedStatement.setString(8, sinhVien.getCMND());
        preparedStatement.setString(9, sinhVien.getSDT());
        preparedStatement.setString(10, sinhVien.getEmail());
        preparedStatement.setString(11, sinhVien.getQueQuan());
        preparedStatement.setDate(12, (Date) sinhVien.getNgayDangKy());
        preparedStatement.setDate(13, (Date) sinhVien.getNgayRoiKhoi());
        preparedStatement.setString(14, sinhVien.getGhiChuSV());

        if (preparedStatement.executeUpdate() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(SinhVien sinhVien) throws SQLException {
        String sql = "delete from sinhvien where MSSV = ?;";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, sinhVien.getMSSV());
        if (preparedStatement.executeUpdate() > 0){
            return true;
        }
        return false;
    }

}
