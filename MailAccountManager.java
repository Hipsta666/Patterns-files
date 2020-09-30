public interface MailAccountManager {

    void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException;
    void removeAccount(String email, String password) throws WrongCredentialsException;
    boolean hasAccount(String email);
    Person getPerson(String email, String password) throws TooManyLoginAttemptsException;
    int numOfAccounts();
}
