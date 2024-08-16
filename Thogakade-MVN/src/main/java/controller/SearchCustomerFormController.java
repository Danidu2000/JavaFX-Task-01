package controller;

import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Customer;

import java.util.List;

public class SearchCustomerFormController {

    private Customer foundCustomer = null;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNumber;

    @FXML
    private JFXTextField txtSearchID;

    @FXML
    void btnSearchOnAction(ActionEvent ignoredEvent) {
        String id = txtSearchID.getText();

        List<Customer> customerList = ThogakadePOS.getInstance().getConnection();

        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                foundCustomer = customer;
                break;
            }
        }
        if ("".equals(id)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Search");
            alert.setHeaderText("Your searched nothing");
            alert.setContentText("please enter valid customer ID.");
            alert.showAndWait();
        }else if (foundCustomer != null) {
            lblID.setText(foundCustomer.getId());
            lblName.setText(foundCustomer.getTitle()+foundCustomer.getName());
            lblAddress.setText(foundCustomer.getAddress());
            lblNumber.setText(foundCustomer.getNumber());
            lblDob.setText(String.valueOf(foundCustomer.getDob()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setHeaderText("Customer with ID '" + id + "' not found.");
            alert.setContentText("please check the ID or search different ID.");
            alert.showAndWait();

        }


    }

}

