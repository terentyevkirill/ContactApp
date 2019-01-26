package sample.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.model.Contact;
import sample.model.ContactFacade;

import java.io.IOException;

public class ContactApp extends Application {

    private ContactFacade facade;

    @Override
    public void init() {
        facade = new ContactFacade();
    }

    public void addContact(Contact contact) {
        facade.addContact(contact);
    }

    public void editContact(Contact selectedContact, Contact newContact) {
        facade.editContact(selectedContact, newContact);
    }

    public void deleteContact(Contact contact) {
        facade.deleteContact(contact);
    }

    public void setItemsForListView(ListView listView) {
        listView.setItems(facade.getContactList());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/list.fxml"));
        Parent root = loader.load();

        ListController listController = loader.getController();
        listController.getContactAppController(this);

        primaryStage.setTitle("Записная книжка");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
