package model;

public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private Integer namVaoHoc;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, Integer namVaoHoc) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.namVaoHoc = namVaoHoc;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public Integer getNamVaoHoc() {
        return namVaoHoc;
    }

    public void setNamVaoHoc(Integer namVaoHoc) {
        this.namVaoHoc = namVaoHoc;
    }
}
