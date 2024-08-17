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

    private Customer foundCustomerForProcess = null;

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
                foundCustomerForProcess = customer;
                break;
            }
        }
        if ("".equals(id)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Search");
            alert.setHeaderText("Your search did not return any results!");
            alert.setContentText("please enter valid customer ID.");
            alert.showAndWait();

            lblID.setText(null);
            lblName.setText(null);
            lblAddress.setText(null);
            lblNumber.setText(null);
            lblDob.setText(null);

        }else if (foundCustomer != null) {
            lblID.setText(foundCustomer.getId());
            lblName.setText(foundCustomer.getTitle()+foundCustomer.getName());
            lblAddress.setText(foundCustomer.getAddress());
            lblNumber.setText(foundCustomer.getNumber());
            lblDob.setText(String.valueOf(foundCustomer.getDob()));

            foundCustomer = null;

            btnDeleteCustomer.setDisable(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setHeaderText("Customer with ID '" + id + "' not found!");
            alert.setContentText("please check the ID or search different ID.");
            alert.showAndWait();

            lblID.setText(null);
            lblName.setText(null);
            lblAddress.setText(null);
            lblNumber.setText(null);
            lblDob.setText(null);
            txtSearchID.setText("");

        }
    }

    @FXML
    void btnDeleteCustomerOnAction() {
        customerList.remove(foundCustomerForProcess);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Success");
        alert.setHeaderText("SUCCESS!");
        alert.setContentText("The customer successfully deleted.");
        alert.showAndWait();

        lblID.setText(null);
        lblName.setText(null);
        lblAddress.setText(null);
        lblNumber.setText(null);
        lblDob.setText(null);
        txtSearchID.setText("");

        btnDeleteCustomer.setDisable(true);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        lblID.setText(null);
        lblName.setText(null);
        lblAddress.setText(null);
        lblNumber.setText(null);
        lblDob.setText(null);
        txtSearchID.setText("");

        foundCustomer = null;
        foundCustomerForProcess = null;

        btnDeleteCustomer.setDisable(true);
    }

}

