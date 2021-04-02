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

    // copy constructor
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


    private Monomial getMonomialByIndex(int ind) {
        return _polynome.get(ind);
    }

    private Monomial getMonomialByPower(int power) {
        Monomial tmpMonomial;

        for (int ind = 0; ind < _polySize; ind++) {
            tmpMonomial = getMonomialByIndex(ind);
            if (tmpMonomial.get_power() == power)
                return tmpMonomial; // deliberately returned like this, to enable calling function, make changes on this monomial
        }
        return null;
    }


    private void sortPolynome() {
        _polynome.sort(Comparator.reverseOrder());
    }

    private Polynome polynomeOperator(Polynome other, char operation) {
        int indThis = 0, indOther = 0;
        Polynome newPolynome = new Polynome(this); // copy of this polynome
        Double newCoef;
        Monomial monomThis, monomOther;
        System.out.println("other size : " + other.get_size());
        while (indThis < _polySize || indOther < other.get_size()) {
            if (newPolynome.get_size() == 0)
                monomThis = new Monomial(0,0);
            else
                monomThis = newPolynome.getMonomialByIndex(indThis);

            if (other.get_size() == 0)
                monomOther = new Monomial(0,0);
            else
                monomOther = new Monomial(other.getMonomialByIndex(indOther));

            if (monomThis.get_power() < monomOther.get_power()) {
                if (operation == MINUS) {
                    newCoef = (-1) * monomOther.get_coefficient();
                    monomOther.set_coefficient(newCoef);
                }
                newPolynome.addMonomial(monomOther, indThis);
                indOther++;
            } else if (monomOther.get_power() < monomThis.get_power())
                indThis++;
            else {// equal power, just sum coeeficients
                newCoef = monomThis.get_coefficient();

                if (operation == PLUS)
                    monomThis.set_coefficient(newCoef + monomOther.get_coefficient());
                else if (operation == MINUS)
                    monomThis.set_coefficient(newCoef - monomOther.get_coefficient());

                indThis++;
                indOther++;
            }
        }

        return new Polynome(newPolynome);
    }

    private void addMonomial(Monomial newMonomial, int ind) {
        _polynome.add(ind, newMonomial);
        _polySize++;
    }


    public Polynome plus(Polynome other) {
        Polynome newPolynome = polynomeOperator(other, PLUS);
        return newPolynome;
    }


    public Polynome minus(Polynome other) {
        Polynome newPolynome = polynomeOperator(other, MINUS);
        return newPolynome;
    }


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

    private void removeMonomial(int ind) {
        _polynome.remove(ind);
        _polySize--;
    }


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


    public Boolean equals(Polynome other) {
        if (_polySize != other.get_size())
            return false;

        for (int ind = 0; ind < _polySize; ind++) {
            if (!(this.getMonomialByIndex(ind).equals(other.getMonomialByIndex(ind))))
                return false;

        }

        return true;

    }

//

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
//                System.out.println(coefVal + " is added to coefArr.");
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid. please try again.");
                throw new InputMismatchException();
            }
        }
        valuesSc.close();
    }

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


    public int get_size() {
        return _polySize;
    }

}
