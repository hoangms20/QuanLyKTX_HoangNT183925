package model;

import javafx.scene.control.CheckBox;

public class LoaiHoaDon {
    private String maLoaiHD;
    private String tenLoaiHD;
    private Integer giaTri;
    private Integer tienVuotDM;
    private Integer mucHoTro;
    private Integer batBuoc;
    private CheckBox cbBatBuoc;

    public LoaiHoaDon() {
    }

    public LoaiHoaDon(String maLoaiHD, String tenLoaiHD, Integer giaTri, Integer tienVuotDM, Integer mucHoTro, Integer batBuoc) {
        this.maLoaiHD = maLoaiHD;
        this.tenLoaiHD = tenLoaiHD;
        this.giaTri = giaTri;
        this.tienVuotDM = tienVuotDM;
        this.mucHoTro = mucHoTro;
        this.batBuoc = batBuoc;
        this.cbBatBuoc = new CheckBox();
        if(batBuoc == 0){
            this.cbBatBuoc.setSelected(false);
        }else {
            this.cbBatBuoc.setSelected(true);
        }

    }

    public String getMaLoaiHD() {
        return maLoaiHD;
    }

    public void setMaLoaiHD(String maLoaiHD) {
        this.maLoaiHD = maLoaiHD;
    }

    public String getTenLoaiHD() {
        return tenLoaiHD;
    }

    public void setTenLoaiHD(String tenLoaiHD) {
        this.tenLoaiHD = tenLoaiHD;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

    public Integer getBatBuoc() {
        return batBuoc;
    }

    public void setBatBuoc(Integer batBuoc) {
        this.batBuoc = batBuoc;
    }

    public Integer getTienVuotDM() {
        return tienVuotDM;
    }

    public void setTienVuotDM(Integer tienVuotDM) {
        this.tienVuotDM = tienVuotDM;
    }

    public Integer getMucHoTro() {
        return mucHoTro;
    }

    public void setMucHoTro(Integer mucHoTro) {
        this.mucHoTro = mucHoTro;
    }

    public CheckBox getCbBatBuoc() {
        return cbBatBuoc;
    }

    public void setCbBatBuoc() {
        if(this.batBuoc == 0){
            this.cbBatBuoc.setSelected(false);
        }else {
            this.cbBatBuoc.setSelected(true);
        }
    }
}
