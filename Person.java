public class Person {
    String fullName = null;
    String dateOfBirth = null;

    public Person(String fullName, String dateOfBirth){
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName(){
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
