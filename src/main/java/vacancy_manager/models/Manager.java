package vacancy_manager.models;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Manager {
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    private int id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty patronymic;
    private StringProperty email;
    private StringProperty phone;

    public Manager(String firstName, String lastName, String patronymic, String email, String phone) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public Manager(int id, String firstName, String lastName, String patronymic, String email, String phone) {
        this(firstName, lastName, patronymic, email, phone);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get() + " " + patronymic.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }
}
