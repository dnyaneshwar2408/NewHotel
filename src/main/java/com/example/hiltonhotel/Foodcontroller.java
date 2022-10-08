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

public  class Foodcontroller extends  NullPointerException implements Initializable
{
    @FXML
    private Label msg;
    @FXML
    private Label Moving;
    @FXML
    private TextField tf_gid;
    @FXML
    private TextField tf_gname;
    @FXML
    private TextField tf_room;
    @FXML
    private TextField tf_charge;
    @FXML
    private TextField tf_gidd;
    @FXML
    private TextField tf_gnamee;
    @FXML
    private TextField tf_total;
    @FXML
    private TextField tf_chargess;

    @FXML
    public void initialize(URL url,ResourceBundle rb)
    {
        initclock();
    }
    @FXML
    protected void save(ActionEvent e)

    {

        String Guest_id=tf_gid.getText();
        String Guest_name=tf_gname.getText();
        String Room_no=tf_room.getText();
        String Charges=tf_charge.getText();
        String Guestt_id=tf_gidd.getText();
        String Guest_namee=tf_gnamee.getText();
        String Total=tf_total.getText();
        String Charge=tf_chargess.getText();

        if(!tf_gid.getText().isBlank() ||!tf_gname.getText().isBlank() || !tf_room.getText().isBlank() || !tf_charge.getText().isBlank())
        {
            if (!tf_gid.getText().isBlank() && !tf_gname.getText().isBlank() && !tf_room.getText().isBlank() && !tf_charge.getText().isBlank()) {
                DatabaseConnection connectnow = new DatabaseConnection();
                Connection connectdb = connectnow.getconnection();
                PreparedStatement psinsert = null;
                PreparedStatement pscheck = null;
                ResultSet resultSet = null;
                try {
                    pscheck = connectdb.prepareStatement("select * from restaurant where Guest_id= ?");
                    pscheck.setString(1, Guest_id);
                    resultSet = pscheck.executeQuery();
                    if (resultSet.isBeforeFirst()) {

                        Alert ep = new Alert(Alert.AlertType.ERROR);
                        ep.setContentText("Guest Already Exists...");
                        ep.show();
                    } else {

                        psinsert = connectdb.prepareStatement("insert into restaurant VALUES (?,?,?,?)");
                        psinsert.setString(1, Guest_id);
                        psinsert.setString(2, Guest_name);
                        psinsert.setString(3, Room_no);
                        psinsert.setString(4, Charges);

                        psinsert.executeUpdate();
                        msg.setText("Saved Successfully..");
                    }
                } catch (SQLException ep) {
                    ep.printStackTrace();
                }


            }
            else
            {
                msg.setText("All Fields of Restaurant Dining  Are Compulsory");
            }
        }
        else if(!tf_gidd.getText().isBlank() || !tf_gnamee.getText().isBlank() || !tf_total.getText().isBlank() || !tf_chargess.getText().isBlank())
        {
            if (!tf_gidd.getText().isBlank() && !tf_gnamee.getText().isBlank() && !tf_total.getText().isBlank() && !tf_chargess.getText().isBlank()) {
                DatabaseConnection connectnow = new DatabaseConnection();
                Connection connectdb = connectnow.getconnection();
                PreparedStatement psinsert = null;
                PreparedStatement pscheck = null;
                ResultSet resultSet = null;
                try {
                    pscheck = connectdb.prepareStatement("select * from banquet where Guest_id= ?");
                    pscheck.setString(1, Guest_id);
                    resultSet = pscheck.executeQuery();
                    if (resultSet.isBeforeFirst()) {

                        Alert ep = new Alert(Alert.AlertType.ERROR);
                        ep.setContentText("Guest Already Exists...");
                        ep.show();
                    } else {

                        psinsert = connectdb.prepareStatement("insert into banquet VALUES (?,?,?,?)");
                        psinsert.setString(1, Guestt_id);
                        psinsert.setString(2, Guest_namee);
                        psinsert.setString(3, Total);
                        psinsert.setString(4, Charge);

                        psinsert.executeUpdate();
                        msg.setText("Saved Successfully..");
                        tf_gid.clear();
                    }
                } catch (SQLException ep) {
                    ep.printStackTrace();
                }


            }
            else
            {
                msg.setText("All Fields of Banquet Dining  Are Compulsory");
            }
        }


        else
        {
            msg.setText("Select Anyone Dining");
        }
        tf_total.clear();
        tf_room.clear();
        tf_charge.clear();
        tf_gname.clear();
        tf_chargess.clear();
        tf_gnamee.clear();
        tf_gid.clear();
        tf_gidd.clear();



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
    private void initclock()
    {
        Timeline clock=new Timeline(new KeyFrame(Duration.ONE,e->{
            Moving.setLayoutX(Moving.getLayoutX()+1);
            if(Moving.getLayoutX()==793)
            {
                Moving.setLayoutX(0);
                initclock();
            }

        }),new KeyFrame(Duration.millis(50)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
    @FXML
    private void search(ActionEvent e)
    {


        tf_gnamee.clear();
        tf_total.clear();

        tf_chargess.clear();


        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        try {




            String ps = ("select * from banquet where Guest_id=" + tf_gidd.getText());
            Statement s = connectdb.createStatement();
            ResultSet rs = s.executeQuery(ps);
            while (rs.next()) {
                tf_gnamee.setText(rs.getString("Guest_name"));
                tf_total.setText(rs.getString("Total_Guests"));
                tf_chargess.setText((rs.getString("Charges")));


            }
        }catch(SQLException ee)
        {

        }
    }
    @FXML
    protected  void update(ActionEvent e)
    {
        String Guest_id=tf_gid.getText();
        String Guest_name=tf_gname.getText();
        String Room_no=tf_room.getText();
        String Charge=tf_charge.getText();
        String Guestt_id=tf_gidd.getText();
        String Guest_namee=tf_gnamee.getText();
        String Total=tf_total.getText();
        String Charges=tf_chargess.getText();
        if(!tf_gidd.getText().isBlank()) {
            try {
                if (!tf_gidd.getText().isBlank()) {
                    DatabaseConnection connectnow = new DatabaseConnection();
                    Connection connectdb = connectnow.getconnection();
                    PreparedStatement pcheck = null;
                    PreparedStatement pupdate = null;
                    ResultSet rs = null;
                    pcheck = connectdb.prepareStatement("select  * from banquet where Guest_id=?");
                    pcheck.setString(1, Guestt_id);
                    rs = pcheck.executeQuery();

                    if (rs.isBeforeFirst()) {

                        pupdate = connectdb.prepareStatement("Update banquet set Total_Guests='"+Total+"'where Guest_id=? ");
                        pupdate.setString(1,Guestt_id);
                        pupdate.executeUpdate();
                        pupdate = connectdb.prepareStatement("Update banquet set Guest_name='"+Guest_namee+"'where Guest_id=? ");
                        pupdate.setString(1,Guestt_id);
                        pupdate.executeUpdate();
                        pupdate = connectdb.prepareStatement("Update banquet set Charges='"+Charges+"'where Guest_id=? ");
                        pupdate.setString(1,Guestt_id);
                        pupdate.executeUpdate();

                        msg.setText("Updated Successfully..");
                    } else {
                        Alert ep = new Alert(Alert.AlertType.ERROR);
                        ep.setContentText("Guest Does not Exist...");
                        ep.show();
                    }


                } else {
                    msg.setText("Enter Guest id in Banquet Dinning");
                }
            } catch (SQLException ed) {

            }
        }
        else if(!tf_gid.getText().isBlank()) {
            try {
                if (!tf_gid.getText().isBlank()) {
                    DatabaseConnection connectnow = new DatabaseConnection();
                    Connection connectdb = connectnow.getconnection();
                    PreparedStatement pcheck = null;
                    PreparedStatement pupdate = null;
                    ResultSet rs = null;
                    pcheck = connectdb.prepareStatement("select  * from restaurant where Guest_id=?");
                    pcheck.setString(1, Guest_id);
                    rs = pcheck.executeQuery();
                    if (rs.isBeforeFirst()) {
                        pupdate = connectdb.prepareStatement("Update restaurant set Room_no='"+Room_no+"'where Guest_id=? ");
                        pupdate.setString(1,Guest_id);
                        pupdate.executeUpdate();
                        pupdate = connectdb.prepareStatement("Update restaurant set Guest_name='"+Guest_name+"'where Guest_id=? ");
                        pupdate.setString(1,Guest_id);
                        pupdate.executeUpdate();
                        pupdate = connectdb.prepareStatement("Update restaurant set Charges='"+Charge+"'where Guest_id=? ");
                        pupdate.setString(1,Guest_id);
                        pupdate.executeUpdate();
                        msg.setText("Updated Successfully..");
                    } else {
                        Alert ep = new Alert(Alert.AlertType.ERROR);
                        ep.setContentText("Guest Does not Exist...");
                        ep.show();
                    }


                } else {
                    msg.setText("Enter Guest id in Restaurant Dinning");
                }
            } catch (SQLException ed) {

            }
        }
        else
        {
            msg.setText("Select Anyone Dining");
        }
        tf_total.clear();
        tf_room.clear();
        tf_charge.clear();
        tf_gname.clear();
        tf_chargess.clear();
        tf_gnamee.clear();
        tf_gid.clear();
        tf_gidd.clear();
    }

}