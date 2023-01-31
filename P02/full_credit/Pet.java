public class Pet {
    private String name;
    private Type type;
    private double age;

    public Pet(String name, Type type, double age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return name + " is a " + type + " age " + age;
    }
}

public class MyPets {
    public static void main(String args[]) {
        Pet[] pets = {
            new Pet("Lola", Pet.Type.Horse, 6),
            new Pet("Shadow", Pet.Type.Fish, 2),
            new Pet("Chloe", Pet.Type.Turtle, 52),
            new Pet("Oreo", Pet.Type.Wolf, 4)
        };

        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

}

