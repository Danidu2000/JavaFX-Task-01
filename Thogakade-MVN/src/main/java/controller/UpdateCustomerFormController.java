package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {

    private Customer foundCustomer = null;

    private Customer foundCustomerForProcess = null;

    private final List<Customer> customerList = ThogakadePOS.getInstance().getConnection();

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private DatePicker dateDob;

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtSearchID;

    @FXML
    private void initialize() {
        // Disable the delete button initially
        btnUpdateCustomer.setDisable(true);
    }

    @FXML
    void btnSearchOnAction() {
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
            cmbTitle.setValue(null);
            txtName.setText(null);
            txtAddress.setText(null);
            txtNumber.setText(null);
            dateDob.setValue(null);

        }else if (foundCustomer != null) {
            lblID.setText(foundCustomer.getId());
            cmbTitle.setValue(foundCustomer.getTitle());
            txtName.setText(foundCustomer.getName());
            txtAddress.setText(foundCustomer.getAddress());
            txtNumber.setText(foundCustomer.getNumber());
            dateDob.setValue(foundCustomer.getDob());

            foundCustomer = null;

            btnUpdateCustomer.setDisable(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setHeaderText("Customer with ID '" + id + "' not found!");
            alert.setContentText("please check the ID or search different ID.");
            alert.showAndWait();

            txtSearchID.setText("");
            lblID.setText(null);
            cmbTitle.setValue(null);
            txtName.setText(null);
            txtAddress.setText(null);
            txtNumber.setText(null);
            dateDob.setValue(null);

        }
    }

    @FXML
    void btnUpdateCustomerOnAction() {
        foundCustomerForProcess.setTitle(cmbTitle.getValue());
        foundCustomerForProcess.setName(txtName.getText());
        foundCustomerForProcess.setAddress(txtAddress.getText());
        foundCustomerForProcess.setNumber(txtNumber.getText());
        foundCustomerForProcess.setDob(dateDob.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Success");
        alert.setHeaderText("SUCCESS!");
        alert.setContentText("The customer successfully updated.");
        alert.showAndWait();

        txtSearchID.setText("");
        lblID.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
        dateDob.setValue(null);

        btnUpdateCustomer.setDisable(true);
    }

    @FXML
    void btnClearOnAction() {
        txtSearchID.setText("");
        lblID.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
        dateDob.setValue(null);

        foundCustomer = null;
        foundCustomerForProcess = null;

        btnUpdateCustomer.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<String> titles = FXCollections.observableArrayList();
            titles.add("Mr.");
            titles.add("Mrs.");
            titles.add("Miss.");

            cmbTitle.setItems(titles);

    }
}
