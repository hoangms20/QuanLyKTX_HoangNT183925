package model;

public class Phong {
    private String maP;
    private String tenP;
    private String maKhu;
    private Integer tang;
    private Integer SLToiDa;
    private Integer SLDangO;
    private String ghiChuP;
    private String tinhTrangPhong;
    private String loaiP;

    public Phong() {
    }

    public Phong(String maP, String maKhu, Integer tang, String tenP, Integer SLToiDa, Integer SLDangO, String ghiChuP, String tinhTrangPhong, String loaiP) {
        this.maP = maP;
        this.tenP = tenP;
        this.maKhu = maKhu;
        this.tang = tang;
        this.SLToiDa = SLToiDa;
        this.SLDangO = SLDangO;
        this.ghiChuP = ghiChuP;
        this.tinhTrangPhong = tinhTrangPhong;
        this.loaiP = loaiP;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getTenP() {
        return tenP;
    }

    public void setTenP(String tenP) {
        this.tenP = tenP;
    }

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public Integer getTang() {
        return tang;
    }

    public void setTang(Integer tang) {
        this.tang = tang;
    }

    public Integer getSLToiDa() {
        return SLToiDa;
    }

    public void setSLToiDa(Integer SLToiDa) {
        this.SLToiDa = SLToiDa;
    }

    public Integer getSLDangO() {
        return SLDangO;
    }

    public void setSLDangO(Integer SLDangO) {
        this.SLDangO = SLDangO;
    }

    public String getGhiChuP() {
        return ghiChuP;
    }

    public void setGhiChuP(String ghiChuP) {
        this.ghiChuP = ghiChuP;
    }

    public String getTinhTrangPhong() {
        return tinhTrangPhong;
    }

    public void setTinhTrangPhong(String tinhTrangPhong) {
        this.tinhTrangPhong = tinhTrangPhong;
    }

    public String getLoaiP() {
        return loaiP;
    }

    public void setLoaiP(String loaiP) {
        this.loaiP = loaiP;
    }
}
