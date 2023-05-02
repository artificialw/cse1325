package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

//Besides the newly added needed for P13, the rest of the code was written by Professor Rice

public class Option implements Saveable {
    public Option(String name, long cost) {
        if(cost < 0) throw new IllegalArgumentException("Negative cost for " + name + ": " + cost);
        this.name = name;
        this.cost = cost;
        this.filename = filename;
        this.deprecated = false;
    }
    public Option(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.cost = Long.parseLong(br.readLine());
        this.filename = br.readLine();
        this.deprecated = false;
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(Long.toString(cost) + '\n');
        bw.write(filename + '\n');
    }
    
    public long cost() {
        return this.cost;
    }
    public boolean isDeprecated {
        return this.deprecated;
    }
    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }
    public String getFilename() {
        return this.filename;
    }
    @Override
    public String toString() {
        long cents = cost % 100;
        String deprecatedStr = deprecated ? " (deprecated)" : "";
        return name + " ($" + cost/100 + "." +  ((cents < 10) ? "0" : "") + cents + ")";
    }
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Option p = (Option) o;
            return this.name.equals(p.name) && (this.cost == p.cost);
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }

    protected String name;
    protected long cost;
    protected String filename;
    protected boolean deprecated;
}