package BankExample;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private List<Account> customerAccounts;
    private int customerID;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer(String firstName, String surname, String address, String phoneNumber, int customerID) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.customerAccounts = new ArrayList<>();
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Account> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(List<Account> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerAccounts=" + customerAccounts +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}
