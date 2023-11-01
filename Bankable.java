public interface Bankable {
    Person registration();
    void authorization();
    void changePassword(Person person);
    void delete(Person person);
}
