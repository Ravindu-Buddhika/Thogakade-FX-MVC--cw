package Controller.customerViewController;

import Controller.itemViewController.ItemViewController;
import Controller.itemViewController.ItemViewService;
import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modle.Customer;
import modle.Item;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewController implements CustomerViewService{
    ObservableList<Customer> customers= FXCollections.observableArrayList();

    @Override
    public void addCustomer(Customer customer) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement( "INSERT INTO customer (customer_id, name, address, email, phone) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhone());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void upadateCustomer(Customer customer) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE customer SET email = ?, phone = ? WHERE customer_id = ?");
            preparedStatement.setString(1,customer.getEmail());
            preparedStatement.setString(2,customer.getPhone());
            preparedStatement.setString(3,customer.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM customer WHERE customer_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                customers.add(new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
