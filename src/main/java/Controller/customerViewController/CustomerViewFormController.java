package Controller.customerViewController;

import Controller.itemViewController.ItemViewController;
import Controller.itemViewController.ItemViewService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modle.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewFormController implements Initializable {
    ObservableList<Item> item= FXCollections.observableArrayList();
    ItemViewService itemViewService=new ItemViewController();


    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TableColumn<?, ?> colDis;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TextArea txtItemDescription;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQuantityOnHand;

    @FXML
    private TextField txtSearch;

    @FXML
    void clickAdd(ActionEvent event) {
        Item item = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemQuantityOnHand.getText())
        );

        itemViewService.addItems(item);
        loadItem();
        clearFields();
    }

    @FXML
    void clickDelete(ActionEvent event) {
        itemViewService.deleteItems(txtItemID.getText());
        loadItem();
        clearFields();
    }

    @FXML
    void clickUpdate(ActionEvent event) {
        Item item = new Item(
                txtItemID.getText(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemQuantityOnHand.getText())
        );

        itemViewService.upadateItems(item);
        loadItem();
        clearFields();

    }

    @FXML
    void searchOnType(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDis.setCellValueFactory(new PropertyValueFactory<>("discription"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("stock"));

        loadItem();

        tblItems.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtItemID.setText(newVal.getId());
                txtItemName.setText(newVal.getName());
                txtItemDescription.setText(newVal.getDiscription());
                txtItemPrice.setText(String.valueOf(newVal.getPrice()));
                txtItemQuantityOnHand.setText(String.valueOf(newVal.getStock()));
            }
        });

    }

    private void loadItem() {
        item.clear();
        item= itemViewService.getAllItemDetails();
        tblItems.setItems(item);
    }

    public void clearFields() {
        txtItemID.clear();
        txtItemName.clear();
        txtItemDescription.clear();
        txtItemPrice.clear();
        txtItemQuantityOnHand.clear();
    }
}
