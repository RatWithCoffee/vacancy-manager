package vacancy_manager.models;

import javafx.beans.property.*;

public class Candidate {

    private final IntegerProperty id;               // Уникальный идентификатор кандидата
    private final StringProperty firstName;         // Имя кандидата
    private final StringProperty lastName;          // Фамилия кандидата
    private final StringProperty patronymic;        // Отчество кандидата
    private final StringProperty email;             // Электронная почта кандидата
    private final StringProperty phone;             // Телефон кандидата
    private final StringProperty cv;                // Ссылка на резюме кандидата
    private final IntegerProperty vacancyId;        // ID вакансии, на которую кандидат подал заявку

    // Конструктор без параметров
    public Candidate() {
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.patronymic = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.cv = new SimpleStringProperty();
        this.vacancyId = new SimpleIntegerProperty();
    }

    // Конструктор с параметрами
    public Candidate(int id, String firstName, String lastName, String patronymic, String email, String phone, String cv, int vacancyId) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.cv = new SimpleStringProperty(cv);
        this.vacancyId = new SimpleIntegerProperty(vacancyId);
    }

    // Геттеры и сеттеры для каждого поля

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
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

    public String getCv() {
        return cv.get();
    }

    public void setCv(String cv) {
        this.cv.set(cv);
    }

    public StringProperty cvProperty() {
        return cv;
    }

    public int getVacancyId() {
        return vacancyId.get();
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId.set(vacancyId);
    }

    public IntegerProperty vacancyIdProperty() {
        return vacancyId;
    }

    // Метод для вывода информации о кандидате в строковом формате
    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id.get() +
                ", firstName='" + firstName.get() + '\'' +
                ", lastName='" + lastName.get() + '\'' +
                ", patronymic='" + patronymic.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", phone='" + phone.get() + '\'' +
                ", cv='" + cv.get() + '\'' +
                ", vacancyId=" + vacancyId.get() +
                '}';
    }
}
