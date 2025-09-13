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

    @FXML
    void clickCheckOrders(ActionEvent event) {
        //customers.setScene();

    }

    @FXML
    void clickCustomers(ActionEvent event) {

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

    }

}

