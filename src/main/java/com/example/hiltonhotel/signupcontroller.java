package com.example.hiltonhotel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.*;
public  class signupcontroller extends NullPointerException
{

    @FXML
    private Label msg;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_lastname;
    @FXML
    private TextField tf_firstname;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_passs;
    @FXML
    private PasswordField tf_cpass;

    @FXML
    protected void signupbtn(ActionEvent e)
    {
        String username=tf_username.getText();
        String Firstname=tf_firstname.getText();
        String Lastname=tf_lastname.getText();
        String Email=tf_email.getText();
        String Password=tf_passs.getText();
        String Confirmpassword=tf_cpass.getText();
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        PreparedStatement psinsert=null;
        PreparedStatement pscheck=null;
        ResultSet resultSet=null;
       if(!tf_username.getText().isBlank() && !tf_firstname.getText().isBlank() && !tf_lastname.getText().isBlank() && !tf_email.getText().isBlank() && !tf_passs.getText().isBlank() &&!tf_cpass.getText().isBlank() ) {
           try {
               pscheck = connectdb.prepareStatement("select * from signup where username= ?");
               pscheck.setString(1, username);
               resultSet = pscheck.executeQuery();
               if (resultSet.isBeforeFirst()) {

                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setContentText("YOU CANNOT USE THIS USERNAME.");
                   alert.show();
               } else {

                   psinsert = connectdb.prepareStatement("insert into signup VALUES (?,?,?,?,?,?)");
                   psinsert.setString(1, username);
                   psinsert.setString(2, Firstname);
                   psinsert.setString(3, Lastname);
                   psinsert.setString(4, Email);
                   psinsert.setString(5, Password);
                   psinsert.setString(6, Confirmpassword);
                   psinsert.executeUpdate();
                   msg.setText("Registered Successfully!!");
               }
           } catch (SQLException ep) {
               ep.printStackTrace();
           }
       }
       else{
           msg.setText("All Fields Are compulsory...");

       }

    }
    @FXML
    protected void golog(ActionEvent e)
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
        }}}