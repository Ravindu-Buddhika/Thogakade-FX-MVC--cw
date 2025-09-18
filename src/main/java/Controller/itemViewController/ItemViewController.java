package Controller.itemViewController;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemViewController implements ItemViewService {
    ObservableList<Item> items= FXCollections.observableArrayList();
    @Override
    public void addItems(Item item) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement( "INSERT INTO item (item_id, name, description, unit_price, stock) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDiscription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setInt(5, item.getStock());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void upadateItems(Item item) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE item SET unit_price = ?, stock = ? WHERE item_id = ?");
            preparedStatement.setDouble(1,item.getPrice());
            preparedStatement.setInt(2,item.getStock());
            preparedStatement.setString(3,item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItems(String id) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM item WHERE item_id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> getAllItemDetails() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                items.add(new Item(
                    resultSet.getString("item_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("unit_price"),
                    resultSet.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
