package model;

import javafx.scene.control.CheckBox;

import java.sql.Date;

public class DonXinRa {
    private String maDonXinRa;
    private String MSSV;
    private Date ngayLapDon;
    private String lyDo;
    private Integer xetDuyet;
    private String trangThai;
    private CheckBox select;

    public DonXinRa() {
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai() {
        if(xetDuyet == 0){
            trangThai = "Đang chờ";
        }else {
            if(xetDuyet == 1){
                trangThai = "Đã rời khỏi";
            }
        }
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public DonXinRa(String maDonXinRa, String MSSV, Date ngayLapDon, String lyDo, Integer xetDuyet) {
        this.maDonXinRa = maDonXinRa;
        this.MSSV = MSSV;
        this.ngayLapDon = ngayLapDon;
        this.lyDo = lyDo;
        this.xetDuyet = xetDuyet;
        if(xetDuyet == 0){
            trangThai = "Đang chờ";
        }else {
            if(xetDuyet == 1){
                trangThai = "Đã rời khỏi";
            }
        }
        this.select = new CheckBox();
    }

    public String getMaDonXinRa() {
        return maDonXinRa;
    }

    public void setMaDonXinRa(String maDonXinRa) {
        this.maDonXinRa = maDonXinRa;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public Date getNgayLapDon() {
        return ngayLapDon;
    }

    public void setNgayLapDon(Date ngayLapDon) {
        this.ngayLapDon = ngayLapDon;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public Integer getXetDuyet() {
        return xetDuyet;
    }

    public void setXetDuyet(Integer xetDuyet) {
        this.xetDuyet = xetDuyet;
        if(xetDuyet == 0){
            trangThai = "Đang chờ";
        }else {
            if(xetDuyet == 1){
                trangThai = "Đã rời khỏi";
            }
        }
    }
}
