package Main;
import java.sql.*;

class DBConnect {


    public static ResultSet ExecuteQuery(String query, String user, String password) throws ClassNotFoundException, SQLException {
        Class driver = Class.forName("com.mysql.jdbc.Driver");
        Connection c = null;
        c = DriverManager.getConnection("jdbc:mysql://192.168.1.104/employees",
                user, password);

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery(
                query);


        return rs;
    }

    public static void InsertQuery(String query, String user, String password) throws SQLException, ClassNotFoundException {
        Class driver = Class.forName("com.mysql.jdbc.Driver");
        Connection c = null;
        c = DriverManager.getConnection("jdbc:mysql://192.168.1.104/employees",
                user, password);

        Statement st = c.createStatement();
        st.execute(query);
    }

}
