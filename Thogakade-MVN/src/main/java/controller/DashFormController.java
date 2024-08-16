package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashFormController implements Initializable {

    private Stage addCustomerPageStage;
    private Stage searchCustomerPageStage;
    private Stage deleteCustomerPageStage;
    private Stage updateCustomerPageStage;
    private Stage viewAllPageStage;

    @FXML
    private JFXButton btnAddCustomerForm;

    @FXML
    private JFXButton btnDeleteCustomerForm;

    @FXML
    private JFXButton btnSearchCustomerForm;

    @FXML
    private JFXButton btnUpdateCustomerForm;

    @FXML
    private JFXButton btnViewAllForm;

    @FXML
    void btnUpdateCustomerFormOnAction(ActionEvent event) {
        if (null== updateCustomerPageStage){
            updateCustomerPageStage =new Stage();
        }
        try {
            btnUpdateCustomerForm.setDisable(true);

            updateCustomerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/update_customer_form.fxml"))));
            updateCustomerPageStage.show();

            updateCustomerPageStage.setOnCloseRequest((WindowEvent we) -> {
                btnUpdateCustomerForm.setDisable(false);
                updateCustomerPageStage = null;
            });

            updateCustomerPageStage.setOnHidden((WindowEvent we) -> {
                btnUpdateCustomerForm.setDisable(false);
                updateCustomerPageStage = null;
            });
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddCustomerFormOnAction(ActionEvent event) {
        if (null== addCustomerPageStage){
            addCustomerPageStage =new Stage();
        }
        try {
            btnAddCustomerForm.setDisable(true);

            addCustomerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_customer_form.fxml"))));
            addCustomerPageStage.show();

            addCustomerPageStage.setOnCloseRequest((WindowEvent we) -> {
                btnAddCustomerForm.setDisable(false);
                addCustomerPageStage = null;
            });

            addCustomerPageStage.setOnHidden((WindowEvent we) -> {
                btnAddCustomerForm.setDisable(false);
                addCustomerPageStage = null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteCustomerFormOnAction(ActionEvent event) {
        if (null== deleteCustomerPageStage){
            deleteCustomerPageStage =new Stage();
        }
        try {
            btnDeleteCustomerForm.setDisable(true);

            deleteCustomerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/delete_customer_form.fxml"))));
            deleteCustomerPageStage.show();

            deleteCustomerPageStage.setOnCloseRequest((WindowEvent we) -> {
                btnDeleteCustomerForm.setDisable(false);
                deleteCustomerPageStage = null;
            });

            deleteCustomerPageStage.setOnHidden((WindowEvent we) -> {
                btnDeleteCustomerForm.setDisable(false);
                deleteCustomerPageStage = null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchCustomerFormOnAction(ActionEvent event) {
        if (null== searchCustomerPageStage){
            searchCustomerPageStage =new Stage();
        }
        try {
            btnSearchCustomerForm.setDisable(true);

            searchCustomerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/search_customer_form.fxml"))));
            searchCustomerPageStage.show();

            searchCustomerPageStage.setOnCloseRequest((WindowEvent we) -> {
                btnSearchCustomerForm.setDisable(false);
                searchCustomerPageStage = null;
            });

            searchCustomerPageStage.setOnHidden((WindowEvent we) -> {
                btnSearchCustomerForm.setDisable(false);
                searchCustomerPageStage = null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewAllFormOnAction(ActionEvent event) {
        if (null== viewAllPageStage){
            viewAllPageStage =new Stage();
        }
        try {
            btnViewAllForm.setDisable(true);

            viewAllPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_all_form.fxml"))));
            viewAllPageStage.show();

            viewAllPageStage.setOnCloseRequest((WindowEvent we) -> {
                btnViewAllForm.setDisable(false);
                viewAllPageStage = null;
            });

            viewAllPageStage.setOnHidden((WindowEvent we) -> {
                btnViewAllForm.setDisable(false);
                viewAllPageStage = null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

