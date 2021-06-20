package model;

public class KhoanThuDotDK {
    private Integer maKhoanThuDotDK;
    private String maKhoanThu;
    private String maDotDK;
    private Integer soTienThu;

    public KhoanThuDotDK() {
    }

    public KhoanThuDotDK(Integer maKhoanThuDotDK, String maKhoanThu, String maDotDK, Integer soTienThu) {
        this.maKhoanThuDotDK = maKhoanThuDotDK;
        this.maKhoanThu = maKhoanThu;
        this.maDotDK = maDotDK;
        this.soTienThu = soTienThu;
    }

    public Integer getMaKhoanThuDotDK() {
        return maKhoanThuDotDK;
    }

    public void setMaKhoanThuDotDK(Integer maKhoanThuDotDK) {
        this.maKhoanThuDotDK = maKhoanThuDotDK;
    }

    public String getMaKhoanThu() {
        return maKhoanThu;
    }

    public void setMaKhoanThu(String maKhoanThu) {
        this.maKhoanThu = maKhoanThu;
    }

    public String getMaDotDk() {
        return maDotDK;
    }

    public void setMaDotDk(String maDotDK) {
        this.maDotDK = maDotDK;
    }

    public Integer getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(Integer soTienThu) {
        this.soTienThu = soTienThu;
    }

}
