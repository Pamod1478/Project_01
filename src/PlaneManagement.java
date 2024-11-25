import java.util.InputMismatchException;
import java.util.Scanner;
public class PlaneManagement {
    // Arrays to store seat booking information for each line
    public static int[] LineA = new int[14];
    public static int[] LineB = new int[12];
    public static int[] LineC = new int[12];
    public static int[] LineD = new int[14];
    public static Ticket[] All_tickets=new Ticket[52];  // Array to store ticket information
    public static int Selltickets=0;        // Variable to keep track of the number of tickets sold

    public static void main(String[] args) {
        MainMenu();         // Call the main menu
    }
    public static void MainMenu() {      // Main menu method
        System.out.println("Welcome to the Plane Management application");
        System.out.println("*****************************************************" +
                "\n*                     MENU OPTION                   *" +
                "\n*****************************************************" +
                "\n     1)Buy a seat" +
                "\n     2)Cancel a seat" +
                "\n     3)Find first available seat" +
                "\n     4)Show Seating plan" +
                "\n     5)Print tickets information and total sales" +
                "\n     6)Search ticket" +
                "\n     0)Quit" +
                "\n*****************************************************");
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.println("Please select an option:");
        while (true) {
            try {
                int selectMethod = input.nextInt();
                switch (selectMethod) {         // Switch statement to execute the selected option
                    case 1:
                        buy_seat();     //Call the buy seat
                        break;
                    case 2:
                        cancel_seat();     //Call the cancel seat
                        break;
                    case 3:
                        Find_first_available_seat();        // Find_first_available_seat
                        break;
                    case 4:
                        Show_seating_plan();        //Show_seating_plan
                        break;
                    case 5:
                        print_ticket_info();        // print_ticket_info
                        break;
                    case 6:
                        search_ticket();             //search_ticket
                        break;
                    case 0:
                        System.out.println("Exited the program");   //Display exit message and exit
                        return;
                    default:
                        System.out.println("Invalid number");       //Display invalid number
                        MainMenu();          // Call the main menu method
                        break;
                }
            }
            catch (InputMismatchException e) {      // Catch block to handle non-integer input
                System.out.println("Please enter a correct number of MENU OPTIONS:");
                input.next();
            }
        }
    }
    public static void buy_seat() {           // Method to buy a seat
        Scanner input = new Scanner(System.in);
        System.out.println("Buy a seat");       //Display Buy a seat
        System.out.println("Enter the row_letter (A-D):");
        char row = input.next().toUpperCase().charAt(0);    //  user input and convert it to uppercase

        if (row < 'A' || row > 'D') {            // Validate row input
            System.out.println("Invalid row");
            buy_seat();
            return;
        }
        System.out.println("Enter the seat_number (1-14):");
        int seatNumber = input.nextInt();

        if (seatNumber < 1 || seatNumber > 14) {
            System.out.println("Invalid seat number");
            buy_seat();
            return;
        }
        int rowIndex = row - 'A';       // Calculate the index of the selected row
        int[] selectedLine = null;
        switch (rowIndex) {            // Select the correct row array based on the rowIndex

            case 0:
                selectedLine = LineA;
                break;
            case 1:
                selectedLine = LineB;
                break;
            case 2:
                selectedLine = LineC;
                break;
            case 3:
                selectedLine = LineD;
                break;
            default:
                System.out.println("Invalid row");
                MainMenu();
                return;
        }   // Check if the seat is already bought
        if (selectedLine[seatNumber - 1] == 1) {
            System.out.println("This seat is already bought");
            MainMenu();
            return;
        }   // Check if the seat is Purchased successfully
        selectedLine[seatNumber - 1] = 1;
        System.out.println("Purchased successfully");

        Person person = person_information();       // Get person information and print it
        person.printInfo();
        Ticket ticket = ticket_information(person);     // Get ticket information and print it
        ticket.Ticketinfo();
        if (Selltickets < All_tickets.length) {     //Adding the ticket  to the array of tickets
            All_tickets[Selltickets++] = ticket;
            System.out.println("Seat added to the list");
        } else {
            System.out.println("Cannot add more tickets");
        }
        ticket.save();       // Save the ticket information
        MainMenu();
    }
    public static void cancel_seat() {        // Method to cancel a seat
        Scanner input = new Scanner(System.in);
        System.out.println("Cancel a seat");
        System.out.println("Enter the row letter (A-D):");
        char row = input.next().toUpperCase().charAt(0);

        if (row < 'A' || row > 'D') {       // Check if the entered row is valid
            System.out.println("Invalid row");
            buy_seat();
            return;
        }
        System.out.println("Enter the seat number (1-14):");
        int seatNumber = input.nextInt();       // Read user input for seat number

        if (seatNumber < 1 || seatNumber > 14) {        // Check if the entered seat number is valid
            System.out.println("Invalid seat number");
            MainMenu();
            return;
        }
        int rowIndex = row - 'A';       // Calculate the index of the selected row
        int[] selectedLine = null;
        switch (rowIndex) {
            case 0:
                selectedLine = LineA;
                break;
            case 1:
                selectedLine = LineB;
                break;
            case 2:
                selectedLine = LineC;
                break;
            case 3:
                selectedLine = LineD;
                break;
            default:
                System.out.println("Invalid row");
                MainMenu();
                return;
        }
        if (selectedLine[seatNumber - 1] !=1) {      // Check if the seat is available
            System.out.println("seat available");
            MainMenu();
            return;
        }
        selectedLine[seatNumber - 1] = 0;       // Cancel the selected seat by setting it to 0
        System.out.println("The seat canceled");
        // Remove the canceled ticket from the tickets array
        for (int i = 0; i < Selltickets; i++) {
            if (All_tickets[i].getRow() == row && All_tickets[i].getSeat() == seatNumber) {
                for (int j = 0; j < Selltickets - 1; j++) {
                    All_tickets[j] = All_tickets[j + 1];
                }
                Selltickets--;
                System.out.println("Seat removed from the list");
            }else {
                System.out.println("The seat is not booked");
            }
        }
        MainMenu();
    }
    public static void Find_first_available_seat() {     // Method to find the first available seat
        System.out.println("First Available Seat");
        for (int i = 0; i < LineA.length; i++) {        // Check if the seat is available
            if (LineA[i] == 0) {
                System.out.println("Seat Number A" + (i + 1));
                MainMenu();
                return;     // Exit the method
            }
        }
        for (int i = 0; i < LineB.length; i++) {
            if (LineB[i] == 0) {        // Check if the seat is available
                System.out.println("Seat Number B" + (i + 1));
                MainMenu();
                return;
            }
        }
        for (int i = 0; i < LineC.length; i++) {
            if (LineC[i] == 0) {
                System.out.println("Seat Number C" + (i + 1));
                MainMenu();
                return;
            }
        }
        for (int i = 0; i < LineD.length; i++) {
            if (LineD[i] == 0) {
                System.out.println("Seat Number D" + (i + 1));
                MainMenu();
                return;
            }
        }
        System.out.println("No available seats");
        MainMenu();
    }
    public static void Show_seating_plan() {         // Method to show seating plan
        System.out.print("Row A : ");
        for (int i : LineA) {           // Check if the seat is empty (0) or taken (1)
            if (i == 0) {
                System.out.print("O ");      // Print 'O' for empty seat
            } else {
                System.out.print("X ");     // Print 'X' for taken seat
            }
        }
        System.out.println();
        System.out.print("Row B : ");
        for (int i : LineB) {
            if (i == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println();
        System.out.print("Row C : ");
        for (int i : LineC) {
            if (i == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println();
        System.out.print("Row D : ");
        for (int i : LineD) {
            if (i == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println();
        System.out.println();
        MainMenu();
    }
    public static Person person_information() {          // Method to person information
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.next();

        System.out.print("Enter your surname: ");
        String surname = input.next();

        System.out.print("Enter your email: ");
        String email = input.next();

        Person person = new Person(name, surname, email);
        return person;
    }
    public static Ticket ticket_information(Person person){     //Method to ticket information
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your row(A-D):");
        char row = input.next().toUpperCase().charAt(0);
        System.out.println("Enter your seat():");
        int seatNumber= input.nextInt();
        double price;
        // Check and set the price based on row and seat number
        if( seatNumber>=1 && seatNumber<=5 && (row=='A'||row=='B'||row=='C'||row=='D')){
            price=200;
        }
        else if(seatNumber>=6 && seatNumber<=9 && (row=='A'||row=='B'||row=='C'||row=='D') ){
            price=150;
        }
        else if(seatNumber>=10 && seatNumber<=14 && (row=='A'||row=='B'||row=='C'||row=='D') ){
            price=180;
        }
        else{
            System.out.println("Invalid seat range");
            MainMenu();
            return null;    // Return null as the ticket is not valid
        }
        return new Ticket(row,seatNumber,price,person);
    }
    public static void print_ticket_info(){     //Method to print ticket info
        int TotalAmount = 0;
        //Loop through the tickets array to print ticket information
        for(int i=0; i<Selltickets;i++){
            Ticket ticket = All_tickets[i];
            TotalAmount+=ticket.getPrice();
            ticket.Ticketinfo();        // Call the Ticketinfo method to print ticket details
        }
        System.out.println("Total amount =  £" + TotalAmount);
        MainMenu();
    }
    public static void search_ticket() {        //Method to search ticket
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the row letter (A-D): ");
        char row = input.nextLine().toUpperCase().charAt(0);         // Read and convert to uppercase

        if (row < 'A' || row > 'D') {       // Check if the entered row is valid (A-D)
            System.out.println("Invalid row letter.");
            return;
        }
        System.out.println("Enter the seat number (1-14): ");
        int seat_no = input.nextInt();
        if (seat_no < 1 || seat_no > 14) {   // Check if the entered seat number is valid (1-14)
            System.out.println("Invalid seat number.");
            return;
        }
        int rowIndex = row - 'A';
        int[] selectedLine = null;
        switch (rowIndex) {
            case 0:
                selectedLine = LineA;
                break;
            case 1:
                selectedLine = LineB;
                break;
            case 2:
                selectedLine = LineC;
                break;
            case 3:
                selectedLine = LineD;
                break;
            default:
                System.out.println("Invalid row");
                return;
        }
        if (selectedLine[seat_no - 1] == 0) {
            System.out.println("This seat is available.");
            MainMenu();
            return;
        }
        for (int i = 0; i < Selltickets; i++) {
            // Check if the ticket matches the selected row and seat number
            if (All_tickets[i].getRow() == row && All_tickets[i].getSeat() == seat_no) {
                All_tickets[i].Ticketinfo();
                System.out.println("This seat is booked.");
                MainMenu();
                return;
            }
        }
    }
}











