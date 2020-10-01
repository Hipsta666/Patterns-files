

public class Main {
    public static void main(String[] args) {

        AccountManagerImpl acc = new AccountManagerImpl();
        Person person1 = new Person("Сергеев Сергей Сергеевич", "3.7.1993");
        Person person2 = new Person("Петров Петр Петрович", "24.12.1988");
        Person person3 = new Person("Иванов Иван Иванович", "3.01.2001");
        Person person4 = new Person("Семенов Семен Семенович", "19.10.1995");
        Person person5 = new Person("Артемов Артем Артемьевич", "11.06.1991");
        try {
            acc.registerNewAccount("Sergeev@gmail.com", "Qwerty123", person1);
            acc.registerNewAccount("PetrovGgmail.com", "Porol321", person2);
            acc.registerNewAccount("IvanovGgmail.com", "SobakaMoyaLubimaya77", person3);
            acc.registerNewAccount("Semenov@gmail.com", "Semen1337", person4);
            acc.registerNewAccount("Artemov@gmail.com", "QAZWSXQWERTY1!", person5);
//            acc.registerNewAccount("Artemov@gmail.com", "QAZWSXQWERTY1!", person5);

//            acc.removeAccount("Semenov@gmail.com", "234223");
            acc.removeAccount("Artemov@gmail.com", "QAZWSXQWERTY1!");

            String login1 = "asdasd@.ru";
            String login2 = "PetrovGgmail.com";
            System.out.println(login1 + " account exists: " + acc.hasAccount(login1));
            System.out.println(login2 + " account exists: " + acc.hasAccount(login2));

            System.out.println("Total accounts: " + acc.numOfAccounts());

            acc.getPerson("asdasd@.ru", "1234");
            acc.getPerson("asdasd@.ru", "2134");
            acc.getPerson("asdasd@.ru", "3123");
            acc.getPerson("asdasd@.ru", "4123");
            acc.getPerson("asdasd@.ru", "3214");


//            acc.getPerson("asdasd@.ru", "3124");
            System.out.println("Wrong attempts: " + AttemptCounter.getInstance().getLogs());

            System.out.println(acc.getPerson("PetrovGgmail.com", "Porol321"));



        } catch (DuplicateAccountException | TooManyLoginAttemptsException | WrongCredentialsException e) {
            System.err.println(e.getMessage());
        }

    }}