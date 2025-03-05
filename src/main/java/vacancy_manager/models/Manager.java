package vacancy_manager.models;


public class Manager {

    private int id;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String email;
    private String phone;

    public Manager(int id, String firstName, String lastName, String patronymic, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.patronymic = patronymic;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + patronymic;
    }

    public void print() {
        System.out.println(id);
        System.out.println(email);
        System.out.println(phone);
    }
}