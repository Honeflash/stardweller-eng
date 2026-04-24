package str_eng.setup.apis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import str_eng.setup.database.ConnectionSetup;

// Set up the endpoint for use with the website
@RestController                                             // Endpoint using Springboot REST API framework
@CrossOrigin(origins = "*")                                 // Endpoint usable by Github Pages
public class TranslatorEndpoint {

    // Define private variables for this program containing database query information
    private static String COLUMN_1 = "english";
    private static String COLUMN_2 = "stardweller";
    private static String TABLE_NAME = "words";
    
    private static PreparedStatement setupPreparedStatement(Connection c, String word) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s = ?", COLUMN_2, TABLE_NAME, COLUMN_1);
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, word.toLowerCase());

        return ps;
    }

	private static String compare(String word1, String word2) {
		if (Character.isUpperCase(word1.charAt(0))) {
			return word2.substring(0, 1).toUpperCase() + word2.substring(1);
		} else {
			return word2;
		}
	}

    /**
     * Takes an incoming string and runs it through the database, looking for matching words in another table before returning the translated
     * string to the frontend program. Words not in the database are just returned as is.
     * @param initString - The initial string.
     * @return Returns the fully translated string.
     */
    @GetMapping("/translator")
    public String translatedString(@RequestParam String initString){
        StringBuilder translatedString = new StringBuilder();
        String[] words = initString.split("\\s+");		// Separate the entire untranslated word by whitespaces.

        try {
            Connection c = ConnectionSetup.getConnection();

            for (String word : words) {
				if (word.charAt(0) == '"' && word.charAt(word.length() - 1) == '"') {
					translatedString.append(word);				// The word was encased in quotation marks, meaning it should be avoided
				} else {
					// Set up the prepared statement and query executor on each iteration
					PreparedStatement ps = setupPreparedStatement(c, word);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {							// A match was found, so add the translation to the String builder
						// Check if the first letter needs to be capitalized or not.
						String preppedWord = compare(word, rs.getString(COLUMN_2));
						translatedString.append(preppedWord);
					} else {									// No match was found, so add the original word to the String builder
						translatedString.append(word);
					}

					// Close the ResultSet and PreparedStatement after each use, to avoid keeping unnecessary connections open
					rs.close();
					ps.close();
				}

				translatedString.append(" ");			// Add a space between words
			}
        } catch (SQLException e) {
            e.printStackTrace();
			return "Error: Database query failed — " + e.getMessage();
        }

        return translatedString.toString().trim();          // Return the completed string, whitespace trimmed
    }
    
}
