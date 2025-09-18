package Controller.dashbode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class dashbodeFomeController {

    @FXML
    private Button btnCheckOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnInventory;

    @FXML
    private Button btnPlaceOrder;

    Stage customers=new Stage();
    Stage items=new Stage();
    Stage addOder=new Stage();
    Stage viewOrder=new Stage();


    @FXML
    void clickCheckOrders(ActionEvent event) {
        try {
            viewOrder.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OderViewForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewOrder.setResizable(false);
        viewOrder.show();
    }

    @FXML
    void clickCustomers(ActionEvent event) {
        try {
            customers.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customersView.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        customers.setResizable(false);
        customers.show();

    }

    @FXML
    void clickInventory(ActionEvent event) {
        try {
            items.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/itemViewForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        items.setResizable(false);
        items.show();
    }

    @FXML
    void clickPlaceOrder(ActionEvent event) {
        try {
            addOder.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddOderForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addOder.setResizable(false);
        addOder.show();
    }

}

