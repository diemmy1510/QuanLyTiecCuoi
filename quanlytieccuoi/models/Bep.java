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
@Table(name = "QuanLySanhTiec.BEP")
public class Bep {
    @Id
    @Column(name = "MaBep")
    private String maBep;
    @Column(name = "MaBepTruong")
    private int maBepTruong;
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

    /**
     * @return the maBepTruong
     */
    public int getMaBepTruong() {
        return maBepTruong;
    }

    /**
     * @param maBepTruong the maBepTruong to set
     */
    public void setMaBepTruong(int maBepTruong) {
        this.maBepTruong = maBepTruong;
    }

    @Override
    public String toString() {
        return maBep;
    }
    
    
}
