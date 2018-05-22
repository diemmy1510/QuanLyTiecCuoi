/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import quanlytieccuoi.Main;
import quanlytieccuoi.models.HibernateUtil;
import quanlytieccuoi.models.Tiec;

/**
 * FXML Controller class
 *
 * @author LeMy
 */
public class SanhTabController implements Initializable {
    @FXML private ImageView imageView1;
    @FXML private ImageView imageView2;
    @FXML private ImageView imageView3;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Tiec> tableViewTiec;
    @FXML private TableColumn<Tiec, String> colMaTiec;
    @FXML private TableColumn<Tiec, Date> colNgayToChuc;
    @FXML private TableColumn<Tiec, String> colMaSanh;
    private ObservableList<Tiec>obsTiec;
    private ObservableList<Tiec>obsTiecTrung = FXCollections.observableArrayList();
    private SessionFactory sessionFactory;
    private LocalDate date;
    private Statement stm;
    Main main;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sessionFactory = HibernateUtil.getSessionFactory();
        try {
            stm = Main.connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SanhTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellValue();
        tableViewTiec.setItems(obsTiecTrung);
    }
    @FXML
    private void chonNgayToChucTiec(ActionEvent event) throws SQLException{
        tableViewTiec.getItems().clear();
        date = datePicker.getValue();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        java.sql.Date dbSqlDate = rs.getDate("Thang");
//        java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime());
//        String dateFormat = formatter.format(dbSqlDateConverted);
        System.out.println(date);
        ObservableList<Tiec> list = getListTiec();
        String sql = String.format("SELECT * FROM QuanLySanhTiec.TIEC WHERE TIEC.NgayToChuc = '%s'", date);
        ResultSet re = stm.executeQuery(sql);
        while (re.next()) {            
            obsTiecTrung.add(new Tiec(re.getString("MaTiec"), 
                    re.getDate("NgayToChuc"), 0 ,re.getString("MaSanh"), null));
        }
    }
    private ObservableList<Tiec> getListTiec(){
        Session session = sessionFactory.openSession();
        obsTiec = FXCollections.observableArrayList();
        Criteria cri = session.createCriteria(Tiec.class);
        List<Tiec> listTiec = cri.list();
        listTiec.stream().forEach((t) -> {
            obsTiec.add(t);
        });
        session.close();
        return obsTiec;
    }
    public void setCellValue() {
        colMaTiec.setCellValueFactory(new PropertyValueFactory<>("maTiec"));
        colNgayToChuc.setCellValueFactory(new PropertyValueFactory<>("ngayToChuc"));
        colMaSanh.setCellValueFactory(new PropertyValueFactory<>("maSanh"));
    }
}
