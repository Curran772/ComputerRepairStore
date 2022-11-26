package DBStructure;

import Controllers.Product;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Update extends DBMethods {

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
    public static Product searchProducts(String searchProducts, String table) throws SQLException, ClassNotFoundException {
        // Execute SELECT statement
        try {
            // ResultSet from dataExecuteQuery method
            ResultSet rs = dataExecuteQuery("SELECT * FROM item_db." + table +
                    "WHERE item_name=" + searchProducts);

            return getProdFromResultSet(rs);

        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }


    // Insert an item into the item table
    public static void insertProductToUser(ObservableList<Product> products) {
        // Insert statement
        try {
            // Clear table of prior data
            String delete = "DELETE FROM item_db.user_selection;";
            dataExecuteUpdate(delete);

            for (Product product : products) {
                String name = product.getItem();
                double amount = product.getAmount();
                int qty = product.getQuantity();

                String update = "INSERT INTO item_db.user_selection (item_name, item_amount, item_qty) " +
                        "VALUES('" + name + "','" + amount + "','" + qty + "');";

                // Insert new table data
                dataExecuteUpdate(update);
            }

        } catch (SQLException e) {
            System.out.println("Failed at insertProductToUser :(");
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
                "   WHERE item_name = " + productName + ";";
        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }
}
