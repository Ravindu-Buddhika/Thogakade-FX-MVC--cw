package Controller.OderViewController;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.Customer;
import modle.Item;
import modle.OderItem;
import modle.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OderViewController implements OderViewService{
    @Override
    public ObservableList<Order> loadAllOrderdata() {
        ObservableList <Order> orders= FXCollections.observableArrayList();
        System.out.println("controller run");
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()){
                orders.add(new Order(
                        resultSet.getString("order_id"),
                        resultSet.getString("customer_id"),
                        resultSet.getDate("order_date").toLocalDate(),
                        resultSet.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public ObservableList <OderItem> getOrder(String orderID) {
        ObservableList <OderItem> oderItems= FXCollections.observableArrayList();
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM order_item WHERE order_id =(?)");
            preparedStatement.setString(1,orderID);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()){
                oderItems.add(new OderItem(
                        resultSet.getString("order_id"),
                        resultSet.getString("item_id"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("discount"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oderItems;
    }

    @Override
    public ObservableList<Customer> getCustomer(String orderID) {
        ObservableList <Customer> customers=FXCollections.observableArrayList();
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM customer WHERE customer_id =(?)");
            preparedStatement.setString(1,orderID);
            ResultSet resultSet= preparedStatement.executeQuery();
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
