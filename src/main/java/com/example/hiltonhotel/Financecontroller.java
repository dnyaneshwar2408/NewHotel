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
import java.sql.*;
import java.time.LocalDate;
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
    protected void save(ActionEvent e) {

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
    PreparedStatement pcheck=null;

    PreparedStatement ptotal = null;
    PreparedStatement pdue = null;
    ResultSet resultSet = null;

    ResultSet rs = null;
        if(!tf_gid.getText().isBlank() && !tf_dining.getText().isBlank() && !tf_mode.getText().isBlank() && !tf_service.getText().isBlank() && !tf_laundry.getText().isBlank() && !tf_payed.getText().isBlank()) {
    try {
        pcheck = connectdb.prepareStatement("select * from finance where Guest_id=?");
        pcheck.setString(1, Guest_id);
        rs = pcheck.executeQuery();
        if (rs.isBeforeFirst()) {
            Alert ep = new Alert(Alert.AlertType.ERROR);
            ep.setContentText("Guest Already Exists...");
            ep.show();

        } else {

            psinsert = connectdb.prepareStatement("insert into finance VALUES (?,?,?,?,?,?,0,?,0)");
            psinsert.setString(1, Guest_id);
            psinsert.setString(2, Service_charges);
            psinsert.setString(3, BRcharges);
            psinsert.setString(4, Laundry_charges);
            psinsert.setString(5, select);
            psinsert.setString(6, Payment_mode);
            psinsert.setString(7, Amount_payed);
            psinsert.executeUpdate();
            msg.setText("Details Saved Successfully..");



        }


    }catch(SQLException ep)
    {
        ep.printStackTrace();
    }

}
        else{
    msg.setText("All Fields are Compulsory.");
}



        if(!tf_gid.getText().isBlank() && !tf_dining.getText().isBlank() && !tf_mode.getText().isBlank() && !tf_service.getText().isBlank() && !tf_laundry.getText().isBlank() && !tf_payed.getText().isBlank()) {
            try {
                if (rs.isBeforeFirst()) {


                } else {
                    ptotal = connectdb.prepareStatement("select Total_billing from finance as Total_billing where Guest_id=?;");
                    ptotal.setString(1, Guest_id);
                    rs = ptotal.executeQuery();
                    while (rs.next()) {
                        int cr = rs.getInt("Total_billing");
                        total.setText(String.valueOf(cr));
                    }

                    pdue = connectdb.prepareStatement("select Amount_due as Amount_due from finance  where Guest_id=?;");
                    pdue.setString(1, Guest_id);
                    rs = pdue.executeQuery();
                    while (rs.next()) {
                        int cr = rs.getInt("Amount_due");
                        due.setText(String.valueOf(cr));
                    }
                }
                } catch(SQLException d){


                }


        }
        else
        {
            msg.setText("All Fields are Compulsory.");
        }




    }
    @FXML
    protected void  search(ActionEvent e)

    {
        tf_service.clear();
        tf_laundry.clear();
        tf_dining.clear();
        tf_mode.clear();
        tf_payed.clear();
        total.setText(null);
       due.setText(null);
        tf_service.clear();
        //checkbox.getItems().addAll(select);






        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        try {




            String ps = ("select * from finance where Guest_id=" + tf_gid.getText());
            Statement s = connectdb.createStatement();
            ResultSet rs = s.executeQuery(ps);
            while (rs.next()) {
                tf_service.setText(rs.getString("Service_charges"));
                tf_dining.setText(rs.getString("BRcharges"));
                tf_laundry.setText((rs.getString("Laundry_charges")));
             //   checkbox.setValue(rs.getString("Dining"));
                tf_mode.setText(rs.getString("Payment_mode"));
                tf_payed.setText(rs.getString("Amount_paid"));
              total.setText(rs.getString("Total_Biling"));
                due.setText(rs.getString("Amount_due"));
            }
        }catch(SQLException ee)
        {

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
    @FXML
    protected void update(ActionEvent e)
    {
        String select = checkbox.getValue();
        String Guest_id = tf_gid.getText();
        String Service_charges = tf_service.getText();
        String Laundry_charges = tf_laundry.getText();
        String Amount_payed = tf_payed.getText();
        String BRcharges = tf_dining.getText();
        String Payment_mode = tf_mode.getText();
        if (Guest_id.isBlank()) {
            msg.setText("Enter Guest Id ");
        } else {


            try {

                DatabaseConnection connectnow = new DatabaseConnection();
                Connection connectdb = connectnow.getconnection();
                PreparedStatement pcheck = null;
                PreparedStatement pinsert=null;

                pcheck = connectdb.prepareStatement("Select * from finance where Guest_id=?");
                pcheck.setString(1, Guest_id);
                ResultSet rs = pcheck.executeQuery();
                if (rs.isBeforeFirst()) {

                    pinsert = connectdb.prepareStatement("update finance set Dinning='" + select + " 'where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Service_charges='" + Service_charges + " 'where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Laundry_charges='" + Laundry_charges + " 'where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set BRcharges='" + BRcharges + " 'where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Amount_paid='" + Amount_payed + " '  where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Total_billing='" + Service_charges + "'+'" +BRcharges+"'+'" +Laundry_charges+"'  where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Amount_due=Total_billing-'"+ Amount_payed + " '  where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();
                    pinsert = connectdb.prepareStatement("update finance set Payment_mode='" + Payment_mode + " 'where Guest_id= ?");
                    pinsert.setString(1, Guest_id);
                    pinsert.executeUpdate();

                    try{
                       PreparedStatement ptotal = connectdb.prepareStatement("select Total_billing from finance as Total_billing where Guest_id=?;");
                        ptotal.setString(1, Guest_id);
                        rs=ptotal.executeQuery();
                        while (rs.next()) {
                            int cr = rs.getInt("Total_billing");
                            total.setText(String.valueOf(cr));
                        }

                        PreparedStatement pdue = connectdb.prepareStatement("select Amount_due as Amount_due from finance  where Guest_id=?;");
                        pdue.setString(1, Guest_id);
                        rs = pdue.executeQuery();
                        while (rs.next()) {
                            int cr = rs.getInt("Amount_due");
                            due.setText(String.valueOf(cr));
                        }}catch(SQLException ed){

                    }
                    msg.setText("Updated Successfully..");



                } else {
                    Alert ep = new Alert(Alert.AlertType.ERROR);
                    ep.setContentText("Guest does not exist...");
                    ep.show();

                }
            } catch (SQLException ed) {
                ed.printStackTrace();
            }
        }


    }
}