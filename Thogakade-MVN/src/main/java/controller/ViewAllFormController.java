package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAllFormController implements Initializable {

    private List<Customer> customerList = ThogakadePOS.getInstance().getConnection();

    private Customer foundCustomer = null;

    private Customer foundCustomerForProcess = null;

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private JFXButton btnDeleteCustomer;


    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dateDob;

    @FXML
    private Label lblID;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtSearchID;

    @FXML
    void btnDeleteCustomerOnActon() {
        customerList.remove(foundCustomerForProcess);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Success");
        alert.setHeaderText("SUCCESS!");
        alert.setContentText("The customer successfully deleted.");
        alert.showAndWait();

        txtSearchID.setText("");
        lblID.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
        dateDob.setValue(null);

        btnDeleteCustomer.setDisable(true);

        tblCustomer.refresh();
        loadTable();
    }

    @FXML
    void btnReloadOnAction() {
        loadTable();
        tblCustomer.refresh();
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
            btnDeleteCustomer.setDisable(false);

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

        tblCustomer.refresh();
        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));

        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
        titles.add("Miss.");

        cmbTitle.setItems(titles);

        loadTable();

        btnUpdateCustomer.setDisable(true);
        btnDeleteCustomer.setDisable(true);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> setTextToValues(newValue)));

    }

    private void setTextToValues(Customer newValue) {
        if (newValue != null) {
            foundCustomer = newValue;
            foundCustomerForProcess = newValue;

            txtSearchID.setText(newValue.getId());
            lblID.setText(newValue.getId());
            cmbTitle.setValue(newValue.getTitle());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtNumber.setText(newValue.getNumber());
            dateDob.setValue(newValue.getDob());

            btnUpdateCustomer.setDisable(false);
            btnDeleteCustomer.setDisable(false);
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        txtSearchID.setText("");
        lblID.setText(null);
        cmbTitle.setValue(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtNumber.setText(null);
        dateDob.setValue(null);
    }

    private void loadTable(){
        customerList = ThogakadePOS.getInstance().getConnection();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerObservableList.addAll(customerList);

        tblCustomer.setItems(customerObservableList);
    }
}
