package model;

public class KhuNha {
    private String maKhu;
    private String tenKhu;

    public KhuNha() {
    }

    public KhuNha(String maKhu, String tenKhu) {
        this.maKhu = maKhu;
        this.tenKhu = tenKhu;
    }

    public String getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(String maKhu) {
        this.maKhu = maKhu;
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }
}
