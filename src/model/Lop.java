package model;

public class Lop {
    private String maLop;
    private String tenLop;
    private String maVien;

    public Lop() {
    }

    public Lop(String maLop, String tenLop, String MSSV) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maVien = MSSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMaVien() {
        return maVien;
    }

    public void setMaVien(String maVien) {
        this.maVien = maVien;
    }
}
