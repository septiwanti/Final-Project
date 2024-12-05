package Restaurant.services;

import Restaurant.entities.Customer;
import Restaurant.entities.MenuItem;
import Restaurant.repositories.CustomerRepositoriesImpl;
import Restaurant.repositories.MenuItemRepositoriesImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class OrderServiceImpl implements OrderService {
    private static Scanner input = new Scanner(System.in);
    private Customer temporaryCustomer = new Customer();
    private int dishIndex;
    private int quantity;
    private MenuItemRepositoriesImpl menuItemRepositories;
    private CustomerRepositoriesImpl customerRepositories;

    public OrderServiceImpl(MenuItemRepositoriesImpl menuItemRepositories, CustomerRepositoriesImpl customerRepositories) {
        this.menuItemRepositories = menuItemRepositories;
        this.customerRepositories = customerRepositories;
    }

    @Override
    public void placeOrder() {
        ArrayList<MenuItem> menuItems = menuItemRepositories.fetchMenuItems();
        if (!menuItems.isEmpty()) {
            System.out.print("Select the dish number: ");
            dishIndex = input.nextInt() - 1;
            MenuItem selectedDish = menuItems.get(dishIndex);

            System.out.print("Enter your name: ");
            input.nextLine(); // Consume newline
            temporaryCustomer.setName(input.nextLine());

            System.out.print("Enter payment method: ");
            temporaryCustomer.setPaymentMethod(input.nextLine());

            System.out.print("Enter quantity: ");
            quantity = input.nextInt();
            temporaryCustomer.setOrderDetails(selectedDish.getName() + " x " + quantity);
            temporaryCustomer.setTotalBill(selectedDish.getPrice() * quantity);
        } else {
            System.out.println("No menu items available!");
        }
    }

    @Override
    public void editOrder() {
        if (temporaryCustomer.getName() != null) {
            System.out.println("Current Order: " + temporaryCustomer.getOrderDetails());
            placeOrder(); // Allow the user to place a new order
        } else {
            System.out.println("No order found!");
        }
    }

    @Override
    public void cancelOrder() {
        temporaryCustomer = new Customer(); // Reset the temporary customer
        System.out.println("Order has been cleared");
    }

    @Override
    public void checkout() {
        if (temporaryCustomer.getName() != null) {
            System.out.println("Total Bill: " + temporaryCustomer.getTotalBill());
            System.out.print("Enter your feedback: ");
            input.nextLine(); // Consume newline
            temporaryCustomer.setFeedback(input.nextLine());
            customerRepositories.saveCustomer(temporaryCustomer);
            subtractMenuItems(quantity);
            cancelOrder(); // Reset after checkout
        } else {
            System.out.println("No order to checkout!");
        }
    }

    private void subtractMenuItems(int orderAmmount){
        MenuItem menuItem = menuItemRepositories.fetchMenuItems().get(dishIndex);
        menuItem.setPrice(menuItem.getQuantity() - orderAmmount);
        menuItemRepositories.updateMenuItem(menuItem);
    }
}