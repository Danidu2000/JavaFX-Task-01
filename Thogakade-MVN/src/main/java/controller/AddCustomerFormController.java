package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import model.Customer;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    private static int code = 1;

    private String id = null;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private DatePicker dateDob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    void btnAddCustomerOnAction() {

        String title = cmbTitle.getValue();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String number = txtNumber.getText();
        LocalDate dob = dateDob.getValue();

        if (null == id || null == name || null == title || null == address || null == number || null == dob) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Textfield");
            alert.setHeaderText("Empty Textfield!");
            alert.setContentText("please fill the all textfields.");
            alert.showAndWait();

        } else {

            Customer customer = new Customer(id, title, name, address, number, dob);

            List<Customer> customerList = ThogakadePOS.getInstance().getConnection();
            customerList.add(customer);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Success");
            alert.setHeaderText("SUCCESS!");
            alert.setContentText("The customer successfully added.");
            alert.showAndWait();
            id = generateCode();

            lblID.setText(id);
            txtName.setText(null);
            txtNumber.setText(null);
            txtAddress.setText(null);
            cmbTitle.setValue(null);
            dateDob.setValue(null);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
        titles.add("Miss.");

        cmbTitle.setItems(titles);

            id = nonIncrementGenerateCode();
            lblID.setText(id);
    }

    private static String generateCode() {
        code++;
        return String.format("C%04d", code);
    }

    private static String nonIncrementGenerateCode() {
        return String.format("C%04d", code);
    }

}
