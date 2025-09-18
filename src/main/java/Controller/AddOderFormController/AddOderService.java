package Controller.AddOderFormController;

import javafx.collections.ObservableList;
import modle.Customer;
import modle.Item;
import modle.OderItem;
import modle.Order;

public interface AddOderService {
    Customer searchCustomer(String name);
    void additems(OderItem item);
    void deleteOders(OderItem item);
    ObservableList<Item> searchItems(String itemName);
    ObservableList<Item> LoadAllItems();
    void UpdateOrder(Order order);
    void addcustomer(Customer customer);

}
