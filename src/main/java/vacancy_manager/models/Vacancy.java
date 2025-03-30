package vacancy_manager.models;

import javafx.beans.property.*;
import java.io.*;

public class Vacancy implements Serializable {
    private static final long serialVersionUID = 1L;

    // Transient properties (не сериализуются напрямую)
    private transient IntegerProperty id;
    private transient StringProperty title;
    private transient StringProperty description;
    private transient DoubleProperty salary;
    private transient StringProperty managerName;
    private Integer managerId;

    public Vacancy() {
        initProperties();
    }

    public Vacancy(int id, String title, String description,
                   double salary, int managerId, String name) {
        initProperties();
        setId(id);
        setTitle(title);
        setDescription(description);
        setSalary(salary);
        setManagerId(managerId);
        setManagerName(name);
    }

    // Инициализация свойств
    private void initProperties() {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.salary = new SimpleDoubleProperty();
        this.managerName = new SimpleStringProperty();
    }

    // Кастомная сериализация
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(getId());
        out.writeUTF(getTitle());
        out.writeUTF(getDescription());
        out.writeDouble(getSalary());
        out.writeInt(getManagerId());
        out.writeUTF(getManagerName());
    }

    // Кастомная десериализация
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        initProperties();
        setId(in.readInt());
        setTitle(in.readUTF());
        setDescription(in.readUTF());
        setSalary(in.readDouble());
        setManagerId(in.readInt());
        setManagerName(in.readUTF());
    }

    // Геттеры и сеттеры
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName.get();
    }

    public void setManagerName(String managerName) {
        this.managerName.set(managerName);
    }

    public StringProperty managerNameProperty() {
        return managerName;
    }

    @Override
    public String toString() {
        return getTitle() + " (ID: " + getId() + ")";
    }
}