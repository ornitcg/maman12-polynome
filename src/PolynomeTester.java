import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PolynomeTester {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer choice = null;
        ArrayList<Double> coefArr = new ArrayList<Double>();
        ArrayList<Integer> powerArr = new ArrayList<Integer>();
        Polynome pol1 = null;
        Polynome pol2 = null;

        System.out.println("Welcome to the polynomial zone! what would you like to do?");
        Polynome.showPolynomeMenu();
        choice = sc.nextInt();
        while (choice != 9) {
            switch (choice) {
                case 1: // Insert New polynomes
                    pol1 = null;
                    pol2 = null;
                    sc.nextLine();
                    System.out.println("Please insert values for two polynomials as follows:");
                    try {
                        Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.FIRST);
                    }
                    catch (InputMismatchException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    try {
                        pol1 = new Polynome(coefArr, powerArr);
                        System.out.println("Inserted polynomial #1: " +pol1.toString());
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    try {
                        Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.SECOND);
                    }
                    catch (InputMismatchException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                    try {
                        pol2 = new Polynome(coefArr, powerArr);
                        System.out.println("Inserted polynomial #2: " + pol2.toString());
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;

                case 2: //Print Polynomials

                    System.out.println("You chose to print polynomials.");

                    try {
                        System.out.println("Polynomial #1 :" + pol1.toString());
                        System.out.println("-------------------------");
                        System.out.println("Polynomial #2 :" + pol2.toString());
                    }
                    catch (Exception e){
                        System.out.println("There are no polynomial to print. Please Insert polynomials first.");
                    }
                    break;

                case 3: // sum
                    System.out.println("You chose to sum the two polynomials.");
                    try {
                        System.out.println("Polynomial #1: " + pol1.toString());
                        System.out.println("Polynomial #2: " + pol2.toString());
                        System.out.println("Polynomial#1 + Polynomial#2: " +pol1.plus(pol2).toString());
                    }
                    catch (NullPointerException e){
                        System.out.println("There are no polynomial to calculate their sum. Please Insert polynomials first.");
                    }
                    break;
                case 4:// polynomial1 - polynomial2
                    try {
                        System.out.println("You chose to Subtract polynomial#1 from polynomial#2.");
                        System.out.println("Polynomial #1: " + pol1.toString());
                        System.out.println("Polynomial #2: " + pol2.toString());
                        System.out.println("\nPolynomial#2 - Polynomial#1: " + pol2.minus(pol1).toString());

                    }
                    catch (NullPointerException e) {
                        System.out.println("There are no polynomial to subtract. Please Insert polynomials first.");
                    }

                    break;
                case 5: //polynomial2 - polynomial1
                    System.out.println("You chose to Subtract polynomial#2 from polynomial#1.");
                    try {
                        System.out.println("Polynomial #1: " + pol1.toString());
                        System.out.println("Polynomial #2: " + pol2.toString());
                        System.out.println("\nPolynomial#1 - Polynomial#2: " + pol1.minus(pol2).toString());

                    }
                    catch (NullPointerException e){
                        System.out.println("There are no polynomial to subtract. Please Insert polynomials first.");
                    }
                    break;
                case 6://Derive polynomial#1
                    System.out.println("You chose to derive polynomial#1.");
                    try {
                        System.out.println("The original polynomial is: " + pol1.toString());

                        System.out.println("Polynomial#1 derivative: " + (pol1.derivative()).toString());
                    }
                    catch(NullPointerException e){
                        System.out.println("There is no polynomial to derive. Please Insert polynomials first.");
                    }
                    break;
                case 7://Derive polynomial#2
                    System.out.println("You chose to derive polynomial#2.");
                    try {
                        System.out.println("The original polynomial is: " + pol2.toString());

                        System.out.println("Polynomial#2 derivative: " + (pol2.derivative()).toString());
                    }
                    catch (NullPointerException e){
                        System.out.println("There is no polynomial to derive. Please Insert polynomials first.");
                    }
                    break;
                case 8://compare polynomials
                    try {
                        System.out.println("You chose to compare the two polynomials.");
                        if (pol1.equals(pol2))
                            System.out.println("The polynomials ARE equal.");
                        else System.out.println("The polynomials are NOT equal.");
                    }
                    catch (Exception e){
                        System.out.println("There are no polynomial to compare. Please Insert polynomials first.");
                    }

                    break;
                case 9://quit
                    System.out.println("You chose to quit. Bye Bye!");

                    break;
                default:
                    System.out.println("Choice is not in menu. please try again!");
                    break;
            } //  end switch
            if (choice != 9) {
                System.out.println("\nWhat would you like to do next?");
                Polynome.showPolynomeMenu();
                choice = sc.nextInt();
            }
        }

    } // end main

    private static void resetPolynomial(Polynome pol) {
        pol = null;
    }


} // end class
