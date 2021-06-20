package model;

import javafx.scene.control.CheckBox;

import java.sql.Date;

public class SinhVien {
    private String MSSV;
    private String maUuTien;
    private String maLop;
    private String maKhoa;
    private Integer maTrangThai;
    private String tenSV;
    private Date ngaySinh;
    private String gioiTinh;
    private String CMND;
    private String SDT;
    private String email;
    private String queQuan;
    private Date ngayDangKy;
    private Date ngayRoiKhoi;
    private String ghiChuSV;
    private CheckBox select;

    public SinhVien() {
    }

    public SinhVien(String MSSV, String maUuTien, String maLop, String maKhoa, Integer maTrangThai, String tenSV, Date ngaySinh, String gioiTinh, String CMND, String SDT, String email, String queQuan, Date ngayDangKy, Date ngayRoiKhoi, String ghiChuSV) {
        this.MSSV = MSSV;
        this.maUuTien = maUuTien;
        this.maLop = maLop;
        this.maKhoa = maKhoa;
        this.maTrangThai = maTrangThai;
        this.tenSV = tenSV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.SDT = SDT;
        this.email = email;
        this.queQuan = queQuan;
        this.ngayDangKy = ngayDangKy;
        this.ngayRoiKhoi = ngayRoiKhoi;
        this.ghiChuSV = ghiChuSV;
        this.select = new CheckBox();
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaUuTien() {
        return maUuTien;
    }

    public void setMaUuTien(String maUuTien) {
        this.maUuTien = maUuTien;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public Integer getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(Integer maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Date getNgayRoiKhoi() {
        return ngayRoiKhoi;
    }

    public void setNgayRoiKhoi(Date ngayRoiKhoi) {
        this.ngayRoiKhoi = ngayRoiKhoi;
    }

    public String getGhiChuSV() {
        return ghiChuSV;
    }

    public void setGhiChuSV(String ghiChuSV) {
        this.ghiChuSV = ghiChuSV;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
