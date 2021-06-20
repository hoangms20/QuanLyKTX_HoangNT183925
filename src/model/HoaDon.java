package model;

import javafx.scene.control.CheckBox;

import java.util.Date;

public class HoaDon {
    private String maHD;
    private String maP;
    private Date ngayLap;
    private Integer tongTien;
    private String tinhTrangHD;
    private CheckBox cbThanhToan;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maP, Date ngayLap, Integer tongTien, String tinhTrangHD) {
        this.maHD = maHD;
        this.maP = maP;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tinhTrangHD = tinhTrangHD;
        this.cbThanhToan = new CheckBox();
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public String getTinhTrangHD() {
        return tinhTrangHD;
    }

    public void setTinhTrangHD(String tinhTrangHD) {
        this.tinhTrangHD = tinhTrangHD;
    }

    public CheckBox getCbThanhToan() {
        return cbThanhToan;
    }

    public void setCbThanhToan(CheckBox cbThanhToan) {
        this.cbThanhToan = cbThanhToan;
    }
}
