package vacancy_manager.models;

import javafx.beans.property.*;
import java.io.*;

public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;

    // Transient properties
    private transient IntegerProperty id;
    private transient StringProperty firstName;
    private transient StringProperty lastName;
    private transient StringProperty patronymic;
    private transient StringProperty email;
    private transient StringProperty phone;
    private transient StringProperty cv;
    private transient IntegerProperty vacancyId;

    public Candidate() {
        initProperties();
    }

    public Candidate(int id, String firstName, String lastName,
                     String patronymic, String email, String phone,
                     String cv, int vacancyId) {
        initProperties();
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPatronymic(patronymic);
        setEmail(email);
        setPhone(phone);
        setCv(cv);
        setVacancyId(vacancyId);
    }

    private void initProperties() {
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.patronymic = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.cv = new SimpleStringProperty();
        this.vacancyId = new SimpleIntegerProperty();
    }

    // Serialization methods
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(getId());
        out.writeUTF(getFirstName());
        out.writeUTF(getLastName());
        out.writeUTF(getPatronymic());
        out.writeUTF(getEmail());
        out.writeUTF(getPhone());
        out.writeUTF(getCv());
        out.writeInt(getVacancyId());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        initProperties();
        setId(in.readInt());
        setFirstName(in.readUTF());
        setLastName(in.readUTF());
        setPatronymic(in.readUTF());
        setEmail(in.readUTF());
        setPhone(in.readUTF());
        setCv(in.readUTF());
        setVacancyId(in.readInt());
    }

    // ID property
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    // First name property
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public StringProperty firstNameProperty() { return firstName; }

    // Last name property
    public String getLastName() { return lastName.get(); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public StringProperty lastNameProperty() { return lastName; }

    // Patronymic property
    public String getPatronymic() { return patronymic.get(); }
    public void setPatronymic(String patronymic) { this.patronymic.set(patronymic); }
    public StringProperty patronymicProperty() { return patronymic; }

    // Email property
    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public StringProperty emailProperty() { return email; }

    // Phone property
    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public StringProperty phoneProperty() { return phone; }

    // CV property
    public String getCv() { return cv.get(); }
    public void setCv(String cv) { this.cv.set(cv); }
    public StringProperty cvProperty() { return cv; }

    // Vacancy ID property
    public int getVacancyId() { return vacancyId.get(); }
    public void setVacancyId(int vacancyId) { this.vacancyId.set(vacancyId); }
    public IntegerProperty vacancyIdProperty() { return vacancyId; }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", patronymic='" + getPatronymic() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", cv='" + getCv() + '\'' +
                ", vacancyId=" + getVacancyId() +
                '}';
    }
}