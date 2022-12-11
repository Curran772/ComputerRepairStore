package DBStructure;

import Controllers.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Update extends DBMethods {
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
                        rs.getDouble("item_amount"), rs.getInt("item_qty"), rs.getString("item_image")));
            }

            return products;
        } catch (SQLException e) {
            System.out.println("DB Connection failed at table population!");
            throw e;
        }
    }

    // Use ResultSet from DB as parameter and set Employee Object's attributes and
    // return employee object.
    private static Product getProdFromResultSet(ResultSet rs) throws SQLException {
        Product prod;
        if (rs.next()) {
            prod = new Product(rs.getString("item_name"), rs.getDouble("item_amount"),
                    rs.getInt("item_qty"), rs.getString("item_image"));
            return prod;
        } else {
            return null;
        }
    }

    // Search the products
    public static Product searchProducts(String searchProducts) throws SQLException, ClassNotFoundException {
        // Execute SELECT statement
        try {
            // ResultSet from dataExecuteQuery method
            ResultSet rs = dataExecuteQuery("SELECT * FROM item_db.inventory" +
                    "WHERE item_name=" + searchProducts);

            return getProdFromResultSet(rs);

        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }

    // Edit the name of an item
    public static void updateProductName(String nameOld, String nameNew) throws SQLException {
        String updateStatement = "  UPDATE item_db.inventory\n" + " SET item_name= '" +
                nameNew + "'\n" + " WHERE item_name = " + nameOld + ";";

        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Failed to update item name");
            throw e;
        }
    }

    // Edit an item price
    public static void updateProductAmount(String productName, String productAmount) throws SQLException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE item_db.inventory\n" +
                "       SET item_amount = '" + productAmount + "'\n" +
                "   WHERE item_name = " + productName + ";";
        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Edit an item quantity
    public static void updateProductQty(String productName, String productQty) throws SQLException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE item_db.inventory\n" +
                "       SET item_qty = '" + productQty + "'\n" +
                "   WHERE item_name = '" + productName + "';";
        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }
}
