/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.Main;
import quanlytieccuoi.models.HibernateUtil;
import quanlytieccuoi.models.KhachHang;

/**
 * FXML Controller class
 *
 * @author LeMy
 */
public class ThongTinKhachHangController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Main main;
    private SessionFactory sessionFactory;
    private ObservableList<KhachHang> obsListKhachHang = FXCollections.observableArrayList();

    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtSdt;
    @FXML
    private TableView<KhachHang> tableViewKH;
    @FXML
    private TableColumn<KhachHang, Integer> colMaKH;
    @FXML
    private TableColumn<KhachHang, String> colTenKH;
    @FXML
    private TableColumn<KhachHang, String> colDiaChi;
    @FXML
    private TableColumn<KhachHang, String> colSdt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Main.initializeDB();
            //binding csdl cua tableRow vs textbox
             this.tableViewKH.setRowFactory(tv -> {
                TableRow<KhachHang> row = new TableRow<>();
                row.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() == 1 && !row.isEmpty()) {
                        KhachHang kh = row.getItem();
                        txtTenKH.setText(kh.getTenKH());
                        txtDiaChi.setText(kh.getDiaChi());
                        txtSdt.setText(kh.getSdt());
                    }
                });
                return row;
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongTinKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongTinKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sessionFactory = HibernateUtil.getSessionFactory();
        loadKhachHang();
    }
    @FXML
    private void nhapLai() {
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
    }
    //hibernate

    private void loadKhachHang() {
        setCellValue();
        getListKhachHang();
        tableViewKH.setItems(obsListKhachHang);
    }

    public void setCellValue() {
        colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        colTenKH.setCellValueFactory(new PropertyValueFactory<>("tenKH"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
    }

    private void getListKhachHang() {
        Session session = sessionFactory.openSession();
        Criteria crite = session.createCriteria(KhachHang.class);
        List<KhachHang> list = crite.list();
        session.close();
        //chuyen data tu list sang observableList
        list.stream().forEach(e -> {
            obsListKhachHang.add(e);
        });
    }

    @FXML
    private void themKhachHang(ActionEvent event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if ((txtTenKH.getText().equalsIgnoreCase("")) || (txtDiaChi.getText().equalsIgnoreCase("")) 
                    || (txtSdt.getText().equalsIgnoreCase(""))) {
                System.out.println("chua nhap info");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setContentText("Chua nhap du thong tin khach hang");
                alert.show();
            } else {
                KhachHang kh = new KhachHang(txtTenKH.getText(), txtDiaChi.getText(), txtSdt.getText());
                session.save(kh);
                transaction.commit();
                System.out.println("them thanh cong");
            }

        } catch (HibernateException e) {
            System.out.println("hibernate error: " + e);
            transaction.rollback();
        }
        session.close();
    }

    @FXML
    private void taiLaiDB(ActionEvent event) {
        loadKhachHang();
    }
}
