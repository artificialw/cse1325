import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        if (email == null || !email.contains("@") || email.indexOf(".") < email.indexOf("@")) {
            throw new IllegalArgumentException("Not a valid email address");
        }
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

import java.io.IOException;
        if (!(o instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) o;
        return name.equals(other.name) && email.equals(other.email);
    }
}

class Option {
    private String name;
    private long cost;

    public Option(String name, long cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost can't be negative");
        }
        this.name = name;
        this.cost = cost;
    }

    public long cost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", cost / 100.0) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Option)) {
            return false;
        }

        Option other = (Option) o;
        return name.equals(other.name) && cost == other.cost;
    }
}

class Computer {
    private String name;
    private String model;
    private List<Option> options = new ArrayList<>();

    public Computer(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public long cost() {
        long totalCost = 0;
        for (Option option : options) {
            totalCost += option.cost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(model).append(")\n");
        for (Option option : options) {
            sb.append("   ").append(option.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if(!(o instanceof Computer)) {
            return false;
        }

        Computer other = (Computer) o;
        return this.toString().equals(other.toString());
    }
}

class Order {
    private static long nextOrderNumber = 0;
    private long orderNumber;
    private Customer customer;
    private List<Computer> computers = new ArrayList<>();

    public Order(Customer customer) {
        this.orderNumber = nextOrderNumber++;
        this.customer = customer;
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order").append(orderNumber).append(" for ").append(customer.toString()).append("\n");
        for (Computer computer : this.computers) {
            sb.append(computer.toString()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Order)) {
            return false;
        }

        Order other = (Order) o;
        return this.customer.equals(other.customer) && this.computers.equals(other.computers);
    }
}

class Store {
    public Store(String name) {
        this.name = name;
    }

    public void load(BufferedReader br) throws IOException {
        name = br.readLine();

        int numCustomers = Integer.parseInt(br.readLin());
        for(int i = 0; i < numCustomers; i++) {
            String name = br.readLine();
            String email = br.readLine();
            customers.add(new Customer(name, email));
        }

        int numOptions = Integer.parseInt(br.readLine());
        for(int i = 0; i < numOptions; i++) {
            String name = br.readLine();
            long cost = long.parseLong(br.readLine());
            options.add(new Option(name, cost));
        }

        int numComputers = Integer.parseInt(br.readLine());
        for(int i = 0; i < numComputers; i++) {
            String name = br.readLine();
            String model = br.readLine();
            int numComputerOptions = Integer.parseInt(br.readLine());
            Computer computer = new Computer(name, model);
            for(int j = 0; j < numComputerOptions; j++) {
                String optionName = br.readLine();
                Option option = findOption(optionName);
                if(option != null) {
                    computer.addOption(option);
                }
            }
            computers.add(computer);
        }

        int numOrders = Integer.parseInt(br.readLine());
        for(int i = 0; i < numOrders; i++) {
            String customerName = br.readLine();
            String customerEmail = br.readLine();
            int numOrderComputers = Integer.parseInt(br.readLine());
            Order order = new Order(findCustomer(customerName, customerEmail));
            for(int j = 0; j < numOrderComputers; j++) {
                String computerName = br.readLine();
                String computerModel = br.readLine();
                int numComputerOptions = Integer.parseInt(br.readLine());
                Computer computer = findComputer(computerName, computerModel);
                if(computer == null) {
                    computer = new Computer(computerName, computerModel);
                    computers.add(computer);
                }

                for(int k = 0; k < numComputerOptions; k++) {
                    String optionName = br.readLine();
                    Option option = findOption(optionName);
                    if(option != null) {
                        computer.addOption(option);
                    }
                }
                order.addComputer(computer);
            }
            orders.add(order);
        }
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');

        bw.write(customers.size() + '\n');
        for(Customer customer : customers) {
            bw.write(customer.getName() + '\n');
            bw.write(customer.getEmail() + '\n');
        }

        bw.write(options.size() + '\n');
        for(Option option : options) {
            bw.write(option.getName() + '\n');
            bw.write(option.cost() + '\n');
        }

        bw.write(computers.size() + '\n');
        for(Computer computer : computers) {
            bw.write(computer.getName() + '\n');
            bw.write(computer.getModel() + '\n');
            bw.write(computer.options().size() + '\n');
            for(Option option : computer.options()) {
                bw.write(option.getName() + '\n');
            }
        }

        bw.write(orders.size() + '\n');
        for(Order order : orders) {
            bw.write(order.getCustomer().getName() + '\n');
            bw.write(order.getCustomer().getEmail() + '\n');
            bw.write(order.computers.size() + '\n');
            for(Computer computer : order.computers()) {
                bw.write(computer.getName() + '\n');
                bw.write(computer.getModel() + '\n');
                bw.write(computer.options().size() + '\n');
                for(Option option : computer.options()) {
                    bw.write(option.getName() + '\n');
                }
            }
        }
    }

    public String name() {
        return this.name;
    }
    
    public void add(Customer customer) {
        if(!customers.contains(customer)) customers.add(customer);
    }
    public Object[] customers() {
        return this.customers.toArray();
    }
    
    public void add(Option option) {
        if(!options.contains(option)) options.add(option);
    }
    public Object[] options() {
        return this.options.toArray();
    }
    
    public void add(Computer computer) {
        if(!computers.contains(computer)) computers.add(computer);
    }
    public Object[] computers() {
        return this.computers.toArray();
    }
    
    public void add(Order order) {
        if(!orders.contains(order)) orders.add(order);
    }
    public Object[] orders() {
        return this.orders.toArray();
    }
    
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();
    private ArrayList<Computer> computers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
}

