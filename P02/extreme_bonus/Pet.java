import java.util.ArrayList;

public class Pet {
    private String name;
    private Type type;
    private double age;
    public static final double humanLifespan = 80;

    public Pet(String name, Type type, double age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public double humanAge() {
        return humanLifespan * age / type.lifespan();
    }
    
    @Override
    public String toString() {
        return name + " is a " + type + " age " + age;
    }
}

public class MyPets {
    public static void main(String args[]) {
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(new Pet("Lola", Pet.Type.Horse, 6));
        pets.add(new Pet("Shadow", Pet.Type.Fish, 2));
        pets.add(new Pet("Chloe", Pet.Type.Turtle, 52));
        pets.add(new Pet("Oreo", Pet.Type.Wolf, 4));
    
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }
}
    
    