import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private String middleName;
    private Date birthdayDate;
    private int age;
    private String password;

    public Person(String name, String surname, String middleName, Date birthdayDate, int age, String password) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthdayDate = birthdayDate;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthdayDate() {
        return this.birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
