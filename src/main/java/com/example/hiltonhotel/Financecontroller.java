package com.example.hiltonhotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public  class Financecontroller extends NullPointerException implements Initializable
{
    @FXML
    private Label msg;
    @FXML
    private Label due;
    @FXML
    private Label total;
    @FXML
    private TextField tf_gid;
    @FXML
    private TextField tf_service;
    @FXML
    private  TextField tf_laundry;
    @FXML
    private  TextField tf_dining;
    @FXML
    private  TextField tf_payed;
    @FXML
    private TextField tf_mode;

    @FXML
    private ChoiceBox<String> checkbox;
    private final String[] select={"Restaurant Charges","Banquet Charges"};
    public void initialize(URL arg0, ResourceBundle arg1){
        checkbox.setValue("Select Dining");
        checkbox.getItems().addAll(select);

    }

    @FXML
    protected void save(ActionEvent e)
    {
        msg.setText("Saved Successfully!!");

    }
    @FXML
    protected void Total(ActionEvent e)
    {
        String select=checkbox.getValue();
        String Guest_id=tf_gid.getText();
        String Service_charges=tf_service.getText();
        String Laundry_charges=tf_laundry.getText();
        String Amount_payed=tf_payed.getText();
        String BRcharges=tf_dining.getText();
        String Payment_mode=tf_mode.getText();

            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            PreparedStatement psinsert = null;
            PreparedStatement ptotal = null;
        PreparedStatement pdue = null;
            ResultSet resultSet = null;
            ResultSet rs = null;
            if(!tf_gid.getText().isBlank() && !tf_dining.getText().isBlank() && !tf_mode.getText().isBlank() && !tf_service.getText().isBlank() && !tf_laundry.getText().isBlank() && !tf_payed.getText().isBlank())
            {
            try {
                psinsert = connectdb.prepareStatement("insert into finance VALUES (?,?,?,?,?,?,?,0,0)");
                psinsert.setString(1, Guest_id);
                psinsert.setString(2, Service_charges);
                psinsert.setString(3, BRcharges);
                psinsert.setString(4, Laundry_charges);
                psinsert.setString(5, Amount_payed);
                psinsert.setString(6, select);
                psinsert.setString(7, Payment_mode);
                psinsert.executeUpdate();


                ptotal=connectdb.prepareStatement("select ifnull(Service_charges,0) + ifnull(BRcharges,0)+ ifnull(Laundry_charges,0) as Total_billing from finance;");
                rs=ptotal.executeQuery();
                while(rs.next())
                {
                    int c=rs.getInt("Total_billing");
                    total.setText(String.valueOf(c));
                }
                ptotal=connectdb.prepareStatement("select ifnull(Service_charges,0) + ifnull(BRcharges,0)+ ifnull(Laundry_charges,0)-ifnull(Amount_paid,0) as Amount_due from finance;");
                rs=ptotal.executeQuery();
                while(rs.next())
                {
                    int cr=rs.getInt("Amount_due");
                    due.setText(String.valueOf(cr));
                }




            }catch (SQLException ep)
            {
                ep.printStackTrace();
            }}
            else{
                msg.setText("invalid");
            }



    }
    @FXML
    protected void goback(ActionEvent e)

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
        }
    }
}