package store;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

//Besides the newly added code needed for Assignment 11, the rest of the code was made by Professor Rice

public class Computer implements Saveable{
    public Computer(String name, String model) {
        this.name = name;
        this.model = model;
    }
    public Computer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.model = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            options.add(new Option(br));
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(model + '\n');
        bw.write("" + options.size() + '\n');
        for(Option option : options)
            option.save(bw);
    }
    public void addOption(Option option) {
        options.add(option);
    }
    public long cost() {
        long cost = 0;
        for(Option o : options) cost += o.cost();
        return cost;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " (" + model + "}");
        for(Option o : options) sb.append("\n  " + o);
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Computer c = (Computer) o;
            return this.toString().equals(c.toString());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + options.hashCode();
        return result;
    }

    
    private String name;
    private String model;
    private ArrayList<Option> options = new ArrayList<>();
}