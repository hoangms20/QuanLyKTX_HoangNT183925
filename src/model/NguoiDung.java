package model;


public class NguoiDung {
    private String tenDangNhap;
    private String hoTen;
    private String matKhau;
    private String nhomNguoiDung;

    public NguoiDung() {
    }

    public NguoiDung(String tenDangNhap, String hoTen, String matKhau, String nhomNguoiDung) {
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.nhomNguoiDung = nhomNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNhomNguoiDung() {
        return nhomNguoiDung;
    }

    public void setNhomNguoiDung(String nhomNguoiDung) {
        this.nhomNguoiDung = nhomNguoiDung;
    }
}
