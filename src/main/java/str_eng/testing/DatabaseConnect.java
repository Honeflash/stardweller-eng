package str_eng.testing;

import java.io.*;
import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnect {
	static final String INSERT_INTO_QUERY = "INSERT INTO Words (english, stardweller) VALUES (?, ?);";
	static final String VIEW_ALL_QUERY = "SELECT * FROM Words;";

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
			String engWord = rs.getString("english");
			String strWord = rs.getString("stardweller");
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

	/*public static void main(String[] args) {
		File words = new File("src\\main\\java\\str_eng\\testing\\words.txt");
		BufferedReader fr = null;

		if (!words.exists()){
			System.err.println("File does not exist.");
			System.exit(1);
		}

		Dotenv dotenv = Dotenv.load();
		String connString = dotenv.get("DATABASE_URL");
		Connection conn = null;
		Statement stmt;

		try {
			conn = DriverManager.getConnection(connString);
			System.out.println("Success!");
			stmt = conn.createStatement();

			// stmt.execute("DROP TABLE IF EXISTS words;");
			// stmt.execute("CREATE TABLE \"words\" (\"english\" text PRIMARY KEY, \"stardweller\" text);");

			fr = new BufferedReader(new FileReader(words));
			String line;
			while ((line = fr.readLine()) != null) {
				if (!line.isEmpty()){
					String[] parts = line.split(" +");
					insert(conn, parts[0].toLowerCase(), parts[1].toLowerCase());
				}
			}
			
			viewAll(stmt, VIEW_ALL_QUERY);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (conn != null){
				try {
					conn.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}

			try {
					if (fr != null){
						fr.close();
					}
				} catch (Exception f) {
					f.printStackTrace();
				}
		}
	}*/
}
