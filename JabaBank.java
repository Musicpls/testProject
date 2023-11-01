import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class JabaBank implements Bankable{
    Scanner console = new Scanner(System.in);
    ArrayList<Person> persons;
    Person person;
    String login;
    String password;

    JabaBank() {
        persons = new ArrayList<>();
    }

    public Person registration() {
        System.out.println("Enter login:");
        String login = console.next();
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                while (person.getLogin().equalsIgnoreCase(login)) {
                    System.out.println("This login already exists, enter another login:");
                    login = console.next();
                }
            }
        }
        System.out.println("Enter first name:");
        String name = console.next();
        System.out.println("Enter last name:");
        String lastName = console.next();
        System.out.println("Set password");
        String password = console.next();
        return new Person(login, name, lastName, password);
    }

    public void authorization() {
        System.out.println("Enter login for authorization:");
        login = console.next();
        for (Person person : persons) {
            while (!login.equalsIgnoreCase(person.getLogin())) {
                System.out.println("You entered a login that is not in the system, enter a login that is in the system:");
                login = console.next();
            }
            System.out.println("The system login has been found, now enter the password:");
            password = console.next();
            while (!password.equalsIgnoreCase(person.getPassword())) {
                System.out.println("You have entered an incorrect password, enter the correct password");
                password = console.next();
            }
            System.out.println("You are logged into your account, select the option you want:");
            System.out.println("1. Show acc info.");
            System.out.println("2. Make a deposit.");
            System.out.println("3. Change password.");
            System.out.println("4. Delete account");
            System.out.println("Press any other key to exit");

            byte choice = console.nextByte();
            switch (choice) {
                case 1 -> person.accInfo();
                case 2 -> person.makeDeposit();
                case 3 -> changePassword(person);
                case 4 -> delete(person);
            }

        }
    }

    public void changePassword(Person person) {
        System.out.println("To change the password, enter the old password:");
        password = console.next();
        while (password.equalsIgnoreCase(person.getPassword())) {
            System.out.println("You have entered an incorrect password, enter the correct password");
            password = console.next();
        }
        System.out.println("You have entered the password correctly, please enter a new password for this account:");
        person.setPassword(password = console.next());
    }

    public void delete(Person person) {
        System.out.println("To delete a profile, enter your password:");
        password = console.next();
        while (password.equalsIgnoreCase(person.getPassword())) {
            System.out.println("You have entered an incorrect password, enter the correct password");
            password = console.next();
        }
        System.out.println("You have entered the correct password, to delete the profile write YES");
        String deletionConfirmation = console.next();
        if (deletionConfirmation.equalsIgnoreCase("yes")) {
            persons.remove(person);
        } else {
            System.out.println("You didn't confirm the deletion");
        }

    }

    public static void main(String[] args) {
        JabaBank jabaBank = new JabaBank();

        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to JabaBank");
        System.out.println("Select the option you want:");
        System.out.println("1. Registration.");
        System.out.println("2. Authorization");
        System.out.println("Press any other digit to exit");
        int choice = console.nextInt();

        switch (choice) {
            case 1:
                String anotherPerson = "yes";
                while (anotherPerson.equalsIgnoreCase("yes")) {
                    jabaBank.persons.add(jabaBank.registration());
                    System.out.println("To register another person write YES, to exit the program EXIT, any other word for authorization");
                    anotherPerson = console.next();
                }

                if (anotherPerson.equalsIgnoreCase("exit")) {
                    return;
                }
            case 2:
                jabaBank.authorization();
        }
    }
}