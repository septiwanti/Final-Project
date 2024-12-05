package Restaurant.services;

import Restaurant.entities.Customer;
import Restaurant.entities.MenuItem;
import Restaurant.repositories.CustomerRepositoriesImpl;
import Restaurant.repositories.MenuItemRepositoriesImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class AdminServicesImpl implements AdminServices {
    private static Scanner input = new Scanner(System.in);
    private MenuItemRepositoriesImpl menuItemRepositories;
    private CustomerRepositoriesImpl customerRepositories;

    public AdminServicesImpl(CustomerRepositoriesImpl customerRepositories,MenuItemRepositoriesImpl menuItemRepositories) {
        this.customerRepositories = customerRepositories;
        this.menuItemRepositories = menuItemRepositories;
    }

    @Override
    public void addMenuItem() {
        MenuItem menuItem = new MenuItem();
        System.out.println("if the next process wont run, press enter to continue...");
        input.nextLine();
        System.out.print("Enter dish name: ");
        menuItem.setName(input.nextLine());
        System.out.print("Enter price: ");
        menuItem.setPrice(input.nextInt());
        System.out.print("Enter quantity: ");
        menuItem.setQuantity(input.nextInt());
        menuItemRepositories.addMenuItem(menuItem);
    }

    @Override
    public void editMenuItem() {
        System.out.print("Enter the index of the menu item to edit: ");
        int index = input.nextInt() -1;
        MenuItem menuItem = fetchMenuItems().get(index);
        System.out.print("Enter new dish name: ");
        menuItem.setName(input.next());
        System.out.print("Enter new price: ");
        menuItem.setPrice(input.nextInt());
        System.out.print("Enter new quantity: ");
        menuItem.setQuantity(input.nextInt());
        menuItemRepositories.updateMenuItem(menuItem);
    }

    @Override
    public void deleteMenuItem() {
        System.out.print("Enter the Index of the menu item to delete: ");
        int index = input.nextInt() -1;
        MenuItem tmp = menuItemRepositories.fetchMenuItems().get(index);
        menuItemRepositories.deleteMenuItem(tmp.getId());
    }

    @Override
    public ArrayList<MenuItem> fetchMenuItems() {
        return menuItemRepositories.fetchMenuItems();
    }

    @Override
    public ArrayList<Customer> fetchCustomers() {
        return customerRepositories.fetchCustomers();
    }
}