/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.layout.GridPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import quanlytieccuoi.models.HibernateUtil;

/**
 *
 * @author THANHSEN
 */
public class Main extends Application {
    private static Stage stage;
    public static Stage menuStage;
    public static BorderPane mainPane;
    public static BorderPane tuVanPane;
    public static StackPane thongKePane;
    public static Connection connection;
    
    @Override
    public void start(Stage prianemaryStage) throws IOException, ClassNotFoundException, SQLException {
        this.stage = prianemaryStage;
        showMainItemsView();
    }
    public void showMainItemsView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/MainItemView.fxml"));
        mainPane = loader.load();
        
        Scene scene = new Scene(mainPane);
        
        this.stage.setTitle("Quan ly tiec cuoi");
        this.stage.setScene(scene);
        this.stage.show();
    }
    public static void showTuVanView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/TuVanView.fxml"));
        tuVanPane = loader.load();
        mainPane.setCenter(tuVanPane);    
    }
    public static void goThongKeView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/ThongKe.fxml"));
        thongKePane = loader.load();
        mainPane.setCenter(thongKePane);
    }
    public static void goXemSanh() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/sanhTab.fxml"));
            VBox imgSanhPane = loader.load();
            
            Stage listSanhStage = new Stage();
            listSanhStage.setTitle("Xem sanh tiec");
            listSanhStage.initModality(Modality.WINDOW_MODAL);
            listSanhStage.initOwner(stage);
            
            Scene imgSanhScene = new Scene(imgSanhPane);
            listSanhStage.setScene(imgSanhScene);
            listSanhStage.show();
    }
    public static void goSumaryMenu() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/SumaryMenu.fxml"));
            BorderPane menuPane = loader.load();
            
            menuStage = new Stage();
            menuStage.setTitle("Sumary Menu");
            menuStage.initModality(Modality.WINDOW_MODAL);
            menuStage.initOwner(stage);
            
            Scene menuScene = new Scene(menuPane);
            menuStage.setScene(menuScene);
            menuStage.show();
    }
    public static void goThemKHView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/ThongTinKhachHang.fxml"));
        BorderPane themKHPane = loader.load();
        Stage thongTinKHStage = new Stage();
        thongTinKHStage.setTitle("Them khach hang");
        thongTinKHStage.initModality(Modality.WINDOW_MODAL);
        thongTinKHStage.initOwner(stage);
            
        Scene thongTinKHScene = new Scene(themKHPane);
        thongTinKHStage.setScene(thongTinKHScene);
        thongTinKHStage.show();
        
    }
    
    public static void initializeDB() throws ClassNotFoundException, SQLException {
        //nap driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");
        //connect CSDL
        connection = DriverManager.getConnection
        ("jdbc:mysql://localhost/QuanLySanhTiec", "root", "mysql123");
        System.out.println("Database connected");
    }
    public Stage getStage(){
        return this.stage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(args);
    }
}
