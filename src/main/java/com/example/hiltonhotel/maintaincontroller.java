package com.example.hiltonhotel;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.security.Key;
import java.sql.*;


import java.time.LocalDate;
import java.util.ResourceBundle;

public  class maintaincontroller extends  NullPointerException
{
    @FXML
    private Label msg;

    @FXML
    private TextField tf_gidd;
    @FXML
    private TextField tf_clothes;
    @FXML
    private TextField tf_lcharge;
    @FXML
    private TextField tf_room;
    @FXML
    private TextField tf_clean;




    @FXML
    protected void save(ActionEvent e)

    {

        String Guest_id=tf_gidd.getText();
        String Clothes=tf_clothes.getText();
        String Room_no=tf_room.getText();
        String Laundry_charges=tf_lcharge.getText();
        String Cleaned=tf_clean.getText();





        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        PreparedStatement psinsert=null;
        PreparedStatement pscheck=null;
        ResultSet resultSet=null;
        if (!tf_gidd.getText().isBlank() && !tf_clothes.getText().isBlank() && !tf_lcharge.getText().isBlank() && !tf_room.getText().isBlank() && !tf_clean.getText().isBlank()) {
            try {
                pscheck = connectdb.prepareStatement("select * from laundry where Guest_id= ?");
                pscheck.setString(1, Guest_id);
                resultSet = pscheck.executeQuery();
                if (resultSet.isBeforeFirst()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                    alert.show();
                } else {

                    psinsert = connectdb.prepareStatement("insert into laundry VALUES (?,?,?)");
                    psinsert.setString(1, Guest_id);
                    psinsert.setString(2, Clothes);
                    psinsert.setString(3, Laundry_charges);
                    psinsert.executeUpdate();
                }
            } catch (SQLException ep) {
                ep.printStackTrace();
            }
            PreparedStatement psi = null;
            PreparedStatement psc = null;
            ResultSet rs = null;


            try {
                psc = connectdb.prepareStatement("select * from housekeeping where Guest_id= ?");
                psc.setString(1, Guest_id);
                resultSet = psc.executeQuery();
                if (resultSet.isBeforeFirst()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                    alert.show();
                } else {

                    psinsert = connectdb.prepareStatement("insert into housekeeping VALUES (?,?,?)");
                    psinsert.setString(1, Guest_id);
                    psinsert.setString(2, Room_no);
                    psinsert.setString(3, Cleaned);
                    psinsert.executeUpdate();
                }
            } catch (SQLException ep) {
                ep.printStackTrace();
            }
            msg.setText("Saved Successfully!!!");
        }
        else
        {
            msg.setText("All Fields Are Compulsory..");
        }

    }
    @FXML
    protected void gobck(ActionEvent e)

    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception ep) {
            ep.printStackTrace();
        }}
    }


