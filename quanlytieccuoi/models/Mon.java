/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;

/**
 *
 * @author LeMy
 */
@Entity
@Table(name = "QuanLySanhTiec.MON")
public class Mon {
    @Id
    @Column(name = "MaMon")
    private int maMon;
    @Column(name = "TenMon")
    private String tenMon;
    @Column(name = "Gia")
    private double giaTien;
    @Column(name = "DonViTinh")
    private String donViTinh;
    @Column(name = "MaLoaiMon")
    private String maLoaiMon;
    
    public static ObservableList<Mon> selectedMons;
    /**
     * @return the maMon
     */
    public Mon(){
        
    }
    public Mon(String tenMon, double gia, String dvt, String maLoaiMon){
        this.tenMon = tenMon;
        this.giaTien = gia;
        this.donViTinh = dvt;
        this.maLoaiMon = maLoaiMon;
    }
    public int getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    /**
     * @return the tenMon
     */
    public String getTenMon() {
        return tenMon;
    }

    /**
     * @param tenMon the tenMon to set
     */
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    /**
     * @return the maLoaiMon
     */
    public String getMaLoaiMon() {
        return maLoaiMon;
    }

    /**
     * @param maLoaiMon the maLoaiMon to set
     */
    public void setMaLoaiMon(String maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
    }

    /**
     * @return the giaTien
     */
    public double getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    /**
     * @return the donViTinh
     */
    public String getDonViTinh() {
        return donViTinh;
    }

    /**
     * @param donViTinh the donViTinh to set
     */
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
}
