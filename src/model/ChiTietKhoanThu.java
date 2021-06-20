package model;

public class ChiTietKhoanThu {
    private Integer maKhoanThuDotDK;
    private String maKhoanThuSV;
    private Integer tinhTrangKhoanThu;
    private Integer thanhTien;
    private String lyDoThuTien;

    public ChiTietKhoanThu() {
    }

    public ChiTietKhoanThu(Integer maKhoanThuDotDK, String maKhoanThuSV, Integer tinhTrangKhoanThu, Integer thanhTien, String lyDoThuTien) {
        this.maKhoanThuDotDK = maKhoanThuDotDK;
        this.maKhoanThuSV = maKhoanThuSV;
        this.tinhTrangKhoanThu = tinhTrangKhoanThu;
        this.thanhTien = thanhTien;
        this.lyDoThuTien = lyDoThuTien;
    }

    public Integer getMaKhoanThuDotDK() {
        return maKhoanThuDotDK;
    }

    public void setMaKhoanThuDotDK(Integer maKhoanThuDotDK) {
        this.maKhoanThuDotDK = maKhoanThuDotDK;
    }

    public String getMaKhoanThuSV() {
        return maKhoanThuSV;
    }

    public void setMaKhoanThuSV(String maKhoanThuSV) {
        this.maKhoanThuSV = maKhoanThuSV;
    }

    public Integer getTinhTrangKhoanThu() {
        return tinhTrangKhoanThu;
    }

    public void setTinhTrangKhoanThu(Integer tinhTrangKhoanThu) {
        this.tinhTrangKhoanThu = tinhTrangKhoanThu;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getLyDoThuTien() {
        return lyDoThuTien;
    }

    public void setLyDoThuTien(String lyDoThuTien) {
        this.lyDoThuTien = lyDoThuTien;
    }
}
