package Controller.OderViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.Customer;
import modle.OderItem;
import modle.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class OderViewFormController implements Initializable {
    ObservableList<Order> orders= FXCollections.observableArrayList();
    OderViewService oderViewService=new OderViewController();
    OderItem oderItem=new OderItem();
    ObservableList<OderItem> oderItems= FXCollections.observableArrayList();
    ObservableList <Customer> customers=FXCollections.observableArrayList();


    @FXML
    private TableColumn<?, ?> colCustEmail;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colCutsAddress;

    @FXML
    private TableColumn<?, ?> colCutsCutsID;

    @FXML
    private TableColumn<?, ?> colCutsPhone;

    @FXML
    private TableColumn<?, ?> colItemDis;

    @FXML
    private TableColumn<?, ?> colItemItemID;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemQTY;

    @FXML
    private TableColumn<?, ?> colOderCustomerID;

    @FXML
    private TableColumn<?, ?> colOderDate;

    @FXML
    private TableColumn<?, ?> colOderOderID;

    @FXML
    private TableColumn<?, ?> colOderTotal;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableView<OderItem> tblItem;

    @FXML
    private TableView<Order> tblOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOderOderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOderCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colOderTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadOrders();


        colItemItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemDis.setCellValueFactory(new PropertyValueFactory<>("dis"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        colCutsCutsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCutsAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCutsPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tblOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadOrderItem(newVal.getOrderId());
                loadCustomer(newVal.getCustomerId());
            }
        });



    }
    private void loadOrders() {
        orders.clear();
        orders=oderViewService.loadAllOrderdata();
        tblOrder.setItems(orders);
    }
    private void loadOrderItem(String orderId){
        oderItems.clear();
        oderItems=oderViewService.getOrder(orderId);
        tblItem.setItems(oderItems);
    }
    private void loadCustomer(String custID){
        customers.clear();
        customers=oderViewService.getCustomer(custID);
        tblCustomer.setItems(customers);
    }
}

