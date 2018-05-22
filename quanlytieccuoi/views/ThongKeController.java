/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import quanlytieccuoi.Main;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kphuo
 */
public class ThongKeController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    TextField txtMonth;
    @FXML
    StackPane stPane;

    Main main;
    ObservableList<PieChart.Data> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Main.initializeDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PieChart chart = new PieChart();
        getDatas();
        chart.getData().addAll(data);
        chart.setStartAngle(90);
        chart.setTitle("So luong tiec theo thang");
        chart.setStyle("-fx-font-size: 16 px;");
        Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font:26  arial;");

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getX());
                    caption.setTranslateY(e.getY());
                    caption.setText(String.valueOf((int) data.getPieValue()));
                }
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setText("");
                }
            });
        }
        stPane.getChildren().addAll(chart, caption);
    }

    public void getDatas() {
        data = FXCollections.observableArrayList();
        try {
            
            Statement stm = Main.connection.createStatement();
            String query = "SELECT Month(NgayToChuc) AS Thang,COUNT(MaTiec) AS SLTiec "
                    + "FROM QuanLySanhTiec.tiec group by Month(NgayToChuc)";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                data.add(new PieChart.Data("Thang " + Integer.toString(rs.getInt("Thang")), 
                        rs.getDouble("SLTiec")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
