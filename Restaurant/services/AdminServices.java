package Restaurant.services;

import Restaurant.entities.Customer;
import Restaurant.entities.MenuItem;

import java.util.ArrayList;

public interface AdminServices {
    void addMenuItem();
    void editMenuItem();
    void deleteMenuItem();
    ArrayList<MenuItem> fetchMenuItems();
    ArrayList<Customer> fetchCustomers();
}
