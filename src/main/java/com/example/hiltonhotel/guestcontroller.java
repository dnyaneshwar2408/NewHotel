package com.example.hiltonhotel;


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

import java.sql.*;


import java.time.LocalDate;
import java.util.ResourceBundle;

public  class guestcontroller extends  NullPointerException implements Initializable
{
    @FXML
    private Label msg;
    @FXML
    private TextField tf_gid;
    @FXML
    private TextField tf_gidd;
    @FXML
    private TextField tf_Fname;
    @FXML
    private TextField tf_Lname;
    @FXML
    private TextField tf_Age;
    @FXML
    private TextField tf_Phone;
    @FXML
    private TextField tf_Email;
    @FXML
    private TextArea tf_Add;
    @FXML
    private Label msg1;
    @FXML
    private TextField tf_adult;
    @FXML
    private TextField tf_child;
    @FXML
    private TextField tf_norooms;
    @FXML
    private DatePicker tf_checkin;
    @FXML
    private DatePicker tf_checkout;
    @FXML
    private ChoiceBox<String> checkbox;
    private final String[] roomty={"Suite","Deluxe","Quad"};

    public void initialize(URL arg0,ResourceBundle arg1){
        checkbox.getItems().addAll(roomty);
        }
    @FXML
    protected void gsave(ActionEvent e)

    {
        String Guest_id=tf_gidd.getText();
        String Firstname=tf_Fname.getText();
        String Lastname=tf_Lname.getText();
        String Email=tf_Email.getText();
        String Address=tf_Add.getText();
        String Age=tf_Age.getText();
        String Phoneno=tf_Phone.getText();
        String Adults=tf_adult.getText();
        String Children=tf_child.getText();
        String Roombooked=tf_norooms.getText();
        LocalDate Checkin=tf_checkin.getValue();
        LocalDate Checkout=tf_checkout.getValue();

        if(!tf_gidd.getText().isBlank() &&!tf_Fname.getText().isBlank() && !tf_Email.getText().isBlank() && !tf_Age.getText().isBlank()&&!tf_Phone.getText().isBlank() &&!tf_Add.getText().isBlank()) {
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            PreparedStatement psinsert = null;
            PreparedStatement pscheck = null;
            ResultSet resultSet = null;
            try {
                pscheck = connectdb.prepareStatement("select * from guest_info1 where Guest_id= ?");
                pscheck.setString(1, Guest_id);
                resultSet = pscheck.executeQuery();
                if (resultSet.isBeforeFirst()) {

                    Alert ep = new Alert(Alert.AlertType.ERROR);
                    ep.setContentText("Guest Already Exists...");
                    ep.show();
                } else {

                    psinsert = connectdb.prepareStatement("insert into guest_info1 VALUES (?,?,?,?,?,?,?)");
                    psinsert.setString(1, Guest_id);
                    psinsert.setString(2, Firstname);
                    psinsert.setString(3, Lastname);
                    psinsert.setString(4, Age);
                    psinsert.setString(5, Phoneno);
                    psinsert.setString(6, Email);
                    psinsert.setString(7, Address);
                    psinsert.executeUpdate();
                    msg.setText("Saved Successfully..");
                }
            } catch (SQLException ep) {
                ep.printStackTrace();
            }

            String Roomty=checkbox.getValue();
            try {
                pscheck = connectdb.prepareStatement("select * from guest_info2 where Guest_id= ?");
                pscheck.setString(1, Guest_id);
                resultSet = pscheck.executeQuery();
                if (resultSet.isBeforeFirst()) {

                    Alert ep = new Alert(Alert.AlertType.ERROR);
                    ep.setContentText("Guest Already Exists...");
                    ep.show();
                } else {

                    psinsert = connectdb.prepareStatement("insert into guest_info2 VALUES (?,?,?,?,?,?,?)");
                    psinsert.setString(1, Guest_id);
                    psinsert.setString(2,Adults);
                    psinsert.setString(3, Children);
                    psinsert.setString(4, Roomty);
                    psinsert.setString(5, Roombooked);
                    psinsert.setString(6, Checkin.toString());
                    psinsert.setString(7, Checkout.toString());
                    psinsert.executeUpdate();

                    tf_gidd.clear();
                    tf_gid.clear();
                    tf_Fname.clear();
                    tf_Lname.clear();
                    tf_Email.clear();
                    tf_Add.clear();
                    tf_Phone.clear();
                    tf_Age.clear();
                    tf_adult.clear();
                    tf_child.clear();
                    tf_norooms.clear();
                    checkbox.getItems().clear();


                    tf_checkin.getEditor().clear();
                    tf_checkout.getEditor().clear();
                }
            } catch (SQLException ep) {
                ep.printStackTrace();
            }
        }


        else {
                       msg.setText("All Fields Are Compulsory");
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
    protected void search(ActionEvent e)

    {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        try {




                     String ps = ("select * from guest_info1 where Guest_id=" + tf_gidd.getText());
                     Statement s = connectdb.createStatement();
                     ResultSet rs = s.executeQuery(ps);
                     while (rs.next()) {
                         tf_Fname.setText(rs.getString("First_name"));
                         tf_Lname.setText(rs.getString("Last_name"));
                         tf_Age.setText(rs.getString("Age"));
                         tf_Phone.setText(rs.getString("Phoneno"));
                         tf_Email.setText(rs.getString("Email"));
                         tf_Add.setText(rs.getString("Address"));

                     }
        }catch(SQLException ee)
        {

        }
        try {




            String ps = ("select * from guest_info2 where Guest_id=" + tf_gidd.getText());
            Statement s = connectdb.createStatement();
            ResultSet rs = s.executeQuery(ps);
            while (rs.next()) {
                tf_gid.setText(rs.getString("Guest_id"));
                tf_adult.setText(rs.getString("Adults"));
                tf_child.setText(rs.getString("Children"));
                checkbox.setValue(rs.getString("Roomtype"));
                tf_norooms.setText(rs.getString("Roomsbooked"));
                tf_checkin.setValue(LocalDate.parse(rs.getString("Checkin")));
                tf_checkout.setValue(LocalDate.parse(rs.getString("Checkout")));

            }
        }catch(SQLException ee)
        {

        }
    }
    @FXML
    protected void update(ActionEvent e)
    {
        String Guest_id=tf_gid.getText();
        String GGuest_id=tf_gidd.getText();
        String Firstname=tf_Fname.getText();
        String Lastname=tf_Lname.getText();
        String Email=tf_Email.getText();
        String Address=tf_Add.getText();
        String Age=tf_Age.getText();
        String Phoneno=tf_Phone.getText();
        String Adults=tf_adult.getText();
        String Children=tf_child.getText();
        String Roombooked=tf_norooms.getText();
        LocalDate Checkin=tf_checkin.getValue();
        LocalDate Checkout=tf_checkout.getValue();
        String Roomty=checkbox.getValue();

        if(GGuest_id.isBlank())
        {
            msg.setText("Enter Guest Id ");
        }
        else
        {


            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            PreparedStatement pcheck=null;
            PreparedStatement pupdate=null;
            ResultSet rs=null;
            try {
                pcheck = connectdb.prepareStatement("Select * from guest_info1 where Guest_id=?");
                pcheck.setString(1,GGuest_id);
                rs=pcheck.executeQuery();
                if(rs.isBeforeFirst()) {

                    pupdate = connectdb.prepareStatement("update guest_info1 set First_name='" + Firstname + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();


                    pupdate = connectdb.prepareStatement("update guest_info1 set Last_name='" + Lastname + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();

                    pupdate = connectdb.prepareStatement("update guest_info1 set Age='" + Age + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info1 set Phoneno='" + Phoneno + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info1 set Address='" + Address + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info1 set Email='" + Email + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Adults='" + Adults + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Children='" + Children + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Roomtype='" + Roomty + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Roomsbooked='" + Roombooked + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Checkin='" + tf_checkin.getValue() + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update guest_info2 set Checkout='" + tf_checkout.getValue() + " 'where Guest_id= ?");
                    pupdate.setString(1, GGuest_id);
                    pupdate.executeUpdate();

                    msg.setText("Updated Successfully..");


                    tf_gidd.clear();
                    tf_gid.clear();
                    tf_Fname.clear();
                    tf_Lname.clear();
                    tf_Email.clear();
                    tf_Add.clear();
                    tf_Phone.clear();
                    tf_Age.clear();
                    tf_adult.clear();
                    tf_child.clear();
                    tf_norooms.clear();
                    checkbox.getItems().clear();


                    tf_checkin.getEditor().clear();
                    tf_checkout.getEditor().clear();

                }



                else
                {
                    Alert ep = new Alert(Alert.AlertType.ERROR);
                    ep.setContentText("Guest does not exist...");
                    ep.show();

                }


            }catch(SQLException ed)
            {
                ed.printStackTrace();
            }
        }

    }
}