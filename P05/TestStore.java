import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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

public class TestStore {
    public static void main(String[] args) {
        String failFormat = "FAIL %s\n\n#### Expected\n\n%s\n\n#### Actual\n\n%s\n\n";
    
        int result = 0;  // non-zero is a failure
        int vector = 1;  // single bit representing the test
    
        Store store = new Store("Tester Joes");
        
        Object[] customers = new Object[]{
            new Customer("Prof Rice", "george.rice@uta.edu")
        };
        store.add(new Customer("Prof Rice", "george.rice@uta.edu"));
        if(!deepEqualsCustomer(customers, store.customers())) {
            System.err.printf(failFormat, "Customer",
                Arrays.toString(customers),
                Arrays.toString(store.customers())
            );
            result |= vector;
        }
        vector <<= 1;

        Object[] options = new Object[]{
            new Option("Mainboard", 20000)
        };
        store.add(new Option("Mainboard", 20000));
        if(!deepEqualsOption(options, store.options())) {
            System.err.printf(failFormat, "Option",
                Arrays.toString(options),
                Arrays.toString(store.options())
            );
            result |= vector;
        }
        vector <<= 1;

        Object[] computers = new Object[]{
            new Computer("SuperCalc", "1Z200XL")
        };
        Computer c = (Computer) computers[0];
        c.addOption((Option) options[0]);

        c = new Computer("SuperCalc", "1Z200XL");
        c.addOption((Option) options[0]);       
        store.add(c);
        if(!deepEqualsComputer(computers, store.computers())) {
            System.err.printf(failFormat, "Computer",
                Arrays.toString(computers),
                Arrays.toString(store.computers())
            );
            result |= vector;
        }
        vector <<= 1;
        
        Object[] orders = new Object[]{
            new Order((Customer) customers[0])
        };
        Order o1 = (Order) orders[0];
        o1.addComputer((Computer) computers[0]);
        
        Order o2 = new Order((Customer) customers[0]);
        o2.addComputer((Computer) computers[0]);                       
        store.add(o2);
                
        if(!deepEqualsOrder(orders, store.orders())) {
            System.err.printf(failFormat, "Order",
                Arrays.toString(orders),
                Arrays.toString(store.orders())
            );
            result |= vector;
        }
        vector <<= 1;

        if (result != 0) {
            System.err.println("FAIL: Error code " + result);
            System.exit(result);
        }
    }

    private static boolean deepEqualsCustomer(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Customer) a[i]).equals((Customer) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean deepEqualsOption(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Option) a[i]).equals((Option) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean deepEqualsComputer(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Computer) a[i]).equals((Computer) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean deepEqualsOrder(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Order) a[i]).equals((Order) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}