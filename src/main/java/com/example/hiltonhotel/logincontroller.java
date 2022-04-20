package com.example.hiltonhotel;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public  class logincontroller extends NullPointerException {
    @FXML
    private Label label;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    public void login(ActionEvent e) {

        if (!tf_username.getText().isBlank() && !tf_password.getText().isBlank() ){
            validatelogin(e);

        } else {
            label.setText("Both Username & Password Needed");
        }
    }

    @FXML
    public void signup(ActionEvent e) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            ((Node)(e.getSource())).getScene().getWindow().hide();
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
    @FXML
    public void cancel(ActionEvent e) {

        try {

            ((Node)(e.getSource())).getScene().getWindow().hide();

        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public void validatelogin(ActionEvent e) {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectdb = connectnow.getconnection();
        String verifylogin = "select count(1) from signup where username='" + tf_username.getText() + "' and Password='" + tf_password.getText() + "'";
        try {
            Statement statement = connectdb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                        Parent root1 = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception ep)
                    {
                        ep.printStackTrace();
                    }
                } else {
                    label.setText("Wrong Credentials");
                }
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }

    }

}



