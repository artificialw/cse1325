package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

//Besides the newly added code needed for Assignment 10, the rest of the code was made by Professor Rice

public class Order {
    public Order(Customer customer) {
        this.customer = customer;
        this.orderNumber = nextOrderNumber++;
    }

    public Order(BufferedReader br) throws IOException {
        this.orderNumber = Long.parseLong(br.readLine());
        if (nextOrderNumber <= orderNumber) nextOrderNumber = orderNumber + 1;
        this.customer = new Customer(br);
        int numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            computers.add(new Computer(br));
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(Long.toString(orderNumber) + '\n');
        this.customer.save(bw);
        bw.write("" + computers.size() + '\n');
        for (Computer computer : computers)
            computer.save(bw);
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    public double cost() {
        double totalCost = 0;
        for (Computer computer : computers) {
            totalCost += computer.getCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order " + orderNumber + " for " + customer + "\n");
        for (Computer c : computers) {
            sb.append(c.toString() + "\n");
        }
        sb.append("Total cost: $" + cost());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o) return true;
            if (this.getClass() != o.getClass()) return false;
            Order c = (Order) o;
            if (!this.customer.equals(c.customer)) return false;
            if (this.computers.size() != c.computers.size()) return false;
            for (int i = 0; i < this.computers.size(); ++i)
                if (!this.computers.get(i).equals(c.computers.get(i))) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Customer customer;
    private long orderNumber;
    private ArrayList<Computer> computers = new ArrayList<>();

    private static long nextOrderNumber = 0;
}
