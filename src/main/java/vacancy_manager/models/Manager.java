package vacancy_manager.models;


public class Manager {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Manager(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void print() {
        System.out.println(id);
        System.out.println(email);
        System.out.println(phone);
    }
}