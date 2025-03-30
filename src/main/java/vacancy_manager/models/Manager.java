package vacancy_manager.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.*;

public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private transient StringProperty firstName;
    private transient StringProperty lastName;
    private transient StringProperty patronymic;
    private transient StringProperty email;
    private transient StringProperty phone;

    public Manager() {
        initProperties();
    }

    public Manager(int id, String firstName, String lastName,
                   String patronymic, String email, String phone) {
        this.id = id;
        initProperties();
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setEmail(email);
        setPhone(phone);
    }

    private void initProperties() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.patronymic = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
    }

    // Custom serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(id);
        out.writeUTF(getFirstName());
        out.writeUTF(getLastName());
        out.writeUTF(getPatronymic());
        out.writeUTF(getEmail());
        out.writeUTF(getPhone());
    }

    // Custom deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        initProperties();
        setFirstName(in.readUTF());
        setLastName(in.readUTF());
        setPatronymic(in.readUTF());
        setEmail(in.readUTF());
        setPhone(in.readUTF());
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    @Override
    public String toString() {
        if (firstName == null || lastName == null || patronymic == null) {
            return "";
        }
        return getFirstName() + " " + getLastName() + " " + getPatronymic();
    }
}