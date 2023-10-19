import javax.swing.text.DefaultEditorKit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;

public  class JabaBank {
        Map<String, Person> personsMap;
        JabaBank(){
            personsMap = new HashMap<>();
        }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        JabaBank jabaBank = new JabaBank();
        String database = "jdbc:h2:~/test";
        try {
            Connection connection = DriverManager.getConnection(database);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Welcome to JabaBank");
        System.out.println("Select the option you want:");
        System.out.println("1. Registration.");
        System.out.println("2. Authorization");
        System.out.println("Press any other key to exit");
        int choice = console.nextInt();
        String login;

        switch (choice){
            case 1: String anotherPerson = "yes";
                while (anotherPerson.equalsIgnoreCase("yes")) {
                    System.out.println("Enter login for registration:");
                    login = console.next();
                    jabaBank.personsMap.put(login, Person.registration(jabaBank, login));
                    System.out.println("To register another person write YES, to exit and program EXIT, any other word authorization");
                    anotherPerson = console.next();
                }

                if (anotherPerson.equalsIgnoreCase("exit")){
                    return;
                }
            case 2:
                System.out.println("Enter login for authorization:");
                 login = console.next();
                while (!jabaBank.personsMap.containsKey(login)){
                    System.out.println("This login does not exist in the system, enter an existing login:");
                    login = console.next();
                }
                jabaBank.personsMap.get(login).authorization(jabaBank, login);
                choice = console.nextInt();

                switch (choice){
                    case 1:
                        jabaBank.personsMap.get(login).showInfo(jabaBank, login);
                        System.out.println("Select the option you want:");
                        System.out.println("1. Add money to the account");
                        System.out.println("2. Change password");
                        System.out.println("Press any other key to exit");
                        choice = console.nextInt();
                        switch (choice){
                            case 1: jabaBank.personsMap.get(login).addFunds(jabaBank, login);
                            case 2: jabaBank.personsMap.get(login).changePassword(jabaBank, login);
                        }
                    case 2:
                        jabaBank.personsMap.get(login).addFunds(jabaBank, login);
                    case 3:
                        jabaBank.personsMap.get(login).changePassword(jabaBank, login);
                    case 4: jabaBank.personsMap.get(login).remove(jabaBank, login);
                }
        }
    }

}