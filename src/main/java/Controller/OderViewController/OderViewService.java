package Controller.OderViewController;

import javafx.collections.ObservableList;
import modle.Customer;
import modle.OderItem;
import modle.Order;

public interface OderViewService {
    ObservableList <Order> loadAllOrderdata();
    ObservableList <OderItem> getOrder(String orderID);
    ObservableList <Customer> getCustomer(String orderID);
}
