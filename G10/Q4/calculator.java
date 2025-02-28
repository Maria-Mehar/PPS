import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("Addition: " + addition(num1, num2));
        System.out.println("Multiplication: " + multiplication(num1, num2));
        System.out.println("Power: " + power(num1, num2));
        System.out.println("Remainder: " + remainder(num1, num2));

        sc.close();
    }

    public static double addition(double a, double b) {
        return a + b;
    }

    public static double multiplication(double a, double b) {
        return a * b;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double remainder(double a, double b) {
        return a % b;
    }
}
