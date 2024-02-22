package com.example.csd214;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {

    @FXML
    private TableView<csd214_newdata> tableView;
    @FXML
    private TableColumn<csd214_newdata,Integer> id;
    @FXML
    private TableColumn<csd214_newdata,String> name;
    @FXML
    private TableColumn<csd214_newdata,Integer> age;
    @FXML
    private TableColumn<csd214_newdata, String> gender;
    ObservableList<csd214_newdata> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<csd214_newdata,Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<csd214_newdata,String>("name"));
        age.setCellValueFactory(new
                PropertyValueFactory<csd214_newdata,Integer>("age"));
        gender.setCellValueFactory(new
                PropertyValueFactory<csd214_newdata,String>("gender"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214_newdata";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM classmates";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                tableView.getItems().add(new csd214_newdata(id, name, age,
                        gender));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}