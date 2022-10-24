package DBStructure;

import Controllers.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {

    // Use ResultSet from DB as parameter and set Product Object's attributes and
    // return Product object.
    private static Product getProductsFromResultSet(ResultSet rs) throws SQLException {
        Product product = null;
        if (rs.next()) {
            product = new Product();
            product.setItem(rs.getString("item_name"));
            product.setAmount(rs.getDouble("item_amount"));
            product.setQuantity(rs.getInt("item_qty"));
        }
        return product;
    }

    // Search for all products
    public static ObservableList<Product> searchProduct() throws SQLException, ClassNotFoundException {

        // Declare the SELECT statement
        String select = "SELECT * FROM item_db.product";

        // Execute SELECT statement
        try {
            // ResultSet from dataExecuteQuery method
            ResultSet rs = DBMethods.dataExecuteQuery(select);

            // Send the ResultSet to the getItemList method and get the item object
            ObservableList<Product> productList = getProductList(rs);
            return productList;
        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }

    // Select * from items operation
    private static ObservableList<Product> getProductList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Product> productList = FXCollections.observableArrayList();

        while (rs.next()) {
            productList.add(new Product(rs.getString(rs.getString("item_name")),
                    rs.getDouble("item_amount"), rs.getInt("item_qty")));
        }
        return productList;
    }

    // Delete item with name
    public static void deleteProductWithName(String productName) throws SQLException, ClassNotFoundException {
        // Delete statement
        String updateStatement = "   DELETE FROM item_db.item\n" +
                "         WHERE item_name =" + productName + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to DELETE from item");
            throw e;
        }
    }

    // Insert an item into the item table
    public static void insertProduct(String name, Double amount, Double qty) throws SQLException, ClassNotFoundException {
        // Insert statement
        String updateStatement = "INSERT INTO item_db.item(item_name, item_amount, item_qty)\n" +
                "VALUES('" + name + "','" + amount + "','" + qty + "');\n";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("SAD, failed to insert item :( ");
            throw e;
        }
    }

    // Edit the name of an item
    public static void updateProductName(String nameOld, String nameNew) throws SQLException, ClassNotFoundException {
        String updateStatement = "  UPDATE item_db.item\n" + " SET item_name= '" +
                nameNew + "'\n" + " WHERE item_name = " + nameOld + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Failed to update item name");
            throw e;
        }
    }

    // Edit an item price
    public static void updateProductAmount(String productName, String productAmount) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE item_db.item\n" +
                "       SET item_amount = '" + productAmount + "'\n" +
                "   WHERE item_name = " + productName + ";";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Edit an item quantity
    public static void updateProductQty(String productName, String productQty) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE item_db.item\n" +
                "       SET item_qty = '" + productQty + "'\n" +
                "   WHERE item_name = " + productName + ";";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Run passed in .sql scripts
    public static void runSqlScript(String script) throws Exception {

        // Connect to the DB
        Connection conn = DBMethods.getConnection();
        System.out.println("SQL Script method has been connected");

        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new FileReader("src/db/" + script + ".sql"));

        sr.runScript(reader);
    }
}
