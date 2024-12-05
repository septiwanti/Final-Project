package Restaurant.repositories;

import Restaurant.entities.MenuItem;

import java.util.ArrayList;

public interface MenuItemRepositories {
    ArrayList<MenuItem> fetchMenuItems();
    void addMenuItem(MenuItem menuItem);
    void updateMenuItem(MenuItem menuItem);
    void deleteMenuItem(int id);
}
