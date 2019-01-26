package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditController extends AddController {
    private ContactApp contactApp;

    private Contact selectedContact;

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
        firstName.setPromptText(selectedContact.getFirstName());
        lastName.setPromptText(selectedContact.getLastName());
        patronymic.setPromptText(selectedContact.getPatronymic());
        email.setPromptText(selectedContact.getEmail());
        phone.setPromptText(selectedContact.getPhone());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void getContactAppController(ContactApp contactApp) {
        this.contactApp = contactApp;
    }

    @FXML
    private Button editButton;

    @FXML
    public void edit() throws IOException {
        contactApp.editContact(getSelectedContact(), new Contact(
                firstName.getText().trim(),
                lastName.getText().trim(),
                patronymic.getText().trim(),
                email.getText().trim(),
                phone.getText().trim()));

        Stage stage = (Stage) (editButton.getScene().getWindow());
        stage.close();
        contactApp.start(new Stage());

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
}
