package org.example.dao;

import org.example.models.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private final Connection connection;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

    // Add an inventory item
    public void addInventoryItem(Inventory item) {

            String sql = "INSERT INTO inventory (item_name, quantity, price_per_unit) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, item.getItemName());
                stmt.setInt(2, item.getQuantity());
                stmt.setDouble(3, item.getPricePerUnit());
                stmt.executeUpdate();
            }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Get all inventory items
    public List<Inventory> getAllInventoryItems() {
        List<Inventory> items = new ArrayList<>();

            String sql = "SELECT * FROM inventory";
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String itemName = rs.getString("item_name");
                    int quantity = rs.getInt("quantity");
                    double pricePerUnit = rs.getDouble("price_per_unit");
                    items.add(new Inventory(id, itemName, quantity, pricePerUnit));
                }
            }
        catch (SQLException e) {
            System.out.println(e);
        }
        return items;
    }
}
