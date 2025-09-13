package Controller.itemViewController;

import javafx.collections.ObservableList;
import modle.Item;

public interface ItemViewService {
    void addItems(Item item);
    void upadateItems(Item item);
    void deleteItems(String id);
    ObservableList<Item> getAllItemDetails();
}
