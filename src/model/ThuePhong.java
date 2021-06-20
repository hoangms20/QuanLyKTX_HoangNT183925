package model;

import java.sql.Date;

public class ThuePhong {
    private String maThue;
    private String MSSV;
    private String maP;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public ThuePhong() {
    }

    public ThuePhong(String maThue, String MSSV, String maP, Date ngayBatDau, Date ngayKetThuc) {
        this.maThue = maThue;
        this.MSSV = MSSV;
        this.maP = maP;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaThue() {
        return maThue;
    }

    public void setMaThue(String maThue) {
        this.maThue = maThue;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
