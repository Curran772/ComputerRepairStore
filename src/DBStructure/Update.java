package DBStructure;

import java.sql.SQLException;

public class Update extends DBMethods {

    // Delete item with name
    public static void deleteProduct(String productName) throws SQLException {
        // Delete statement
        String updateStatement = "   DELETE FROM item_db.inventory\n" +
                "         WHERE item_name ='" + productName + "';";

        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to DELETE from item");
            throw e;
        }
    }

    // Insert an item into the item table
    public static void insertProduct(String name, Double amount, Double qty) throws SQLException {
        // Insert statement
        String updateStatement = "INSERT INTO item_db.inventory(item_name, item_amount, item_qty)\n" +
                "VALUES('" + name + "','" + amount + "','" + qty + "');\n";
        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("SAD, failed to insert item :( ");
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
                "   WHERE item_name = " + productName + ";";
        try {
            dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }



}
