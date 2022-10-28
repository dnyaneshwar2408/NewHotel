package com.example.hiltonhotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class displaycontroller implements Initializable {

    @FXML
    private TableView<TableModel> tableview;

    @FXML
    private TableColumn<TableModel, String> tf_gid;
    @FXML
    private TableColumn<TableModel, String> tf_fname;
    @FXML
    private TableColumn<TableModel, String> tf_no;
    @FXML
    private TableColumn<TableModel, String> tf_ty;
    @FXML
    private TableColumn<TableModel, String> tf_in;
    @FXML
    private TableColumn<TableModel, String> tf_out;
    @FXML
    private TableColumn<TableModel, String> Age;
    @FXML
    private TableColumn<TableModel, String> Laundry;
    @FXML
    private TableColumn<TableModel, String> BR;
    @FXML
    private TableColumn<TableModel, String> Service;
    @FXML
    private TableColumn<TableModel, String> Total;
    @FXML
    private TableColumn<TableModel, String> Due;
    @FXML
    private TableColumn<TableModel, String> mode;

    final ObservableList<TableModel>listview=FXCollections.observableArrayList();

    @Override

    public void initialize(URL url, ResourceBundle rb){
        tf_gid.setCellValueFactory(new PropertyValueFactory<>("Guest_id"));
        tf_fname.setCellValueFactory(new PropertyValueFactory<>("First_name"));
        tf_ty.setCellValueFactory(new PropertyValueFactory<>("Room_type"));
        tf_no.setCellValueFactory(new PropertyValueFactory<>("Room_no"));
        tf_in.setCellValueFactory(new PropertyValueFactory<>("Check_in"));
        tf_out.setCellValueFactory(new PropertyValueFactory<>("Check_out"));
        Age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Laundry.setCellValueFactory(new PropertyValueFactory<>("Laundry"));
        BR.setCellValueFactory(new PropertyValueFactory<>("BR"));
        Service.setCellValueFactory(new PropertyValueFactory<>("Service"));
        mode.setCellValueFactory(new PropertyValueFactory<>("Payment_mode"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        Due.setCellValueFactory(new PropertyValueFactory<>("Due"));
        try
        {
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.getconnection();
           PreparedStatement ps =connectdb.prepareStatement("select guest_info1.Guest_id,First_name,Age,Roomtype,Room_no,Checkin,Checkout,Payment_mode,Laundry_charges,BRcharges,Service_charges,Total_billing,Amount_due from guest_info1 inner join roomdetail on guest_info1.Guest_id=roomdetail.Guest_id   inner join guest_info2 on roomdetail.Guest_id=guest_info2.Guest_id inner join finance on guest_info1.Guest_id=finance.Guest_id;");
            ResultSet r= ps.executeQuery();


            while(r.next())
            {
                listview.add(new TableModel(r.getString("Guest_id"),r.getString("First_name"),r.getString("Age"),r.getString("Roomtype"),r.getString("Room_no"),r.getString("Checkin"),r.getString("Checkout"),r.getString("Laundry_charges"),r.getString("BRcharges"),r.getString("Service_charges"),r.getString("Total_billing"),r.getString("Amount_due"),r.getString("Payment_mode")));
            }
            tableview.setItems(listview);

        }catch(SQLException e)
        {
            e.printStackTrace();

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
