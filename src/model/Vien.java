package model;

public class Vien {
    private String maVien;
    private String tenVien;

    public Vien() {
    }

    public Vien(String maVien, String tenVien) {
        this.maVien = maVien;
        this.tenVien = tenVien;
    }

    public String getMaVien() {
        return maVien;
    }

    public void setMaVien(String maVien) {
        this.maVien = maVien;
    }

    public String getTenVien() {
        return tenVien;
    }

    public void setTenVien(String tenVien) {
        this.tenVien = tenVien;
    }
}
