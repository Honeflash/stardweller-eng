import java.sql.*;

public class db_connect {

    static final String INSERT_INTO_QUERY = "INSERT INTO Words (English, Stardweller) VALUES (?, ?)";

    /**
     * Allows the specified connection variable to be used to connect to a database as specified by the provided url, username, and password
     * @param url - The link to the webpage in the format jdbc.mysql://host:port/name
     * @param user - The username associated with the database
     * @param pwd - The password associated with the database
     * @return Returns the attempt to connect to the database
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static Connection connectToDatabase(String url, String user, String pwd ) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(url, user, pwd); // Connection is attempted here
    }

    /**
     * Returns the query of the database's entire library in ascending order
     * @param st - Statement variable used to execute the database query
     * @param query - The query as a string
     * @throws SQLException
     */
    static void viewAll(Statement st, String query)  throws SQLException {
        ResultSet rs = st.executeQuery(query); // Execute the query

        // Format and print out the results
        System.out.println("\tEnglish\t\t\tStardweller");
        System.out.println("---------------------------------------------------");
        while (rs.next()){
            String engWord = rs.getString("English");
            String strWord = rs.getString("Stardweller");
            System.out.println("\t" + engWord + "\t\t\t" + strWord);
        }
    }

    /**
     * Allows for the insertion of values into the specified database
     * @param c - A representation of the connection to the database
     * @param v1 - The value for column 1
     * @param v2 - The value for column 2
     * @throws SQLException
     */
    static void insert(Connection c, String v1, String v2) throws SQLException {
        // The static String above is used as a template for ensuring that the values are entered properly
        PreparedStatement p = c.prepareStatement(INSERT_INTO_QUERY);
        p.setString(1, v1);
        p.setString(2, v2);
        int affectedRows = p.executeUpdate(); // Perform the insertion

        // Report back the successful insertion
        System.out.println(affectedRows + " row(s) inserted successfully\n");
    }
    
}
