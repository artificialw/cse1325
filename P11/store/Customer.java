package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

//Besides the newly added code needed for Assignment 11, the rest of the code was made by Professor Rice

public class Customer implements Saveable, Compareable<Customer> {
    public Customer(String name, String email) {
        int atIndex = email.indexOf('@', 0);
        int dotIndex = (atIndex >= 0) ? email.indexOf('@', 0) : -1;
        if(dotIndex < 0) 
            throw new IllegalArgumentException("Invalid email address: " + email);
        this.name = name;
        this.email = email;
    }
    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }
    
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Customer c = (Customer) o;
            return this.name.equals(c.name) && this.email.equals(c.email);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public int compareTo(Customer other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        } else {
            return this.email.compareTo(other.email);
        }
    }

    private String name;
    private String email;
}