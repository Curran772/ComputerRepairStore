package DBStructure;

import Objects.Product;
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

    /**
     * Get the quantity of the passed in item from the db table
     */
    public static int getQuantity(String item) throws SQLException {
        int qty = 0;

        ResultSet rs = Update.dataExecuteQuery("SELECT item_qty FROM item_db.inventory " +
                "WHERE item_name = '" + item + "';");

        // Loop through the result set to get the item quantity
        while (rs.next()) {
            qty = rs.getInt("item_qty");
        }

        return qty;
    }

    // Edit the name of an item
    public static void updateProductName(String nameOld, String nameNew) throws SQLException {
        String updateStatement = "  UPDATE item_db.inventory\n" + " SET item_name= '" +
                nameNew + "'\n" + " WHERE item_name = '" + nameOld + "';";

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
                "   WHERE item_name = '" + productName + "';";
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
