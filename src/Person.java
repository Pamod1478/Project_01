public class Person {       // Defining a public class named 'Person'
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    // Getter method to retrieve the 'name' value
    public String getName() {
        return name;         // Returning the value of 'name'
    }
    // Setter method to set the 'name' value
    public void setName(String name) {
        this.name = name;       // Setting the value of 'name' with the provided value
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }

}