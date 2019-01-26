package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

public class ContactFacade extends Observable {

    private ObservableList<Contact> contactList;

    public ContactFacade() {
        contactList = FXCollections.observableArrayList();
    }

    public ObservableList<Contact> getContactList() {
        contactList.sort(Comparator.comparing(Contact::toString));
        return contactList;
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public void deleteContact(Contact contact) {
        contactList.remove(contact);
    }

    public void editContact(Contact selectedContact, Contact newContact) {
        contactList.remove(selectedContact);
        if (newContact.getFirstName().trim().isEmpty()) {
            newContact.setFirstName(selectedContact.getFirstName());
        }
        if (newContact.getLastName().trim().isEmpty()) {
            newContact.setLastName(selectedContact.getLastName());
        }
        if (newContact.getPatronymic().trim().isEmpty()) {
            newContact.setPatronymic(selectedContact.getPatronymic());
        }
        if (newContact.getEmail().trim().isEmpty()) {
            newContact.setEmail(selectedContact.getEmail());
        }
        if (newContact.getPhone().trim().isEmpty()) {
            newContact.setPhone(selectedContact.getPhone());
        }
        contactList.add(newContact);
    }
}
