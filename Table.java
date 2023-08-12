import java.util.Scanner;


public class Table{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number:");

        int num= scan.nextInt();
        for (int i = 1; i<= 10; i++) {
            System.out.println(num + " * " + i + " = " + num*i);
        }
    }
}