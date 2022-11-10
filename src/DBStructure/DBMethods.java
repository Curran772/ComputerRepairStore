package DBStructure;

import Controllers.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class DBMethods {

    // Class variables
    private static Connection conn = null;

    // Connect to the database
    public static void connect() throws SQLException {

        // Establish connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://item-db.c2cdh2umtdwx.us-east-2.rds.amazonaws.com:3306/", "root", "fMM4JMBwpsUQTan");
        } catch (SQLException e) {
            System.out.println("Connection failed... SAD");
        }
    }

    // Disconnect from the database
    public static void disconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to disconnect!");
        }
    }

    // Execute the passed in Query statement
    public static ResultSet dataExecuteQuery(String queryStmt) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet crs;
        try {
            // Connect to the database
            connect();
            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(queryStmt);

            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
        } catch (SQLException e) {
            System.out.println("Problem occurred at dataExecuteQuery!");
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
        return crs;
    }

    // Execute update in the database
    public static void dataExecuteUpdate(String sqlStmt) throws SQLException {
        Statement stmt = null;
        try {
            connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
    }

    /**
     * Method that returns an observable list of the items in the database table
     */
    public static ObservableList<Product> getProducts() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();

        // Try to populate the table with data from the MySQL database
        try {
            ResultSet rs = DBMethods.dataExecuteQuery("SELECT * FROM item_db.inventory");

            while (rs.next()) {
                products.add(new Product(rs.getString("item_name"),
                        rs.getDouble("item_amount"), rs.getInt("item_qty")));
            }
            return products;
        } catch (SQLException e) {
            System.out.println("DB Connection failed at table population!");
            throw e;
        }
    }

    // Run passed in sql scripts
    public static void runSqlScript(String script) throws Exception {

        // Connect to the DB
        System.out.println("SQL Script method has been connected");
        connect();
        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new FileReader("src/db/" + script + ".sql"));

        sr.runScript(reader);
    }
}
