package com.example.hiltonhotel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Paint;

public  class Dashboardcontroller extends NullPointerException
{
    @FXML
    private Label label;



    @FXML
    protected void finance(ActionEvent e)

    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finance.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void guest(ActionEvent e)

    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Guestinfo.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void foodb(ActionEvent e)

    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoodBeverage.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void maintain(ActionEvent e)

    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Maintainence.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void room(ActionEvent e)

    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RoomDivision.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }

    @FXML
    protected void help(ActionEvent e)

    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void delete(ActionEvent e)

    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void loginbk(ActionEvent e)

    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    protected void newbook(ActionEvent e)

    {

        try {
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            PreparedStatement pnew = null;
            pnew = connectdb.prepareStatement("select count(*) as avai from guest_info1 ");

           ResultSet rs = pnew.executeQuery();
            while (rs.next()) {
                int c = rs.getInt("avai");
                label.setText(String.valueOf(c));
            }
        } catch(Exception ep) {
            ep.printStackTrace();
        }
    }
}
