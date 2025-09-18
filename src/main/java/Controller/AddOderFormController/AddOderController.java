package Controller.AddOderFormController;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.Customer;
import modle.Item;
import modle.OderItem;
import modle.Order;

import java.sql.*;

public class AddOderController implements AddOderService {
    @Override
    public Customer searchCustomer(String name) {
        Customer customer = new Customer();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE phone = ?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;
    }

    @Override
    public void additems(OderItem item) {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO order_item (order_id, item_id, qty, discount, price) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,item.getOderID());
            preparedStatement.setString(2,item.getItemID());
            preparedStatement.setInt(3, (item.getQty()));
            preparedStatement.setDouble(4, item.getDis());
            preparedStatement.setDouble(5,item.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteOders(OderItem item) {
        String id= item.getOderID();
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM order_item WHERE order_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> searchItems(String itemName) {
        ObservableList <Item> items= FXCollections.observableArrayList();
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE name LIKE ?");
            preparedStatement.setString(1, "%" + itemName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString("item_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unit_price"),
                        resultSet.getInt("stock")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public ObservableList<Item> LoadAllItems() {
        return null;
    }

    @Override
    public void UpdateOrder(Order order) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO orders (order_id, customer_id, order_date, total) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1,order.getOrderId());
            preparedStatement.setString(2,order.getCustomerId());
            preparedStatement.setDate(3, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDouble(4,order.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addcustomer(Customer customer) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO customer (customer_id, name, address, email, phone)VALUES (?, ?, ?, ?, ?)");
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

}
