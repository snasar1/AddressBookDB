/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;
import entity.*;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Gokhan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private static DAO contactDAO;
    
    public static void main(String[] args) {
        contactDAO = new ContactDAO();
        addContact(4, "Mike", "Smith", "+1-216-551-6776");
        printContacts();
        Contact contact;
        contact = new Contact(1, "John", "Doe", "+1-215-551-6776");
        contactDAO.update(contact);//Update John
        contact = new Contact(2, "Alice", "Mira", "+1-215-334-4343");
        contactDAO.insert(contact);//Insert Alice
        contact = new Contact(2, "Alice", "Smith", "+1-215-334-4343");
        contactDAO.update(contact);
        contact = new Contact(3, "Sezen", "Aksu", "+90-535-331-4869");
        contactDAO.insert(contact);//Insert Sezen
        printContacts();
        contactDAO.delete(contact);//Delete Sezen
        contact = new Contact(4, "Mike", "Smith", "+1-216-551-6776");
        contactDAO.delete(contact);
        printContacts();
        contact = getContact(0);
        System.out.println(contact.getID() + "-" + contact.getFirstName() + "-" + contact.getLastName() + "-" + contact.getPhoneNumber());
    }
    
    static void addContact(int id, String firstName, String lastName, String phoneNumber) {
        Contact contact;
        contact = new Contact(id, firstName, lastName, phoneNumber);
        contactDAO.insert(contact);
    }
    
    static Contact getContact(int id) {
        Optional<Contact> contact = contactDAO.get(id);
        return contact.orElseGet(() -> new Contact(-1, "Non-exist", "Non-exist", "Non-exist"));
    }
    
    static void printContacts() {
        List<String> headers = contactDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%17s", header);
        }
        System.out.println();
        //Print the results
        List<Contact> contacts = contactDAO.getAll();
        int numberRows = contacts.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%17s%17s%17s%17s", contacts.get(i).getID(), contacts.get(i).getFirstName(), contacts.get(i).getLastName(), contacts.get(i).getPhoneNumber());
            System.out.println();
        }
        
    }
}
