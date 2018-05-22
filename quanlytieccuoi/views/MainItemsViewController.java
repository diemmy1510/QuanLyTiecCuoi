/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi.views;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import quanlytieccuoi.Main;

/**
 *
 * @author THANHSEN
 */
public class MainItemsViewController {

    private Main main;

    @FXML
    public void goTuVan() throws IOException {
        Main.showTuVanView();
    }

    @FXML
    private void goBackHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("views/MainItemView.fxml"));
        BorderPane homePane = loader.load();
        Main.mainPane.setCenter(homePane);
    }

    @FXML
    public void goThongKe() throws IOException {
        Main.goThongKeView();
    }

}
