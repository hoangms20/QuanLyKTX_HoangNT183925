package model;

import javafx.scene.control.CheckBox;

import java.util.Date;

public class KhoanThuSV {
    private String maKhoanThuSV;
    private String MSSV;
    private Date ngayThu;
    private Integer tongTienThu;
    private Integer tinhTrangThanhToan;
    private String trangThai;
    private CheckBox selected;

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        if (tinhTrangThanhToan == 0){
            this.trangThai = "Chưa thanh toán";
        }else {
            this.trangThai = "Đã thanh toán";
        }
    }

    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    public KhoanThuSV() {
    }

    public KhoanThuSV(String maKhoanThuSV, String MSSV, Date ngayThu, Integer tongTienThu, Integer tinhTrangThanhToan) {
        this.maKhoanThuSV = maKhoanThuSV;
        this.MSSV = MSSV;
        this.ngayThu = ngayThu;
        this.tongTienThu = tongTienThu;
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        this.selected = new CheckBox();
        if (tinhTrangThanhToan == 0){
            this.trangThai = "Chưa thanh toán";
        }else {
            this.trangThai = "Đã thanh toán";
        }

    }

    public String getMaKhoanThuSV() {
        return maKhoanThuSV;
    }

    public void setMaKhoanThuSV(String maKhoanThuSV) {
        this.maKhoanThuSV = maKhoanThuSV;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public Date getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(Date ngayThu) {
        this.ngayThu = ngayThu;
    }

    public Integer getTongTienThu() {
        return tongTienThu;
    }

    public void setTongTienThu(Integer tongTienThu) {
        this.tongTienThu = tongTienThu;
    }

    public Integer getTinhTrangThanhToan() {
        return tinhTrangThanhToan;
    }

    public void setTinhTrangThanhToan(Integer tinhTrangThanhToan) {
        this.tinhTrangThanhToan = tinhTrangThanhToan;
        if (tinhTrangThanhToan == 0){
            this.trangThai = "Chưa thanh toán";
        }else {
            this.trangThai = "Đã thanh toán";
        }
    }
}
