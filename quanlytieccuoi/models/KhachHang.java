/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import javafx.beans.property.SimpleStringProperty;
import quanlytieccuoi.models.HibernateUtil;
import javax.persistence.*;

/**
 *
 * @author LeMy
 */
@Entity
@Table (name = "QuanLySanhTiec.KHACHHANG")
public class KhachHang {
    @Id
    @Column(name = "MaKH")
    private int maKH;
    @Column(name = "TenKH")
    private String tenKH;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "sdt")
    private String sdt;
    
    public KhachHang(){
        
    }
    public KhachHang(String ten, String diaChi, String sdt){
        this.tenKH = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    /**
     * @return the maKH
     */
    public int getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}
