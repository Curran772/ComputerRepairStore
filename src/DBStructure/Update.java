package DBStructure;

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

    public static Item searchItemByName(String itemName) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_name=" + itemName;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with name " + itemName + ", an error occurred: " + e);
            throw e;
        }
    }

    public static Item searchItemByAmount(Double itemAmount) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_amount=" + itemAmount;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with price " + itemAmount + ", an error occurred: " + e);
            throw e;
        }
    }

    public static Item searchItemByQty(Double itemQty) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM item WHERE item_qty=" + itemQty;
        try {
            // Get ResultSet from dataExecuteQuery and send it
            // to the getItemFromResultSet method and get the item object
            ResultSet rsItem = DBMethods.dataExecuteQuery(selectStmt);
            Item item = getItemFromResultSet(rsItem);
            return item;
        } catch (SQLException e) {
            System.out.println("While searching an item with id " + itemQty + ", an error occurred: " + e);
            throw e;
        }
    }

    // Use ResultSet from DB as parameter and set Employee Object's attributes and
    // return employee object.
    private static Item getItemFromResultSet(ResultSet rs) throws SQLException {
        Item item = null;
        if (rs.next()) {
            item = new Item();
            item.setItemName(rs.getString("item_name"));
            item.setItemPrice(rs.getDouble("item_amount"));
            item.setItemQuantity(rs.getDouble("item_qty"));
        }
        return item;
    }

    // Observer : SELECT item
    public static ObservableList<Item> searchItems() throws SQLException, ClassNotFoundException {

        // Declare the SELECT statement
        String select = "SELECT * FROM inventory_db.item";

        // Execute SELECT statement
        try {
            // ResultSet from dataExecuteQuery method
            ResultSet rsItem = DBMethods.dataExecuteQuery(select);

            // Send the ResultSet to the getItemList method and get the item object
            ObservableList<Item> itemList = getItemList(rsItem);
            return itemList;
        } catch (SQLException e) {
            System.out.println("SQL select operation failed: " + e);
            throw e;
        }
    }

    // Select * from items operation
    private static ObservableList<Item> getItemList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        while (rs.next()) {
            itemList.add(new Item(rs.getString(rs.getString("item_name")),
                    rs.getDouble("item_amount"), rs.getDouble("item_qty")));
        }
        return itemList;
    }

    // Delete item with name
    public static void deleteItemWithName(String itemName) throws SQLException, ClassNotFoundException {
        // Delete statement
        String updateStatement = "   DELETE FROM inventory_db.item\n" +
                "         WHERE item_name =" + itemName + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error occurred while trying to DELETE from item");
            throw e;
        }
    }

    // Insert an item into the item table
    public static void insertItem(String name, Double amount, Double qty) throws SQLException, ClassNotFoundException {
        // Insert statement
        String updateStatement = "INSERT INTO inventory_db.item(item_name, item_amount, item_qty)\n" +
                "VALUES('" + name + "','" + amount + "','" + qty + "');\n";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("SAD, failed to insert item :( ");
            throw e;
        }
    }

    // Edit the name of an item
    public static void updateItemName(String nameOld, String nameNew) throws SQLException, ClassNotFoundException {
        String updateStatement = "  UPDATE inventory_db.item\n" + " SET item_name= '" +
                nameNew + "'\n" + " WHERE item_name = " + nameOld + ";";

        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Failed to update item name");
            throw e;
        }
    }

    // Edit an item price
    public static void updateItemAmount(String itemName, String itemAmount) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE inventory_db.item\n" +
                "       SET item_amount = '" + itemAmount + "'\n" +
                "   WHERE item_name = " + itemName + ";";
        try {
            DBMethods.dataExecuteUpdate(updateStatement);
        } catch (SQLException e) {
            System.out.println("Error attempting to UPDATE operation: " + e);
            throw e;
        }
    }

    // Edit an item quantity
    public static void updateItemQty(String itemName, String itemQty) throws SQLException, ClassNotFoundException {
        // Declare the UPDATE sql statement
        String updateStatement = "   UPDATE inventory_db.item\n" +
                "       SET item_qty = '" + itemQty + "'\n" +
                "   WHERE item_name = " + itemName + ";";
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
