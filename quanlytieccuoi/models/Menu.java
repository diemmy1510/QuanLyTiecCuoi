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
@Table(name = "QuanLySanhTiec.MENU")
public class Menu {
    @Id
    @Column(name = "MaMenu")
    private String maMenu;
    @Column(name = "MaTiec")
    private String maTiec;
    @Column(name = "MaBep")
    private String maBep;
    
    public Menu(){
        
    }
    public Menu(String maMenu, String maTiec, String maBep){
        this.maMenu = maMenu;
        this.maTiec = maTiec;
        this.maBep = maBep;
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
     * @return the maBep
     */
    public String getMaBep() {
        return maBep;
    }

    /**
     * @param maBep the maBep to set
     */
    public void setMaBep(String maBep) {
        this.maBep = maBep;
    }
    
}
