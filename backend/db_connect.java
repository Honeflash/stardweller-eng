import java.sql.*;

public class db_connect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5818418";
        String user = "sql5818418";
        String password = "N6hXtIzRrG";
        String query = "SELECT * FROM Words";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("Connection Successful.");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String text = rs.getString("Stardweller");
            System.out.println(text);
        }
        
        con.close();
        st.close();   
        System.out.println("Connection Closed.");

    }
    
}
