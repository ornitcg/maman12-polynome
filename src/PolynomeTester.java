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
                    sc.nextLine();
                    System.out.println("Please insert values for two polynomials as follows:");
                    //Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.FIRST);
                    fillcoef(coefArr,1);
                    fillpower(powerArr,1);
                    try {
                        pol1 = new Polynome(coefArr, powerArr);
                        System.out.println(pol1.toString());
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        break;
                    }
//                    Polynome.getPolynomeValues(sc, coefArr, powerArr, Polynome.SECOND);
                    coefArr.clear();
                    powerArr.clear();
                    fillcoef(coefArr,2);
                    fillpower(powerArr,2);
                    try {
                        pol2 = new Polynome(coefArr, powerArr);
                        System.out.println(pol2.toString());
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                        break;
                    }
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

                case 3: // sum
                    System.out.println("You chose to sum the two polynomials.");
                    System.out.println("The result is:");
                    pol1.plus(pol2).toString();
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
            System.out.println("\nWhat would you like to do next?");
            Polynome.showPolynomeMenu();
            choice = sc.nextInt();
        }

        //Polynome poly1  = getPolynomValues(sc);
        System.out.println("Lets fill polynome #2:");
        //Polynome poly2  = getPolynomValues(sc);







    } // end main

    public static void fillcoef(ArrayList<Double> coefArr, int number){
        if (number == 1) {
            coefArr.add(3.0);
            coefArr.add(3.9);
            coefArr.add(5.0);
            coefArr.add(0.0);
            coefArr.add(6.5);
        }
        else {
            coefArr.add(3.1);
            coefArr.add(2.9);
            coefArr.add(9.0);
            coefArr.add(8.0);

        }

    }

    public static void fillpower(ArrayList<Integer> powerArr, int number){
        if (number == 1) {
            powerArr.add(1);
            powerArr.add(3);
            powerArr.add(0);
            powerArr.add(4);
            powerArr.add(6);
        }
        else {
            powerArr.add(4);
            powerArr.add(0);
            powerArr.add(2);
            powerArr.add(2);

        }

    }

} // end class
