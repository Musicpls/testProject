import java.util.Map;

public interface AccountActions {
//    static Person registration(JabaBank jabaBank, String login){
//        return Person;
//    };
    void authorization(JabaBank jabaBank, String login);
    void changePassword(JabaBank jabaBank, String login);
    void showInfo(JabaBank jabaBank, String login);
    void showBalance(JabaBank jabaBank, String login);
    void remove( JabaBank jabaBank, String login);
    void addFunds(JabaBank jabaBank, String login);
}
