import java.util.Scanner;
import java.util.ArrayList;

class Product {
    private final String name;
    private final double cost;

    public Product(String name, double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("The cost cannot be negative");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double price() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", cost) + ") $" + String.format("%.2f", price());
    }
}

class Taxfree extends Product {
    public Taxfree(String name, double cost) {
        super(name, cost);
    }

    @Override
    public double price() {
        return getCost();
    }
}

class Taxed extends Product {
    private static double salesTaxRate = 0.0;

    public Taxed(String name, double cost) {
        super(name, cost);
    }

    public static void setTaxRate(double taxRate) {
        salesTaxRate = taxRate;
    }

    @Override
    public double price() {
        return getCost() * (1 +salesTaxRate);
    }
}

class Store {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Product> shoppingCart = new ArrayList<>();

    public static void main(String[] args) {
        products.add(new Taxfree("Milk", 2.85));
        products.add(new Taxfree("Bread", 1.99));
        products.add(new Taxfree("Chesse", 0.85));
        products.add(new Taxfree("Eggs", 6.95));
        products.add(new Taxfree("Ice Cream", 4.95));
        products.add(new Taxfree("Poptarts", 3.49));
        products.add(new Taxfree("Donuts", 5.99));

        Taxed.setTaxRate(0.0725);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the store\n");
            for(int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if(product instanceof Taxfree) {
                    System.out.println((i+1) + ") " + product);
                }
            }

            System.out.println("\nCurrent Order\n");
            for(Product product : shoppingCart) {
                System.out.println(product);
            }

            System.out.print("\nBuy which product?\n");
            Scanner scanner = new Scanner(System.in);
            int itemNumber = scanner.nextInt();
            if(itemNumber < 0) {
                break;
            }

            try {
                shoppingCart.add(products.get(itemNumber - 1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid item number.");
            }
        }
    }
}
