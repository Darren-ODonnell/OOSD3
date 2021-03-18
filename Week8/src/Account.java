public class Account{

    private int accountType;
    private double balance;
    private String iban;

    public Account(int accountType, String iban) {
        this.accountType = accountType;
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountType=" + accountType +
                ", balance=" + balance +
                ", iban='" + iban + '\'' +
                '}';
    }
}
