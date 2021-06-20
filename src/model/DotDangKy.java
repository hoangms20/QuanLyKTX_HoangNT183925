package model;

import java.sql.Date;

public class DotDangKy {
    private String maDotDK;
    private String tenDotDK;
    private Date ngayBatDauDK;
    private Date ngayKetThucDK;
    private Integer chiTieu;
    private Integer SLDangKy;

    public DotDangKy() {
    }

    public DotDangKy(String maDotDK, String tenDotDK, Date ngayBatDauDK, Date ngayKetThucDK, Integer chiTieu, Integer SLDangKi) {
        this.maDotDK = maDotDK;
        this.tenDotDK = tenDotDK;
        this.ngayBatDauDK = ngayBatDauDK;
        this.ngayKetThucDK = ngayKetThucDK;
        this.chiTieu = chiTieu;
        this.SLDangKy = SLDangKi;
    }

    public String getMaDotDK() {
        return maDotDK;
    }

    public void setMaDotDK(String maDotDK) {
        this.maDotDK = maDotDK;
    }

    public String getTenDotDK() {
        return tenDotDK;
    }

    public void setTenDotDK(String tenDotDK) {
        this.tenDotDK = tenDotDK;
    }

    public Date getNgayBatDauDK() {
        return ngayBatDauDK;
    }

    public void setNgayBatDauDK(Date ngayBatDauDK) {
        this.ngayBatDauDK = ngayBatDauDK;
    }

    public Date getNgayKetThucDK() {
        return ngayKetThucDK;
    }

    public void setNgayKetThucDK(Date ngayKetThucDK) {
        this.ngayKetThucDK = ngayKetThucDK;
    }

    public Integer getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(Integer chiTieu) {
        this.chiTieu = chiTieu;
    }

    public Integer getSLDangKi() {
        return SLDangKy;
    }

    public void setSLDangKi(Integer SLDangKi) {
        this.SLDangKy = SLDangKi;
    }
}
