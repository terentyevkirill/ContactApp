package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.model.Contact;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    private ContactApp contactApp;

    @FXML
    private ListView listView;
    @FXML
    private Button addButton;

    public void getContactAppController(ContactApp contactApp) {
        this.contactApp = contactApp;
        contactApp.setItemsForListView(listView);
    }

    @FXML
    public void add() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/add.fxml"));
        Parent root = loader.load();
        AddController addController = loader.getController();
        addController.getContactAppController(contactApp);
        Stage stage = (Stage) (addButton.getScene().getWindow());
        stage.setTitle("Добавление контакта");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showInfo() {
        listView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/info.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InfoController infoController = loader.getController();
                infoController.getContactAppController(contactApp);
                infoController.readInfo((Contact) listView.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Контакт \"" + listView.getSelectionModel().getSelectedItem().toString() + "\"");
                assert root != null;
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
    }

    @FXML
    public void openContextMenu() {
        listView.setOnContextMenuRequested(event -> {
            if (listView.getSelectionModel().getSelectedItem() != null) {
                ContextMenu menu = new ContextMenu();
                MenuItem edit = new MenuItem("Изменить");
                MenuItem delete = new MenuItem("Удалить");
                menu.getItems().addAll(edit, delete);
                listView.setContextMenu(menu);
                edit.setOnAction(event1 -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/edit.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.close();
                            EditController editController = loader.getController();
                            editController.setSelectedContact((Contact) listView.getSelectionModel().getSelectedItem());
                            editController.getContactAppController(contactApp);
                            assert root != null;
                            stage.setScene(new Scene(root));
                            stage.setTitle("Изменение контакта");
                            stage.show();
                        }
                );

                delete.setOnAction((event2) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Подтверждение удаления");
                    alert.setContentText("Вы точно хотите удалить контакт?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        contactApp.deleteContact((Contact) listView.getSelectionModel().getSelectedItem());
                    }
                });
            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
