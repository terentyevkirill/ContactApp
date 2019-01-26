package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private ContactApp contactApp;

    @FXML
    protected TextField firstName;
    @FXML
    protected TextField lastName;
    @FXML
    protected TextField patronymic;
    @FXML
    protected TextField email;
    @FXML
    protected TextField phone;

    @FXML
    private Button saveButton;
    @FXML
    protected Button cancelButton;

    public void getContactAppController(ContactApp contactApp) {
        this.contactApp = contactApp;
    }

    @FXML
    public void save() throws IOException {
        if (validateInput()) {
            contactApp.addContact(new Contact(
                    firstName.getText().trim(),
                    lastName.getText().trim(),
                    patronymic.getText().trim(),
                    email.getText().trim(),
                    phone.getText().trim()));
            Stage stage = (Stage) (saveButton.getScene().getWindow());
            stage.close();
            contactApp.start(new Stage());
        }
    }

    boolean validateInput() {
        return !firstName.getText().trim().isEmpty() && !lastName.getText().trim().isEmpty() && !patronymic.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !phone.getText().trim().isEmpty();
    }

    @FXML
    public void cancel() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Подтверждение отмены");
        alert.setContentText("Вы точно хотите отменить ввод?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Stage stage = (Stage) (cancelButton.getScene().getWindow());
            stage.close();
            contactApp.start(new Stage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
