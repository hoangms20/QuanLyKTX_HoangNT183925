package model;

public class ChiTietHoaDon {
    private String maLoaiHD;
    private String maHD;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maLoaiHD, String maHD) {
        this.maLoaiHD = maLoaiHD;
        this.maHD = maHD;
    }

    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }
}
