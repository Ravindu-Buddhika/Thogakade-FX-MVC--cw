package Controller.customerViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modle.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewFormController implements Initializable {
    ObservableList<Customer> customers= FXCollections.observableArrayList();
    CustomerViewService customerViewService=new CustomerViewController();


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
    private TableView<Customer> tblItems;

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
        Customer customer = new Customer(
                txtItemID.getText(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                txtItemPrice.getText(),
                txtItemQuantityOnHand.getText()
        );

        customerViewService.addCustomer(customer);
        loadCustomer();
        clearFields();
    }

    @FXML
    void clickDelete(ActionEvent event) {
        customerViewService.deleteCustomer(txtItemID.getText());
        loadCustomer();
        clearFields();
    }

    @FXML
    void clickUpdate(ActionEvent event) {
        Customer customer = new Customer(
                txtItemID.getText(),
                txtItemName.getText(),
                txtItemDescription.getText(),
                txtItemPrice.getText(),
                txtItemQuantityOnHand.getText()
        );

        customerViewService.upadateCustomer(customer);
        loadCustomer();
        clearFields();

    }

    @FXML
    void searchOnType(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDis.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("email"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadCustomer();

        tblItems.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtItemID.setText(newVal.getId());
                txtItemName.setText(newVal.getName());
                txtItemDescription.setText(newVal.getAddress());
                txtItemPrice.setText(String.valueOf(newVal.getEmail()));
                txtItemQuantityOnHand.setText(String.valueOf(newVal.getPhone()));
            }
        });

    }

    private void loadCustomer() {
        customers.clear();
        customers= customerViewService.getAllCustomers();
        tblItems.setItems(customers);
    }

    public void clearFields() {
        txtItemID.clear();
        txtItemName.clear();
        txtItemDescription.clear();
        txtItemPrice.clear();
        txtItemQuantityOnHand.clear();
    }
}
