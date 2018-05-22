/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.models;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author LeMy
 */
@Entity
@Table(name = "QuanLySanhTiec.TIEC")
public class Tiec {
    @Id
    @Column(name = "MaTiec")
    private String maTiec;
    @Column(name = "NgayToChuc")
    private Date ngayToChuc;
    @Column(name = "MaChuTiec")
    private int maChuTiec;
    @Column(name = "MaSanh")
    private String maSanh;
    @Column(name = "MaMenu")
    private String maMenu;
    
    public Tiec(){
        
    }
    public Tiec(String maTiec, Date ngayToChuc, int maChuTiec, String maSanh, String maMenu){
        this.maTiec = maTiec;
        this.ngayToChuc = ngayToChuc;
        this.maChuTiec = maChuTiec;
        this.maSanh = maSanh;
        this.maMenu = maMenu;
    }

    /**
     * @return the maTiec
     */
    public String getMaTiec() {
        return maTiec;
    }

    /**
     * @param maTiec the maTiec to set
     */
    public void setMaTiec(String maTiec) {
        this.maTiec = maTiec;
    }

    /**
     * @return the ngayToChuc
     */
    public Date getNgayToChuc() {
        return ngayToChuc;
    }

    /**
     * @param ngayToChuc the ngayToChuc to set
     */
    public void setNgayToChuc(Date ngayToChuc) {
        this.ngayToChuc = ngayToChuc;
    }

    /**
     * @return the maChuTiec
     */
    public int getMaChuTiec() {
        return maChuTiec;
    }

    /**
     * @param maChuTiec the maChuTiec to set
     */
    public void setMaChuTiec(int maChuTiec) {
        this.maChuTiec = maChuTiec;
    }

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
     * @return the maMenu
     */
    public String getMaMenu() {
        return maMenu;
    }

    /**
     * @param maMenu the maMenu to set
     */
    public void setMaMenu(String maMenu) {
        this.maMenu = maMenu;
    }

    @Override
    public String toString() {
        return maTiec;
    }
    
    
}
