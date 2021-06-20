package model;

public class SinhVienDotDK {
    private String MSSV;
    private String maDotDK;

    public SinhVienDotDK() {
    }

    public SinhVienDotDK(String MSSV, String maDotDK) {
        this.MSSV = MSSV;
        this.maDotDK = maDotDK;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaDotDK() {
        return maDotDK;
    }

    public void setMaDotDK(String maDotDK) {
        this.maDotDK = maDotDK;
    }
}
