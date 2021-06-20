package model;

public class UuTien {
    private String maUuTien;
    private String tenUuTien;
    private Integer mucDoUuTien;
    private String ghiChuUuTien;

    public UuTien() {
    }

    public UuTien(String maUuTien, String tenUuTien, Integer mucDoUuTien, String ghiChuUuTien) {
        this.maUuTien = maUuTien;
        this.tenUuTien = tenUuTien;
        this.mucDoUuTien = mucDoUuTien;
        this.ghiChuUuTien = ghiChuUuTien;
    }

    public String getMaUuTien() {
        return maUuTien;
    }

    public void setMaUuTien(String maUuTien) {
        this.maUuTien = maUuTien;
    }

    public String getTenUuTien() {
        return tenUuTien;
    }

    public void setTenUuTien(String tenUuTien) {
        this.tenUuTien = tenUuTien;
    }

    public Integer getMucDoUuTien() {
        return mucDoUuTien;
    }

    public void setMucDoUuTien(Integer mucDoUuTien) {
        this.mucDoUuTien = mucDoUuTien;
    }

    public String getGhiChuUuTien() {
        return ghiChuUuTien;
    }

    public void setGhiChuUuTien(String ghiChuUuTien) {
        this.ghiChuUuTien = ghiChuUuTien;
    }
}
