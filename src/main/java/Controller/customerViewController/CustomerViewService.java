package Controller.customerViewController;

import javafx.collections.ObservableList;
import modle.Customer;
import modle.Item;

public interface CustomerViewService {
    void addCustomer(Customer customer);
    void upadateCustomer(Customer customer);
    void deleteCustomer(String id);
    ObservableList<Customer> getAllCustomers();
}
