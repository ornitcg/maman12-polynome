import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Polynome {
    static final int FIRST = 1;
    static final int SECOND = 2;
    static final char YES = 'Y';
    static final char NO = 'N';

    private static final char PLUS = '+';
    private static final char MINUS = '-';

    private ArrayList<Monomial> _polynome;
    private int _polySize;

    //////////////////////////////////////constructors///////////////////////////////////

    /**
     * Constructor - creates a polynome from given arrays of coefficient values and power values
     * @param coefArr - array of coefficient values
     * @param powerArr - array of power values
     */
    public Polynome(ArrayList<Double> coefArr, ArrayList<Integer> powerArr) {
        int size = coefArr.size();
        Double currentCoef;
        Monomial tmpMonomial;
        _polynome = new ArrayList<Monomial>();

        if (coefArr.size() != powerArr.size())
            throw new ArrayIndexOutOfBoundsException("********* ERROR ********* \n Coefficient values and power values are not of same amount. Please try again.");

        for (int ind = 0; ind < size; ind++) {
            if (coefArr.get(ind) != 0) {
                Monomial newMonomial = new Monomial(coefArr.get(ind), powerArr.get(ind));
                _polynome.add(newMonomial);
            }
        }
        _polySize = _polynome.size();
        sortPolynome();
        uniteDuplicates();
        //throws exception of different lengths
    }

    /**
     * Copy constructor - copies a polynomial
     * @param other - the polynomial to copy
     */
    public Polynome(Polynome other) {

        int size = other.get_size();
        _polynome = new ArrayList<Monomial>();

        for (int ind = 0; ind < size; ind++) {
            _polynome.add(new Monomial(other.getMonomialByIndex(ind)));
            _polySize = other.get_size();
        }
        _polySize = size;
    }

////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds up coefficients of monomials which have a similar power value
     */
    private void uniteDuplicates() {
        double coefTmp;
        int ind = 1;
        Monomial prevMonomial, currentMonomial;
        if (_polySize <= 1)
            return;

        while (ind < _polySize) {
            prevMonomial = _polynome.get(ind - 1);
            currentMonomial = _polynome.get(ind);
            if (currentMonomial.get_power() == prevMonomial.get_power()) {
                coefTmp = currentMonomial.get_coefficient();
                prevMonomial.add_coefficient(coefTmp);
                _polynome.remove(ind);
                _polySize--;
            } else ind++;
        }
    }

    /**
     * Gets a monomial , according to a given location (index) in the polynomial
     * @param ind - the location of the wanted monomial
     * @return The monomial at a requested index
     */
    private Monomial getMonomialByIndex(int ind) {
        return _polynome.get(ind);
    }

    /**
     * Arranges the polynomial according to it's monomials power values, from top down
     */
    private void sortPolynome() {
        _polynome.sort(Comparator.reverseOrder());
    }

    /**
     * Sums the calling polynomial to other polynomial
     * Does not change the original polynomial
     * @param other - the polynomial to add
     * @return - The polynomial resulting from the sum calculation
     */
    public Polynome plus(Polynome other) {
        int indThis = 0, indOther = 0;
        Polynome newPolynome = new Polynome(this); // copy of this polynome, an all the changes are performed on it
        Double newCoef;
        Monomial monomThis = null, monomOther;   // to hold  current monomials

        if (other.get_size() == 0) // the other polynome is empty
            return newPolynome;

        if (_polySize == 0 ) // this polynomial is empty
            return new Polynome(other);

        while (indOther < other.get_size()) {

            monomOther = new Monomial(other.getMonomialByIndex(indOther));

            if (indThis >= newPolynome.get_size()){    // this polynome has got to its end, then just add the rest of other
                newPolynome.appendMonomial(monomOther);
                indOther++;
                continue;
            }
            else monomThis = newPolynome.getMonomialByIndex(indThis);  // this polynome has not yest got to its end

            if (monomThis.get_power() < monomOther.get_power()) {
                newPolynome.addMonomial(monomOther, indThis); // add the higher power monomial before the current lower one
                indOther++;
            }
            else if (monomOther.get_power() < monomThis.get_power()) // skip this monomial and check the same power at next iteration
                indThis++;

            else {  // monomials have equal power, just sum the coeficients
                monomThis.set_coefficient(monomThis.get_coefficient() + monomOther.get_coefficient());
                indThis++;
                indOther++;
            }
        }
        return new Polynome(newPolynome);
    }




    /**
     * Changes the sign of the whole polynomial
     */
    private void changeSign() {
        Monomial currenMonomial = null;
        for (int ind = 0; ind< _polySize; ind++){
            currenMonomial = getMonomialByIndex(ind);
            currenMonomial.changeSign();
        }
    }


    /**
     * Appends a monomial to the calling polynomial
     * @param newMonomial - The monomial to append
     */
    private void appendMonomial(Monomial newMonomial) {
        _polynome.add(newMonomial);
        _polySize++;
    }




    // adds a monomial to polynomial, at a specific location (index)

    /**
     * Adds a monomial at a requested index of the polynomial order
     * @param newMonomial - The new monomial to add
     * @param ind - The index of the new monomial, inside the polynomial
     */
    private void addMonomial(Monomial newMonomial, int ind) {
        _polynome.add(ind, newMonomial);
        _polySize++;
    }


    /**
     * Calculates the subtraction between calling polynomial and other
     * Does not change the original polynomial
     * @param other - the polynomial to subtract
     * @return The polynomial resulting from the subtraction operation
     */
    public Polynome minus(Polynome other) {
        Polynome tmpOther = new Polynome(other);
        Polynome sumPolynom = new Polynome(this);
        tmpOther.changeSign();
        return sumPolynom.plus(tmpOther);
    }


    // calclates the derivative of the calling polynomial

    /**
     * Calculates the derivative of the calling polynomial
     * Does not change the original polynomial
     * @return - The derivative of the calling polynomial
     */
    public Polynome derivative() {
        Polynome derivedPolynomial = new Polynome(this); // make a copy of the current polynomial and make the changes on it
        Monomial currentMonomial = null;
        Double currentCoef;
        Integer currentPower;
        int ind = 0 , size = derivedPolynomial.get_size();

        while (ind < size) {
            currentMonomial = derivedPolynomial.getMonomialByIndex(ind); // copy the raw monomial at index
            currentCoef = currentMonomial.get_coefficient();
            currentPower = currentMonomial.get_power();
            if (currentPower == 0) {
                derivedPolynomial.removeMonomial(ind);
                size--;
            }
            else {
                currentMonomial.set_coefficient(currentCoef * currentPower);
                currentMonomial.set_power(currentPower - 1);
                ind++;
            }
        }
        return derivedPolynomial; // already new
    }




    /**
     * Deletes a monomial from the polynimial at a requested index
     * @param ind - the location of the monomial to remove
     */
    private void removeMonomial(int ind) {
        _polynome.remove(ind);
        _polySize--;
    }


    /**
     * Builds a string that represents the polynomial
     * @return - The polynomial string
     */
    public String toString() {
        Monomial tmpMonomial = null;  // to hold the current monomial
        String polyString = "";  // To build on it , the polynomial string
        String monomialStr = "";
        if (_polySize == 0)
            polyString = "0";

        for (int ind = 0; ind < _polySize; ind++) {
            tmpMonomial = _polynome.get(ind);  // get the current monomial
            monomialStr = tmpMonomial.toString(); // get the monomial string

            if (!(monomialStr.isEmpty())) {
                if ((polyString.isEmpty())) {
                    if (tmpMonomial.get_coefficient() < 0)
                        polyString = "-";
                    polyString = polyString + monomialStr;
                } else {
                    if (tmpMonomial.get_coefficient() < 0)
                        polyString = polyString + " - " + monomialStr;
                    else
                        polyString = polyString + " + " + monomialStr;

                }
            }
        }
        return polyString;
    }



    /**
     * Finds if the two polynimials are fully equal
     * @param other - The polynomial to compare to
     * @return - true- if polynomials are similar and false, otherwise
     */
    public Boolean equals(Polynome other) {
        if (_polySize != other.get_size())
            return false;

        for (int ind = 0; ind < _polySize; ind++) {
            if (!(this.getMonomialByIndex(ind).equals(other.getMonomialByIndex(ind))))
                return false;
        }
        return true;
    }





    /**
     * Prints out the main menu for the user
     */
    public static void showPolynomeMenu() {

        System.out.println("\nMAIN MENU");
        System.out.println("-----------");

        System.out.println("(1) Insert new polynomials");
        System.out.println("(2) Print Polynomials");
        System.out.println("(3) Sum the two polynomials");
        System.out.println("(4) Subtract polynomial#2 from polynomial#1");
        System.out.println("(5) Subtract polynomial#1 from polynomial#2");
        System.out.println("(6) Derive polynomial#1");
        System.out.println("(7) Derive polynomial#2");
        System.out.println("(8) compare polynomials");
        System.out.println("(9) Quit\n");

    }


    /**
     * Gets all the coefficient and power values for a certain polynomial
     * @param sc - Scans the values from user
     * @param coefArr - The array to fill in with coefficient values
     * @param powerArr - The array to fill in with power values
     * @param polynomeNumber - Serial number of the polynome to fill in (values 1 or 2)
     */
    public static void getPolynomeValues(Scanner sc, ArrayList<Double> coefArr, ArrayList<Integer> powerArr, int polynomeNumber) {

        if (coefArr != null)
            coefArr.clear();
        if (powerArr != null)
            powerArr.clear();

        System.out.println("Lets fill polynomial #" + polynomeNumber + ": ");
        getCoefArr(sc, coefArr);
        System.out.println("Now please insert a set of integer-type values to be used as powers in your polynomial");
        getPowerArr(sc, powerArr);
    }



    /**
     * Gets a list of numbers from user and assignes
     * them as a set of coefficients for a polynomial
     * @param sc - Scans the user input
     * @param coefArr - The array , to fill in with the coefficient values
     */
    private static void getCoefArr(Scanner sc, ArrayList<Double> coefArr) {
        double coefVal;
        String valuesLine;
        System.out.println("Please add your coefficient value(s) in one line, saparated with spaces");

        valuesLine = sc.nextLine();
        Scanner valuesSc = new Scanner(valuesLine);

        while (valuesSc.hasNextDouble()) {

            coefVal = valuesSc.nextDouble();
            try {
                coefArr.add(coefVal);
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid. please try again.");
                throw new InputMismatchException();
            }
        }
        valuesSc.close();
    }
    // Gets a list of numbers from user and assignes them as a set of powers for a polynomial
    private static void getPowerArr(Scanner sc, ArrayList<Integer> powerArr) {
        int powerVal;
        String valuesLine;
        System.out.println("Please add your power value(s) in one line, saparated with spaces");

        valuesLine = sc.nextLine();
        Scanner valuesSc = new Scanner(valuesLine);

        while (valuesSc.hasNextInt()) {

            powerVal = valuesSc.nextInt();
            try {
                powerArr.add(powerVal);
//                System.out.println(powerVal + " is added to powerArr.");
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid. please try again.");
                throw new InputMismatchException();
            }
        }
        valuesSc.close();
    }




    /**
     * Gets the size of the calling polynomial
     * @return The size of the calling polynomial
     */
    public int get_size() {
        return _polySize;
    }

}
