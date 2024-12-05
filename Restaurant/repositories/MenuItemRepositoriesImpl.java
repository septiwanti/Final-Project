package Restaurant.repositories;

import Restaurant.config.Database;
import Restaurant.entities.MenuItem;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class MenuItemRepositoriesImpl implements MenuItemRepositories {
    private final Database database;

    public MenuItemRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<MenuItem> fetchMenuItems() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM menu_items";
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getInt("price"));
                menuItem.setQuantity(resultSet.getInt("quantity"));
                menuItem.setId(resultSet.getInt("id"));
                menuItems.add(menuItem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return menuItems;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO menu_items(name, price, quantity) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setInt(2, menuItem.getPrice());
            preparedStatement.setInt(3, menuItem.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item added successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE menu_items SET name = ?, price = ?, quantity = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setInt(2, menuItem.getPrice());
            preparedStatement.setInt(3, menuItem.getQuantity());
            preparedStatement.setInt(4, menuItem.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item updated successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteMenuItem(int id) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM menu_items WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}