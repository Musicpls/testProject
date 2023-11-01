import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Person implements Accountable {
    Scanner console = new Scanner(System.in);
    private String login;
    private String name;
    private String lastName;
    private String password;
    private static int id = 0;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<>(5);

    public Person(String login, String name, String lastName, String password) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        System.out.println("We added a man to the database with the name: " + name);
        System.out.println("Surname: " + lastName);
        System.out.println("Login: " + login);
        System.out.println("System sequence number: " + id);
        System.out.println("-----------------------------------");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" + "console=" + console + ", login='" + login + '\'' + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", password='" + password + '\'' + ", balance=" + balance + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.balance, balance) == 0 && Objects.equals(console, person.console) && Objects.equals(login, person.login) && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console, login, name, lastName, password, balance);
    }

    public void accInfo() {
        System.out.println("----------Account info----------");
        System.out.println("Login: " + login);
        System.out.println("Name: " + name);
        System.out.println("LastName: " + lastName);
        System.out.println("Balance: " + balance);
        System.out.println("The last five transactions: ");
        showTransactionInfo();
    }

    private void addTransaction(String message) {

        transactions.add(0, message);
        if (transactions.size() > 5) {
            transactions.remove(5);
            transactions.trimToSize();
        }
    }

    public void makeDeposit() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. H:m ");
        double add;
        String cartNumber;
        String cartCVC;
        System.out.println("Specify the amount you want to add to the account:");
        add = console.nextInt();
        System.out.println("Enter the number of the card from which the account will be replenished: ");
        cartNumber = console.next();
        while (isValidCartNumber(cartNumber)) {
            System.out.println("The card number is not in the correct format, enter the card number correctly:");
            cartNumber = console.next(); //There should just be numbers
        }
        long cartNumberToLong = Long.parseLong(cartNumber);

        System.out.println("Enter the cvc code:");
        cartCVC = console.next();
        while (isValidCartCVC(cartCVC)) {
            System.out.println("The CVC number is not in the correct format, enter the card number correctly:");
            cartCVC = console.next();
        }
        System.out.println("You replenished the balance of your account for: " + NumberFormat.getCurrencyInstance().format(add) + " the money was debited from your card: " + cartNumberToLong);
        balance += add;
        System.out.println("Your account balance is: " + balance);
        addTransaction(String.format("A deposit was made at " + formatter.format(dateTime) + NumberFormat.getCurrencyInstance().format(add)));
        showTransactionInfo();
    }

    void showTransactionInfo() {
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    boolean isValidCartNumber(String cartNumber) {
        return !(cartNumber == null | cartNumber.replaceAll(" ", "").length() == 16);
    }

    boolean isValidCartCVC(String cartCvc) {
        return !(cartCvc == null | cartCvc.length() == 3);
    }
}
