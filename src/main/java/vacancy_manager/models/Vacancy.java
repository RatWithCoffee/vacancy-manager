package vacancy_manager.models;

import javafx.beans.property.*;

public class Vacancy {

    private  IntegerProperty id;
    private final StringProperty title;
    private final StringProperty description;
    private final DoubleProperty salary;
    private StringProperty managerName;
    private int managerId;

    public Vacancy(int id, String title, String description, double salary, String managerName) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.salary = new SimpleDoubleProperty(salary);
        this.managerName = new SimpleStringProperty(managerName); // Initialize correctly
    }

    public Vacancy(String title, String description, double salary, int managerId) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.salary = new SimpleDoubleProperty(salary);
        this.managerId = managerId; // Initialize correctly
    }

    public Vacancy(int id, String title, String description, double salary, int managerId, String name) {
        this(title, description,  salary,  managerId,  name);
        this.id = new SimpleIntegerProperty(id);
    }

    public Vacancy(String title, String description, double salary, int managerId, String name) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.salary = new SimpleDoubleProperty(salary);
        this.managerId = managerId;
        this.managerName = new SimpleStringProperty(name);
    }

    public int getManagerId() {
        return managerId;
    }

    // Getter for managerName (JavaBeans convention)
    public String getManagerName() {
        return managerName.get();
    }

    // Getter for managerName as a property (required for PropertyValueFactory)
    public StringProperty managerNameProperty() {
        return managerName;
    }

    // Other getters for the remaining fields
    public int getId() {
        return id.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public double getSalary() {
        return salary.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }


    public void setManagerName(String managerName) {
        this.managerName.set(managerName);
    }
}
