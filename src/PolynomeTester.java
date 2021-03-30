import java.util.ArrayList;
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
                    System.out.println("Please insert values for two polynomials as follows:");
                    Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.FIRST);
                    pol1 = new Polynome(coefArr, powerArr);
                    Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.SECOND);
                    pol2 = new Polynome(coefArr, powerArr);
                    break;

                case 2: //Print Polynomials
                    if (pol1 == null || pol2 == null) {
                        System.out.println("One of your polynomials may be empty, please add polynomials to play with");
                        break;
                    }
                    System.out.println("Polynomial #1 :");
                    System.out.println(pol1.toString());
                    System.out.println("-------------------------");
                    System.out.println("Polynomial #2 :");
                    System.out.println(pol2.toString());
                    break;

                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:

                    break;

            } //  end switch
            System.out.println("\nWhat would you like ot do next?");
            Polynome.showPolynomeMenu();
            choice = sc.nextInt();
        }

        //Polynome poly1  = getPolynomValues(sc);
        System.out.println("Lets fill polynome #2:");
        //Polynome poly2  = getPolynomValues(sc);







    } // end main


} // end class
