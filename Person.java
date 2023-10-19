import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Person implements AccountActions{
    Scanner console = new Scanner(System.in);
    private String name;
    private String lastName;
    private String password;
    private static int id = 0;
    private double balance;
    Person(String name, String lastName, String login, String password){
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        id++;
        System.out.println("We added a man to the database with the name: " + name);
        System.out.println("Surname: " + lastName);
        System.out.println("Login: " + login);
        System.out.println("System sequence number: " + id);
        System.out.println("-----------------------------------");
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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


    public static Person registration(JabaBank jabaBank, String login){
        Scanner console = new Scanner(System.in);
        while (jabaBank.personsMap.containsKey(login)){
            System.out.println("This login already exists, enter another login:");
            login = console.next();
        }
        System.out.println("Enter first name:");
        String firstName = console.next();
        System.out.println("Enter last name:");
        String lastName = console.next();
        System.out.println("Set password");
        String password = console.next();
        return new Person(firstName, lastName, login, password);
    }
    public void authorization(JabaBank jabaBank, String login){
        System.out.println("The system login has been found, now enter the password:");

        String password = console.next();

        while (!jabaBank.personsMap.get(login).getPassword().equals(password)) {
            System.out.println("You have entered an incorrect password, enter the password again:");
            password = console.next();
        }
        System.out.println("You are logged into your account, select the option you want:");
        System.out.println("1. Show acc info.");
        System.out.println("2. Make a deposit.");
        System.out.println("3. Change password.");
        System.out.println("4. Delete account");
        System.out.println("Press any other key to exit");
    }

    public void changePassword(JabaBank jabaBank, String login) {
        System.out.println("To change the password, enter the previous password:");
        String pass = console.next();
        while (!jabaBank.personsMap.get(login).getPassword().equals(pass)){
            System.out.println("You have entered an incorrect password, enter the password again:");
            password = console.next();
        }
        System.out.println("You have entered the password correctly, enter a new password:");
        password = console.next();
        jabaBank.personsMap.get(login).setPassword(password);
        System.out.println("The password has been updated!");
    }

    @Override
    public void showInfo(JabaBank jabaBank, String login) {
        System.out.println("Firs name: " + jabaBank.personsMap.get(login).getName());
        System.out.println("Last name: " + jabaBank.personsMap.get(login).getLastName());
        System.out.println("Your account balance: " + jabaBank.personsMap.get(login).getBalance());
        System.out.println("Your password:" + jabaBank.personsMap.get(login).getPassword());
    }

    @Override
    public void showBalance(JabaBank jabaBank, String login) {
        System.out.println("Your account balance: " + jabaBank.personsMap.get(login).getBalance());
    }

    @Override
    public void addFunds(JabaBank jabaBank, String login) {
        System.out.println("Enter the amount you want to add:");
        double funds = 0;
        while (!console.hasNextInt()){
            System.out.println("Enter a number without letters:");
            funds = console.nextInt();
        }
        balance += funds;
        System.out.println("Amount successfully added");
        System.out.println("Your account balance is now:" + jabaBank.personsMap.get(login).getBalance());
    }

    @Override
    public void remove(JabaBank jabaBank, String login) {
        System.out.println("If you want to delete the account, then enter the word \"yes\"");
        System.out.println("if you don't want to delete the profile, press any other letters");
        String yes = "yes";
        String no = console.next();
        if (yes.toUpperCase().equals(no.toUpperCase())){
            System.out.println("Delete profile with login: " + login);
            jabaBank.personsMap.remove(login);
            System.out.println("Profile deleted.");
        }else {
            System.out.println("You changed your mind about deleting the profile with login: " +login);
        }
    }
}
