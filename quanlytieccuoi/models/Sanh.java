/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import javax.persistence.*;

/**
 *
 * @author LeMy
 */
@Entity
@Table(name = "QuanLySanhTiec.SANH")
public class Sanh {
    @Id
    @Column(name = "MaSanh")
    private String maSanh;
    @Column(name = "TenSanh")
    private String tenSanh;
    @Column(name = "MaNguoiQuanLy")
    private int maNguoiQuanLy;
    @Column(name = "Soban")
    private int soBan;

    /**
     * @return the maSanh
     */
    public String getMaSanh() {
        return maSanh;
    }

    /**
     * @param maSanh the maSanh to set
     */
    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    /**
     * @return the tenSanh
     */
    public String getTenSanh() {
        return tenSanh;
    }

    /**
     * @param tenSanh the tenSanh to set
     */
    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    /**
     * @return the maNguoiQuanLy
     */
    public int getMaNguoiQuanLy() {
        return maNguoiQuanLy;
    }

    /**
     * @param maNguoiQuanLy the maNguoiQuanLy to set
     */
    public void setMaNguoiQuanLy(int maNguoiQuanLy) {
        this.maNguoiQuanLy = maNguoiQuanLy;
    }

    /**
     * @return the soBan
     */
    public int getSoBan() {
        return soBan;
    }

    /**
     * @param soBan the soBan to set
     */
    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }
    
}
