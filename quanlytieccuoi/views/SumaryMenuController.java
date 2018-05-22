/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.Main;
import quanlytieccuoi.models.Bep;
import quanlytieccuoi.models.HibernateUtil;
import quanlytieccuoi.models.Menu;
import quanlytieccuoi.models.Mon;
import quanlytieccuoi.models.Tiec;

/**
 * FXML Controller class
 *
 * @author LeMy
 */
public class SumaryMenuController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML private ComboBox<Tiec> comboBoxTiec;
    @FXML private ComboBox<Bep> comboBoxBep;
    @FXML private TableView<Mon> tableViewMenu;
    @FXML private TableColumn<Mon, String> colTenMon;
    @FXML private TableColumn<Mon, Double> colGiaTien;
    private Main main;
    private SessionFactory sessionFactory;
    private ObservableList<Tiec>obsTiec;
    private ObservableList<Bep>obsBep;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            main.initializeDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SumaryMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SumaryMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessionFactory = HibernateUtil.getSessionFactory();
        setComboBoxTiecValue();
        setComboBoxBepValue();
        showMenu();
        
    } 
    private void showMenu(){
        setCellTableViewMenu();
        tableViewMenu.setItems(Mon.selectedMons);
    }
    private void setCellTableViewMenu(){
        colTenMon.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        colGiaTien.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
    }
    private void setComboBoxTiecValue(){
        Session session = sessionFactory.openSession();
        obsTiec = FXCollections.observableArrayList();
        Criteria cri = session.createCriteria(Tiec.class);
        List<Tiec> listTiec = cri.list();
        listTiec.stream().forEach((t) -> {
            obsTiec.add(t);
        });
        session.close();
        for (Tiec tiec : obsTiec) {
            comboBoxTiec.getItems().add(tiec);
        }
    }
    private void setComboBoxBepValue(){
        Session session = sessionFactory.openSession();
        obsBep = FXCollections.observableArrayList();
        Criteria cri = session.createCriteria(Bep.class);
        List<Bep> listBep = cri.list();
        listBep.stream().forEach((t) -> {
            obsBep.add(t);
        });
        session.close();
        for (Bep bep : obsBep) {
            comboBoxBep.getItems().add(bep);
        }
    }
    @FXML
    private void SaveMenu(ActionEvent event){
        String maTiec = comboBoxTiec.getValue().toString();
        String maBep = comboBoxBep.getValue().toString();
        Session session = sessionFactory.openSession();
        String maMenu = UUID.randomUUID().toString();
        
        Transaction trans = session.beginTransaction();
        try {
            Menu menu = new Menu(maMenu,maTiec,maBep);
            session.save(menu);
            trans.commit();
            System.out.println("them menu thanh cong");
        } catch (HibernateException e) {
            System.out.println("hibernate error: " + e);
            trans.rollback();
        }
        session.close();
    }
    @FXML
    private void huyMenu(ActionEvent event){
        main.menuStage.close();
    }
    
}
