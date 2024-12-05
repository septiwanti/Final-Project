package Restaurant.views;

import Restaurant.entities.Customer;
import Restaurant.entities.MenuItem;
import org.springframework.stereotype.Component;
import Restaurant.services.AdminServicesImpl;
import Restaurant.services.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class TerminalViews implements Menus {
    private static Scanner input = new Scanner(System.in);
    private AdminServicesImpl adminServices;
    private OrderServiceImpl orderService;

    public TerminalViews(AdminServicesImpl adminServices, OrderServiceImpl orderService) {
        this.adminServices = adminServices;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        loginMenu();
    }

    private void loginMenu() {
        while (true) {
            System.out.println("WELCOME TO THE RESTAURANT");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void customerMenu() {
        while (true) {
            System.out.println("WELCOME TO THE RESTAURANT ORDERING SYSTEM");
            System.out.println("1. Order Food");
            System.out.println("2. Edit Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. Checkout");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    viewMenuItems();
                    orderService.placeOrder();
                    break;
                case 2:
                    viewMenuItems();
                    orderService.editOrder();
                    break;
                case 3:
                    orderService.cancelOrder();
                    break;
                case 4:
                    orderService.checkout();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void viewMenuItems() {
        ArrayList<MenuItem> menuItems = adminServices.fetchMenuItems();
        if (!menuItems.isEmpty()) {
            System.out.println("No.\tName\tPrice\tQuantity");
            int number = 1;
            for (MenuItem item : menuItems) {
                System.out.println(number + ".\t" + item.getName() + "\t" + item.getPrice() + "\t" + item.getQuantity());
                number++;
            }
        } else {
            System.out.println("No menu items available!");
        }
    }

    private void viewCustomerOrders() {
        ArrayList<Customer> menuItems = adminServices.fetchCustomers();
        if (!menuItems.isEmpty()) {
            System.out.println("No.\tName\ttotal bill\tPayment method\tfeedback");
            int number = 1;
            for (Customer item : menuItems) {
                System.out.println(number + ".\t" + item.getName() + "\t" + item.getTotalBill() + "\t" + item.getPaymentMethod() + "\t" + item.getFeedback());
                number++;
            }
        } else {
            System.out.println("No menu items available!");
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("WELCOME TO THE ADMIN MENU");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Edit Menu Item");
            System.out.println("3. Delete Menu Item");
            System.out.println("4. View Menu Items");
            System.out.println("5. View Menu Customer Orders");

            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    adminServices.addMenuItem();
                    break;
                case 2:
                    viewMenuItems();
                    adminServices.editMenuItem();
                    break;
                case 3:
                    viewMenuItems();
                    adminServices.deleteMenuItem();
                    break;
                case 4:
                    viewMenuItems();
                    break;
                case 5:
                    viewCustomerOrders();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}