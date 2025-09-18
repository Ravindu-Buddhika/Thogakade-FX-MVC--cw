package Controller.AddOderFormController;

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
import modle.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddOderFormController implements Initializable {
    AddOderService addOderService=new AddOderController();
    ItemViewService itemViewService=new ItemViewController();
    ObservableList <Item> items= FXCollections.observableArrayList();
    ObservableList <Cart> cart=FXCollections.observableArrayList();

    @FXML
    private Button btDel;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCheckOut;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private TableColumn<?, ?> colCartDis;

    @FXML
    private TableColumn<?, ?> colCartID;

    @FXML
    private TableColumn<?, ?> colCartName;

    @FXML
    private TableColumn<?, ?> colCartPrice;

    @FXML
    private TableColumn<?, ?> colCartQTY;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colItemPrice;

    @FXML
    private TableColumn<?, ?> colItemStock;

    @FXML
    private TableColumn<?, ?> coltemDiscription;

    @FXML
    private TableView<Cart> tblCart;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private Spinner<Integer> txtItemQTY;

    @FXML
    private TextField txtOderID;

    @FXML
    private TextField txtSearItem;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtItemPrice;

    @FXML
    void SearchItem(KeyEvent event) {
        String itemName = txtSearItem.getText();
        loadItems(itemName);
    }

    @FXML
    void addBtnClicked(ActionEvent event) {
        String id = txtItemID.getText();
        String name = txtItemName.getText();
        int qty = txtItemQTY.getValue();
        int dis = Integer.parseInt(txtDiscount.getText());
        String price = txtItemPrice.getText();


        Cart cartItem = new Cart(id, name, qty, dis, price);
        cart.add(cartItem);

        tblCart.getItems().add(cartItem);

        OderItem oderItem=new OderItem(
                txtOderID.getText(),
                txtItemID.getText(),
                txtItemQTY.getValue(),
                Integer.parseInt(txtDiscount.getText()),
                Double.parseDouble(txtItemPrice.getText())
        );

        addOderService.additems(oderItem);

    }

    @FXML
    void clearBtnClicked(ActionEvent event) {
        txtItemID.clear();
        txtItemName.clear();
        txtItemQTY.getValueFactory().setValue(1);
        txtDiscount.clear();
        txtItemPrice.clear();
    }

    @FXML
    void clikedBtnDel(ActionEvent event) {
        OderItem oderItem=new OderItem(
                txtOderID.getText(),
                txtItemID.getText(),
                txtItemQTY.getValue(),
                Integer.parseInt(txtDiscount.getText()),
                Double.parseDouble(txtItemPrice.getText())
        );

        addOderService.deleteOders(oderItem);
        tblCart.getItems().clear();
    }

    @FXML
    void searchCustomer(KeyEvent event) {
        String number = txtContactNumber.getText();
        if (number.length() == 10) {
            Customer customer = addOderService.searchCustomer(number);

            if (customer != null) {
                txtCustomerID.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerEmail.setText(customer.getEmail());
            }
        }

    }
    public void loadItems(String ItemName){
        items.clear();
        items=addOderService.searchItems(ItemName);
        tblItem.setItems(items);

    }
    public void loadAllItems(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        coltemDiscription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tblItem.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtItemID.setText(newVal.getId());
                txtItemName.setText(newVal.getName());
                txtItemPrice.setText(String.valueOf(newVal.getPrice()));
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        txtItemQTY.setValueFactory(valueFactory);

        colCartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCartQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCartDis.setCellValueFactory(new PropertyValueFactory<>("dis"));
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    @FXML
    void clickedCheckOut(ActionEvent event) {
        double totalPrice = 0;

        for (Cart cartItem : cart) {
            double price = Double.parseDouble(cartItem.getPrice());
            int qty = cartItem.getQty();
            double discount = cartItem.getDis(); // Assuming it's already in percentage (e.g., 10 for 10%)

            double itemTotal = (price * qty) * (1 - discount / 100.0);
            totalPrice += itemTotal;
        }
        txtTotal.setText(String.valueOf(totalPrice));
    }

    @FXML
    void clickedDone(ActionEvent event) {
        Order order=new Order();
        //order.setOrderDate();
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        order.setOrderId(txtOderID.getText());
        order.setCustomerId(txtCustomerID.getText());
        order.setOrderDate(LocalDate.parse(formattedDate));
        order.setTotal(Double.parseDouble(txtTotal.getText()));

        addOderService.UpdateOrder(order);

        txtOderID.clear();
        txtCustomerID.clear();
        txtTotal.clear();
        tblCart.getItems().clear();
        txtCustomerID.clear();
        txtCustomerName.clear();
        txtContactNumber.clear();
        txtCustomerAddress.clear();
        txtCustomerEmail.clear();
        txtItemPrice.clear();
        txtItemName.clear();
        txtItemID.clear();
        txtSearItem.clear();
    }
    @FXML
    void AddCustomer(ActionEvent event) {
        Customer customer=new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                txtCustomerEmail.getText(),
                txtContactNumber.getText()
        );
        addOderService.addcustomer(customer);
    }
}


