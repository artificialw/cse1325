package store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

//Besides the newly added code needed for Assignment 11, the rest of the code was made by Professor Rice

public class Store {
    public Store(String name) {
        this.name = name;
    }
     public Store(BufferedReader br) throws IOException {
        this.name = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            customers.add(new Customer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            options.add(new Option(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            computers.add(new Computer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            orders.add(new Order(br));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        
        save(bw, customers);
        save(bw, options);
        save(bw, computers);
        save(bw, orders);
    }
    
    private <T extends Saveable> void save(BufferedWriter bw, Set<T> set) throws IOException {
        bw.write("" + set.size() + '\n');
        for (var item : set) {
             item.save(bw);
        }
    }
    
   public String name() {
        return this.name;
    }
    
    // ///////////////////////////////////////////////////////////
    // Customers
    
    public void add(Customer customer) {
        customers.add(customer);
    }
    public Object[] customers() {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    
    public void add(Option option) {
        options.add(option);
    }
    public Object[] options() {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    
    public void add(Computer computer) {
        computers.add(computer);
    }
    public Object[] computers() {
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    
    public void add(Order order) {
        orders.add(order);
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Fields
    
    private String name;
    private TreeSet<Customer> customers = new TreeSet<>();
    private HashSet<Option> options = new HashSet<>();
    private HashSet<Computer> computers = new HashSet<>();
    private HashSet<Order> orders = new HashSet<>();
}