package model;

import javafx.scene.control.TextField;

import java.sql.Date;

public class DienNuoc {

    private Date thangNamGhiSoDN;
    private String maLoaiHD;
    private String maP;
    private Integer chiSoCuDN;
    private Integer chiSoMoiDN;
    private Integer SLTieuThu;
    private Integer thanhTien;
    private TextField tfChiSoMoi;

    public DienNuoc() {
    }

    public DienNuoc(Date thangNamGhiSoDN, String maLoaiHD, String maP, Integer chiSoCuDN, Integer chiSoMoiDN, Integer SLTieuThu, Integer thanhTien) {
        this.thangNamGhiSoDN = thangNamGhiSoDN;
        this.maLoaiHD = maLoaiHD;
        this.maP = maP;
        this.chiSoCuDN = chiSoCuDN;
        this.chiSoMoiDN = chiSoMoiDN;
        this.SLTieuThu = SLTieuThu;
        this.thanhTien = thanhTien;
        this.tfChiSoMoi = new TextField();
    }

    public Date getThangNamGhiSoDN() {
        return thangNamGhiSoDN;
    }

    public void setThangNamGhiSoDN(Date thangNamGhiSoDN) {
        this.thangNamGhiSoDN = thangNamGhiSoDN;
    }

    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public Integer getChiSoCuDN() {
        return chiSoCuDN;
    }

    public void setChiSoCuDN(Integer chiSoCuDN) {
        this.chiSoCuDN = chiSoCuDN;
    }

    public Integer getChiSoMoiDN() {
        return chiSoMoiDN;
    }

    public void setChiSoMoiDN(Integer chiSoMoiDN) {
        this.chiSoMoiDN = chiSoMoiDN;
    }

    public Integer getSLTieuThu() {
        return SLTieuThu;
    }

    public void setSLTieuThu(Integer SLTieuThu) {
        this.SLTieuThu = SLTieuThu;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }

    public TextField getTfChiSoMoi() {
        return tfChiSoMoi;
    }

    public void setTfChiSoMoi(TextField tfChiSoMoi) {
        this.tfChiSoMoi = tfChiSoMoi;
    }
}
