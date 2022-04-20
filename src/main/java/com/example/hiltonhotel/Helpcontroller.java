package com.example.hiltonhotel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public  class Helpcontroller extends NullPointerException
{
    @FXML
    private Label label;
    @FXML
    private TextField nameee;
    @FXML
    private TextField tf_email;
    @FXML
    private TextArea tf_msgg;

    @FXML
    protected void send(ActionEvent e) throws NullPointerException {
        String name=nameee.getText();
        String email=tf_email.getText();
        String msg=tf_msgg.getText();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        PreparedStatement psinsert = null;
        try{
        psinsert = connectdb.prepareStatement("insert into help VALUES (?,?,?)");
        psinsert.setString(1, name);
        psinsert.setString(2, email);
        psinsert.setString(3, msg);
        psinsert.executeUpdate();
        label.setText("Feedback Saved Successfully .THANK YOU!!");
    } catch (SQLException epe)
    {
    epe.printStackTrace();
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
        }}}