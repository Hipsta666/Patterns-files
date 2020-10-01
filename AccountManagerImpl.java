import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AccountManagerImpl implements MailAccountManager {
    BufferedReader fileIn = null;
    BufferedWriter fileOut = null;
    String rememberLine;

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException {
        String[] newAccount = {email, password, person.getName(), person.getDateOfBirth()};
        ArrayList<String[]> registeredAccounts = new ArrayList<>();

        try {
            fileIn = new BufferedReader(new FileReader("Server.csv"));
            while ((rememberLine = fileIn.readLine()) != null) {
                String[] registered = rememberLine.substring(1, rememberLine.length() - 1).split(", ");
                registeredAccounts.add(registered);
                if (registered[0].equals(newAccount[0])) throw new DuplicateAccountException("Mail " + registered[0] +
                        " is already in use");
            }
            writeOut(registeredAccounts, newAccount);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            multiCloser(true, true);
        }
    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException {

        boolean connection = true;
        String[] accountRemove = {email, password};
        ArrayList<String[]> registeredAccounts = new ArrayList<>();
        try {
            fileIn = new BufferedReader(new FileReader("Server.csv"));
            while ((rememberLine = fileIn.readLine()) != null) {
                String[] registered = rememberLine.substring(1, rememberLine.length() - 1).split(", ");
                if (accountRemove[0].equals(registered[0]) & accountRemove[1].equals(registered[1])) {
                    connection = false;
                } else {
                    registeredAccounts.add(registered);
                }
            }

            if (connection) {
//                AttemptCounter.getInstance().addLog();
                throw new WrongCredentialsException("Login or password is incorrect, cannot be deleted.");
            }

            writeOut(registeredAccounts, null);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            multiCloser(true, true);
        }
    }


    @Override
    public boolean hasAccount(String email) {

        try {
            fileIn = new BufferedReader(new FileReader("Server.csv"));
            while ((rememberLine = fileIn.readLine()) != null) {
                String[] registered = rememberLine.substring(1, rememberLine.length() - 1).split(", ");
                if (email.equals(registered[0])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            multiCloser(true, false);
        }
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException {

        String[] check = {email, password};

        try {
            fileIn = new BufferedReader(new FileReader("Server.csv"));
            while ((rememberLine = fileIn.readLine()) != null) {
                String[] registered = rememberLine.substring(1, rememberLine.length() - 1).split(", ");
                if (check[0].equals(registered[0]) & check[1].equals(registered[1])) {
                    return  new Person(registered[2], registered[3]);
                }
            }

            AttemptCounter.getInstance().addLog();
            throw new WrongCredentialsException("Login or password is incorrect, try again...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongCredentialsException e) {
            System.err.println(e.getMessage());
        } finally {
            multiCloser(true, false);
        }
        return null;
    }

    @Override
    public int numOfAccounts() {
        int count = 0;
        try {
            fileIn = new BufferedReader(new FileReader("Server.csv"));
            while ((fileIn.readLine()) != null) {
                count += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            multiCloser(true, false);
        }
        return count;
    }

    public void multiCloser(boolean closeIn, boolean closeOut) {
        if (closeIn) {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (closeOut) {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeOut(ArrayList<String[]> accounts, String[] newString) {
        try {
            fileOut = new BufferedWriter(new FileWriter("Server.csv"));
            for (String[] account : accounts) {
                fileOut.write(Arrays.toString(account) + "\n");
            }

            if (newString != null) {
                fileOut.write(Arrays.toString(newString));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
