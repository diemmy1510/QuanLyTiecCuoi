/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import quanlytieccuoi.Main;
import quanlytieccuoi.models.Mon;

/**
 * FXML Controller class
 *
 * @author kphuo
 */
public class TuVanViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Main main;
    private PreparedStatement prepareStatement;
    private ObservableList<Mon> obsListMonKhaiVi;
    private ObservableList<Mon> obsListMonChinh;
    private ObservableList<Mon> obsListMonTrangMieng;
    public ObservableList<CheckBox> obsCheckBoxMon = FXCollections.observableArrayList();
    public ObservableList<String> obsSelectedTenMon;
    @FXML
    private Label lblTuVanTitle;
    @FXML
    private TabPane tuVanTabPane;
    @FXML
    private Tab sanhTab;
    @FXML
    private Tab menuTab;
    @FXML
    private Tab thongTinKHTab;
    @FXML
    private VBox vbMonKhaiVi;
    @FXML
    private VBox vbMonChinh;
    @FXML
    private VBox vbMonTrangMieng;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            main.initializeDB();
            init();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TuVanViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TuVanViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void init() {
        tuVanTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == sanhTab) {
                System.out.println("-da chon sanh tab-");
                try {
                    Main.goXemSanh();
                } catch (IOException ex) {
                    Logger.getLogger(TuVanViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (newValue == menuTab) {
                System.out.println("-da chon menu tab-");
                vbMonKhaiVi.getChildren().clear();
                vbMonChinh.getChildren().clear();
                vbMonTrangMieng.getChildren().clear();
                try {
                    loadListMonKhaiVi();
                    loadListMonChinh();
                    loadListMonTrangMieng();
                } catch (SQLException ex) {
                    Logger.getLogger(TuVanViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else if (newValue == thongTinKHTab) {
                System.out.println("-da chon thong tin khach hang tab-");
                try {
                    Main.goThemKHView();
                } catch (IOException ex) {
                    Logger.getLogger(TuVanViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    @FXML
    private void btnSumaryMenuClick() throws IOException, SQLException {
        chonMon();
        Main.goSumaryMenu();
    }
    private ObservableList<Mon> getListMonKhaiVi() throws SQLException {
        //khoi tao obslist
        obsListMonKhaiVi = FXCollections.observableArrayList();
        String q = "SELECT * FROM QuanLySanhTiec.MON WHERE MON.MaLoaiMon = \"MKV\"";
        prepareStatement = Main.connection.prepareStatement(q);
        ResultSet set = prepareStatement.executeQuery();
        while (set.next()) {
            obsListMonKhaiVi.add(new Mon(set.getString("TenMon"),
                    set.getDouble("Gia"),
                    set.getString("DonViTinh"),
                    set.getString("MaLoaiMon")));
        }
        return obsListMonKhaiVi;
    }

    private ObservableList<Mon> getListMonChinh() throws SQLException {
        obsListMonChinh = FXCollections.observableArrayList();
        String q = "SELECT * FROM QuanLySanhTiec.MON WHERE MON.MaLoaiMon = \"MC\"";
        prepareStatement = Main.connection.prepareStatement(q);
        ResultSet set = prepareStatement.executeQuery();
        while (set.next()) {
            obsListMonChinh.add(new Mon(set.getString("TenMon"),
                    set.getDouble("Gia"),
                    set.getString("DonViTinh"),
                    set.getString("MaLoaiMon")));
        }
        return obsListMonChinh;
    }

    private ObservableList<Mon> getListMonTrangMieng() throws SQLException {
        obsListMonTrangMieng = FXCollections.observableArrayList();
        String q = "SELECT * FROM QuanLySanhTiec.MON WHERE MON.MaLoaiMon = \"MTM\"";
        prepareStatement = Main.connection.prepareStatement(q);
        ResultSet set = prepareStatement.executeQuery();
        while (set.next()) {
            obsListMonTrangMieng.add(new Mon(set.getString("TenMon"),
                    set.getDouble("Gia"),
                    set.getString("DonViTinh"),
                    set.getString("MaLoaiMon")));
        }
        return obsListMonTrangMieng;
    }

    private void loadListMonKhaiVi() throws SQLException {
        ObservableList<Mon> listMKVs = FXCollections.observableArrayList();
        listMKVs = getListMonKhaiVi();
        for (Mon mon : listMKVs) {
            CheckBox cb = new CheckBox();
            cb.setUserData(mon);
            cb.setText(mon.getTenMon());
            vbMonKhaiVi.getChildren().add(cb);
            obsCheckBoxMon.add(cb);
        }
    }

    private void loadListMonChinh() throws SQLException {
        ObservableList<Mon> listMonChinh = FXCollections.observableArrayList();
        listMonChinh = getListMonChinh();
        for (Mon mon : listMonChinh) {
            CheckBox cb = new CheckBox();
            cb.setText(mon.getTenMon());
            vbMonChinh.getChildren().add(cb);
            obsCheckBoxMon.add(cb);
        }
    }

    private void loadListMonTrangMieng() throws SQLException {
        ObservableList<Mon> listMonTrangMieng = FXCollections.observableArrayList();
        listMonTrangMieng = getListMonTrangMieng();
        for (Mon mon : listMonTrangMieng) {
            CheckBox cb = new CheckBox();
            cb.setText(mon.getTenMon());
            vbMonTrangMieng.getChildren().add(cb);
            obsCheckBoxMon.add(cb);
        }
    }

    public void chonMon() throws SQLException {
        Mon.selectedMons = FXCollections.observableArrayList();
        //load mon tu cdsl
        for (CheckBox cb : obsCheckBoxMon) {
            if (cb.isSelected() == true) {
                String q = "SELECT * FROM QuanLySanhTiec.MON WHERE MON.TenMon = ?";
                prepareStatement = Main.connection.prepareStatement(q);
                prepareStatement.setString(1, cb.getText());
                ResultSet set = prepareStatement.executeQuery();
                while (set.next()) {
                    Mon.selectedMons.add(new Mon(set.getString("TenMon"),
                            set.getDouble("Gia"),
                            set.getString("DonViTinh"),
                            set.getString("MaLoaiMon")));
                }
            }
        }

    }

}
