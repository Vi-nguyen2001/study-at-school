package Assignment.model;

public class SanPhamAssm {
    private int maSp;
    private String tenSp;
    private String giaSp;
    private int soLuong;

    public SanPhamAssm(int maSp, String tenSp, String giaSp, int soLuong) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.soLuong = soLuong;
    }

    public SanPhamAssm() {
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(String giaSp) {
        this.giaSp = giaSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
