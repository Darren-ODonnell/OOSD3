package BankExample;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Bank {

    private static int uniqueIdentifier = 10001;
    private int depositType = 101;
    private int currentType = 102;
    private static String ibanStart = "IEAIB";
    private static int accountNumber = 1000001;
    private static int sortCode = 900102;

    private Map<Integer, Customer> customerCollection;

    public Bank(){
        this.customerCollection = new Hashtable<>();
        Customer customer1 = createCustomer("Jack", "ODonnell", "Dublin", "0851231234");
        createAccount(customer1.getCustomerID(), 1);
        createAccount(customer1.getCustomerID(), 1);


    }

    public Customer createCustomer(String firstName, String surname, String address, String phoneNumber){
        Bank.uniqueIdentifier ++;
        int customerID = Bank.uniqueIdentifier;
        Customer myCustomer = new Customer(firstName, surname, address, phoneNumber, customerID);
        customerCollection.put(customerID, myCustomer);
        return myCustomer;
    }

    public void createAccount(int customerID, int accountType){
        Customer customer = customerCollection.get(customerID);
        Bank.accountNumber++;
        String accountNumber = Bank.ibanStart + Bank.accountNumber + Bank.sortCode;
        Account account = new Account(currentType, accountNumber);
        customer.getCustomerAccounts().add(account);
    }

    public Customer findCustomer(int customerID){
        return customerCollection.get(customerID);
    }

    public void deposit(Customer customer, double amount, String iban){
        synchronized () {
            List<Account> customerAccounts = customer.getCustomerAccounts();
            for (Account acc : customerAccounts) {
                if (acc.getIban().equals(iban)) {
                    acc.setBalance(acc.getBalance() + amount);
                }
            }
    }

    public void withdraw(Customer customer, double amount, String iban){
        List<Account> customerAccounts = customer.getCustomerAccounts();

        for(Account acc: customerAccounts){
            if(acc.getIban().equals(iban)){
                if(acc.getBalance() >= amount)
                    acc.setBalance(acc.getBalance() + amount);
            }
        }
    }

    public static void main(String[] args) {
        new Bank();
    }
}
//As preparation for the etest
//Take this code and make it thread safe and play around with ita
//use notify and waits with processes and such
