import java.util.Scanner;

public class Hello{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String word;
        System.out.print("What is your name? ");
        word = in.nextLine();
        System.out.println("Hello, " + word + "!");
    }
}
