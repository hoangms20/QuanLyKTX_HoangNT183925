package model;

import javafx.scene.control.TextField;

public class DichVuPhong {

    private String maP;
    private String maLoaiHD;
    private Integer soLuongSuDung;
    private Integer thanhTienDV;
    private TextField tfSoLuongSuDung;

    public DichVuPhong() {
    }

    public DichVuPhong(String maP, String maLoaiHD, Integer soLuongSuDung, Integer thanhTienDV) {
        this.maP = maP;
        this.maLoaiHD = maLoaiHD;
        this.soLuongSuDung = soLuongSuDung;
        this.thanhTienDV = thanhTienDV;
        this.tfSoLuongSuDung = new TextField();
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }

    public Integer getSoLuongSuDung() {
        return soLuongSuDung;
    }

    public void setSoLuongSuDung(Integer soLuongSuDung) {
        this.soLuongSuDung = soLuongSuDung;
    }

    public Integer getThanhTienDV() {
        return thanhTienDV;
    }

    public void setThanhTienDV(Integer thanhTienDV) {
        this.thanhTienDV = thanhTienDV;
    }

    public TextField getTfSoLuongSuDung() {
        return tfSoLuongSuDung;
    }

    public void setTfSoLuongSuDung(TextField tfSoLuongSuDung) {
        this.tfSoLuongSuDung = tfSoLuongSuDung;
    }
}
