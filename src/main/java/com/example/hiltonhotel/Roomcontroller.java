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

public  class Roomcontroller extends NullPointerException implements Initializable
{
    @FXML
    private Label ravai,rfull;
    @FXML
    private Label label;
    @FXML
    private TextField tf_Fname;
    @FXML
    private TextField tf_room;
    @FXML
    private TextField tf_gid;
    @FXML
    private DatePicker tf_bfrom;
    @FXML
    private DatePicker tf_btill;
    @FXML
    private ChoiceBox<String> checkbox;
    private final String[] roomty={"Suite","Deluxe","Quad"};
    public void initialize(URL arg0,ResourceBundle arg1){
        checkbox.getItems().addAll(roomty);
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        PreparedStatement ptotal,pfull = null;

        ResultSet rs=null;
        try {
            ptotal = connectdb.prepareStatement("select count(*) as avai from roomdetail");

            rs = ptotal.executeQuery();
            while (rs.next()) {
                int c = rs.getInt("avai");
                rfull.setText(String.valueOf(c));
            }
            pfull = connectdb.prepareStatement("select count(*) as avai from roomdetail ");

            rs = pfull.executeQuery();
            while (rs.next()) {
                int c = rs.getInt("avai");
                c=100-c;
                ravai.setText(String.valueOf(c));
            }
        }catch(SQLException ep)
        {
            ep.printStackTrace();
        }

          }

        @FXML
        protected  void save(ActionEvent e){
     String Roomty=checkbox.getValue();
     String Guest_id=tf_gid.getText();
     String Firstname=tf_Fname.getText();
     String Room_no=tf_room.getText();
     LocalDate Bookedfrom=tf_bfrom.getValue();
     LocalDate Bookedtill=tf_btill.getValue();
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
     if(!tf_gid.getText().isBlank() &&!tf_Fname.getText().isBlank() && !tf_room.getText().isBlank() && !tf_room.getText().isBlank())
     {

         PreparedStatement psinsert = null;
         PreparedStatement pscheck = null;
         ResultSet resultSet = null;
         try {
             pscheck = connectdb.prepareStatement("select * from roomdetail where Guest_id= ?");
             pscheck.setString(1, Guest_id);
             resultSet = pscheck.executeQuery();
             if (resultSet.isBeforeFirst()) {

                 Alert ep = new Alert(Alert.AlertType.ERROR);
                 ep.setContentText("Guest Already Exists...");
                 ep.show();
             } else {

                 psinsert = connectdb.prepareStatement("insert into roomdetail VALUES (?,?,?,?,?,?)");
                 psinsert.setString(1, Guest_id);
                 psinsert.setString(2, Firstname);
                 psinsert.setString(3, Roomty);
                 psinsert.setString(4, Room_no);
                 psinsert.setString(5, Bookedfrom.toString());
                 psinsert.setString(6, Bookedtill.toString());

                 psinsert.executeUpdate();
                 label.setText("Saved Successfully..");
                 PreparedStatement ptotal,pfull = null;

                 ResultSet rs=null;
                 try {
                     ptotal = connectdb.prepareStatement("select count(*) as avai from roomdetail ");

                     rs = ptotal.executeQuery();
                     while (rs.next()) {
                         int c = rs.getInt("avai");
                         rfull.setText(String.valueOf(c));
                     }
                     pfull = connectdb.prepareStatement("select count(*) as avai from roomdetail ");

                     rs = pfull.executeQuery();
                     while (rs.next()) {
                         int c = rs.getInt("avai");
                         c=100-c;
                         ravai.setText(String.valueOf(c));
                     }
                     tf_room.clear();
                     tf_btill.getEditor().clear();
                     tf_Fname.clear();
                     tf_gid.clear();
                     tf_bfrom.getEditor().clear();
                     checkbox.getItems().clear();
                 }catch(SQLException ep)
                 {
                     ep.printStackTrace();
                 }
             }
         } catch (SQLException ep) {
             ep.printStackTrace();
         }
     }
     else
     {
         PreparedStatement ptotal,pfull = null;

         ResultSet rs=null;
         try {
             ptotal = connectdb.prepareStatement("select count(*) as avai from roomdetail");

             rs = ptotal.executeQuery();
             while (rs.next()) {
                 int c = rs.getInt("avai");
                 rfull.setText(String.valueOf(c));
             }
             pfull = connectdb.prepareStatement("select count(*) as avai from roomdetail ");

             rs = pfull.executeQuery();
             while (rs.next()) {
                 int c = rs.getInt("avai");
                 c=100-c;
                 ravai.setText(String.valueOf(c));
             }
         }catch(SQLException ep)
         {
             ep.printStackTrace();
         }
         label.setText("All Fields are Compulsory");
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
    protected void update(ActionEvent e) {
        String Roomty = checkbox.getValue();
        String Guestt_id = tf_gid.getText();
        String Firstname = tf_Fname.getText();
        String Room_no = tf_room.getText();
        LocalDate Bookedfrom = tf_bfrom.getValue();
        LocalDate Bookedtill = tf_btill.getValue();

        if(Guestt_id.isBlank())
        {
            label.setText("Enter Guest id");
        }
        else
        {
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            PreparedStatement pupdate = null;
            PreparedStatement pscheck = null;
            ResultSet resultSet = null;
            try {

            pscheck = connectdb.prepareStatement("select * from roomdetail where Guest_id= ?");
            pscheck.setString(1, Guestt_id);
            resultSet = pscheck.executeQuery();
                if (resultSet.isBeforeFirst()) {


                    pupdate=connectdb.prepareStatement("update roomdetail set Guest_name='" +Firstname+" 'where Guest_id= ?");
                    pupdate.setString(1, Guestt_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update roomdetail set Room_type='" + Roomty + " 'where Guest_id=? ");
                    pupdate.setString(1, Guestt_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update roomdetail set Room_no='" + Room_no + " 'where Guest_id=? ");
                    pupdate.setString(1, Guestt_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update roomdetail set Booked_from='" + tf_bfrom.getValue() + " 'where Guest_id=? ");
                    pupdate.setString(1, Guestt_id);
                    pupdate.executeUpdate();
                    pupdate = connectdb.prepareStatement("update roomdetail set Book_till='" + tf_btill.getValue() + " 'where Guest_id=? ");
                    pupdate.setString(1, Guestt_id);
                    pupdate.executeUpdate();

                    label.setText("Updated Successfully..");
                    tf_room.clear();
                    tf_btill.getEditor().clear();
                    tf_Fname.clear();
                    tf_gid.clear();
                    tf_bfrom.getEditor().clear();
                    checkbox.getItems().clear();

                } else {
                    Alert ep = new Alert(Alert.AlertType.ERROR);
                    ep.setContentText("Guest Does not  Exist...");
                    ep.show();

                }






            } catch(SQLException ed){
                ed.printStackTrace();
            }

            }
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        PreparedStatement ptotal,pfull = null;

        ResultSet rs=null;
        try {
            ptotal = connectdb.prepareStatement("select count(*) as avai from roomdetail");

            rs = ptotal.executeQuery();
            while (rs.next()) {
                int c = rs.getInt("avai");
                rfull.setText(String.valueOf(c));
            }
            pfull = connectdb.prepareStatement("select count(*) as avai from roomdetail ");

            rs = pfull.executeQuery();
            while (rs.next()) {
                int c = rs.getInt("avai");
                c=100-c;
                ravai.setText(String.valueOf(c));
            }
        }catch(SQLException ep)
        {
            ep.printStackTrace();
        }
        }
        @FXML
        private  void search(ActionEvent e)
        {
            tf_room.clear();
            tf_btill.getEditor().clear();
            tf_Fname.clear();
            checkbox.getItems().setAll("Select");
            tf_bfrom.getEditor().clear();
            checkbox.getItems().clear();
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
            try {




                String ps = ("select * from roomdetail where Guest_id=" + tf_gid.getText());
                Statement s = connectdb.createStatement();
                ResultSet rs = s.executeQuery(ps);
                while (rs.next()) {
                    tf_Fname.setText(rs.getString("Guest_name"));
                    tf_room.setText(rs.getString("Room_no"));
                    checkbox.setValue(rs.getString("Room_type"));
                    tf_bfrom.setValue(LocalDate.parse(rs.getString("Booked_from")));
                    tf_btill.setValue(LocalDate.parse(rs.getString("Book_till")));

                }
            }catch(SQLException ee)
            {

            }
        }


    }

