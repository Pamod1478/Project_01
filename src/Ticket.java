import java.io.FileWriter;      //imported
import java.io.IOException;

public class Ticket {       // Define the Ticket class
    private char row;       // Represents the row of the ticket
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;     // Set the row of the ticket
        this.seat = seat;
        this.price = price;
        this.person = person;
    }


    public void setRow(char row) {
        this.row = row;       // Set the row of the ticket
    }
    public int getRow() {
        return row;         // Return the row of the ticket
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public int getSeat() {
        return seat;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
    public void Ticketinfo() {
        System.out.println("name: "+person.getName());
        System.out.println("surname: "+person.getSurname());
        System.out.println("email: "+person.getEmail());
        System.out.println("row: " + row);
        System.out.println("seat: "+ seat);
        System.out.println("price of ticket: "+ price);
    }
    public void save() {    // Method to save ticket information to a file
        try {
            FileWriter writer = new FileWriter(String.valueOf(row)+seat + ".txt");
            // Create a new FileWriter object with filename based on row and seat
            writer.write("Ticket Information:\n");
            writer.write("Seat: " + seat + "\n");        // Write seat number
            writer.write("Price: £" + price + "\n");
            System.out.println();
            writer.write("Person Information:\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
            System.out.println("Ticket information saved to txt.file.");
        } catch (IOException e) {
            System.out.println("Error saving ticket information to file.");
            e.printStackTrace();
        }
    }
}