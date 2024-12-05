package Restaurant.repositories;

import Restaurant.entities.Customer;

import java.util.ArrayList;

public interface CustomerRepositories {
    ArrayList<Customer> fetchCustomers();
    void saveCustomer(Customer customer);
}
