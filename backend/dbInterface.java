import java.sql.*;
import java.util.Scanner;

public class dbInterface {

    // List of permanent strings to be used
    static final String URL = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5818418";
    static final String USERNAME = "sql5818418";
    static final String PASSWORD = "N6hXtIzRrG";
    static final String MAIN_MENU = "Select an option from the list of available service in this interface:\n"
    + "1) View the entire table\n2) Add to the table\n3) Close connection & Log off\n" +"H) Help\n\nYour Choice: ";
    static final String GET_ALL_QUERY = "SELECT * FROM Words ORDER BY English ASC";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Database variables
        Connection dbConnection;
        Statement dbStatement;

        // User input variables
        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in);
        char menuInput = ' ';
        char insInput = ' ';

        //Other variables
        boolean isInserting = false; // Handles the while loop for inserting into the database's table
        String value1 = "", value2 = ""; // Values for inserting into the database's table
        
        // Initial connection to the database 
        dbConnection = db_connect.connectToDatabase(URL, USERNAME, PASSWORD);
        if (dbConnection.isValid(0)) {
            System.out.println("Connection Established.\nEntering the Database Remote Interface...\n\n");
        }
        dbStatement = dbConnection.createStatement();

        // While loop for continuing until either the connection is interrupted or until the user manually ends the session
        while (dbConnection.isValid(0)) {
            System.out.print(MAIN_MENU);
            menuInput = scn.next().charAt(0); // Get input from the user over their next option
            scn.nextLine(); // This, and all random placements of scn.nextLine() are here to ensure the newline character is properly processed

            if (menuInput == '3') {
                System.out.println("Logging out, please wait...");
                return;
            }

            // Handle processing the user's choice
            switch (menuInput) {
                case '1':
                    db_connect.viewAll(dbStatement, GET_ALL_QUERY); // Use the previous defind query to return all values in the table
                    break;
                case '2':
                    isInserting = true; // Enter the while loop for inserting into the table

                    while (isInserting) {
                        // Get the user's input
                        System.out.print("Insert the word to add in the English column: ");
                        value1 = scn.nextLine();
                        System.out.print("Insert the word to add in the Stardweller column: ");
                        value2 = scn.nextLine();

                        db_connect.insert(dbConnection, value1, value2); // Call the insert function

                        // Confirm if the user would like to continue or not
                        System.out.print("Add another set of values?\nResponse (Y/N): ");
                        insInput = scn.next().charAt(0);

                        // Handle invalid user input
                        while (insInput != 'Y' && insInput != 'y' && insInput != 'N' && insInput != 'n'){
                            System.out.println("You have entered an invalid option. Please, enter a valid option.\n");
                            System.out.print("Add another set of values?\nResponse (Y/N): ");
                            insInput = scn.next().charAt(0);
                        }
                        scn.nextLine();
                        
                        if (insInput == 'N' || insInput == 'n') { isInserting = false; } // Exit the loop if the user chooses to
                    }

                    break;
                case 'H', 'h':
                    // Placeholder
                    break;
                default:
                    System.out.println("You have entered an invalid option. Please, enter a valid option.\n");
                    break;
            }
        }

        // Close all connections, both database and user input related
        dbConnection.close();
        dbStatement.close();
        scn.close();
    }

}
