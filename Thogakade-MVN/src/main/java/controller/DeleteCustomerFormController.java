package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Customer;

import java.util.List;

public class DeleteCustomerFormController {

    @FXML
    private JFXButton btnDeleteCustomer;

    private Customer foundCustomer = null;

    private final List<Customer> customerList = ThogakadePOS.getInstance().getConnection();

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
    private void initialize() {
        // Disable the delete button initially
        btnDeleteCustomer.setDisable(true);
    }

    @FXML
    void btnSearchOnAction(ActionEvent ignoredEvent) {
        String id = txtSearchID.getText();

        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                foundCustomer = customer;
                break;
            }
        }
        if ("".equals(id)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Search");
            alert.setHeaderText("Your search did not return any results!");
            alert.setContentText("please enter valid customer ID.");
            alert.showAndWait();

        }else if (foundCustomer != null) {
            lblID.setText(foundCustomer.getId());
            lblName.setText(foundCustomer.getTitle()+foundCustomer.getName());
            lblAddress.setText(foundCustomer.getAddress());
            lblNumber.setText(foundCustomer.getNumber());
            lblDob.setText(String.valueOf(foundCustomer.getDob()));

            btnDeleteCustomer.setDisable(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setHeaderText("Customer with ID '" + id + "' not found!");
            alert.setContentText("please check the ID or search different ID.");
            alert.showAndWait();

        }
    }

    @FXML
    void btnDeleteCustomerOnAction() {
        customerList.remove(foundCustomer);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Success");
        alert.setHeaderText("SUCCESS!");
        alert.setContentText("The customer successfully deleted.");
        alert.showAndWait();

        lblID.setText("");
        lblName.setText("");
        lblAddress.setText("");
        lblNumber.setText("");
        lblDob.setText("");
        txtSearchID.setText(null);

        btnDeleteCustomer.setDisable(true);
    }

}

