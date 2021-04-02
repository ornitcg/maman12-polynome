import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Polynome {
    static final int FIRST = 1;
    static final int SECOND = 2;
    static final char YES = 'Y';
    static final char NO = 'N';

    private static final char PLUS = '+' ;
    private static final char MINUS = '-';

    private ArrayList<Monomial> _polynome;
    private int _polySize;


    public Polynome(ArrayList<Double> coefArr, ArrayList<Integer> powerArr){
        int size = coefArr.size();
        Double currentCoef;
        Monomial tmpMonomial;
        _polynome = new ArrayList<Monomial>();

        if (coefArr.size() != powerArr.size())
            throw new ArrayIndexOutOfBoundsException("ERROR: Coefficient values and power values are not of same amount. Please try again.");

        for (int ind = 0; ind < size ; ind++){
                if (coefArr.get(ind) != 0) {
                    Monomial newMonomial = new Monomial(coefArr.get(ind), powerArr.get(ind));
//                  System.out.println("[DEBUG] monomial #" + ind + " is: "+ newMonomial.toString());
                    _polynome.add(newMonomial);
                }
        }
        _polySize = _polynome.size();
        System.out.println("The not sorted polynome is: " +this.toString());

        sortPolynome();
        System.out.println("The sorted polynome is: " +this.toString());
        uniteDuplicates();
        //throws exception of different lengths
    }

    private void uniteDuplicates() {
        double coefTmp;
        int ind = 1;
        Monomial prevMonomial, currentMonomial;
        if (_polySize <= 1)
            return;

        while (ind < _polySize){
            prevMonomial = _polynome.get(ind-1);
            currentMonomial = _polynome.get(ind);
            if (currentMonomial.get_power() == prevMonomial.get_power()){
                coefTmp = currentMonomial.get_coefficient();
                prevMonomial.add_coefficient(coefTmp);
                _polynome.remove(ind);
                _polySize--;
            }
            else ind++;
        }
    }

    // copy constructor
    public Polynome(Polynome other){
        int size = other.get_size();
        for (int ind = 0; ind < size ;  ind++){
            _polynome.add(new Monomial(other.getMonomialByIndex(ind)));
        }
    }


    private int get_size() {
        return _polySize;
    }


    private Monomial getMonomialByIndex(int ind) {
        return _polynome.get(ind);
    }

    private Monomial getMonomialByPower(int power) {
        Monomial tmpMonomial;

        for( int ind = 0 ; ind < _polySize ; ind++){
            tmpMonomial = getMonomialByIndex(ind);
            if (tmpMonomial.get_power() == power )
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
        while (indThis < _polySize || indOther < other.get_size()) {
            monomThis = newPolynome.getMonomialByIndex(indThis);
            monomOther = other.getMonomialByIndex(indOther);

            if (monomThis.get_power() < monomOther.get_power())
                indThis++;
            else if (monomOther.get_power() < monomThis.get_power())
                indOther++;
            else {
                newCoef = monomThis.get_coefficient();

                if (operation == PLUS)
                    monomThis.set_coefficient(newCoef + monomOther.get_coefficient());
                else if (operation == MINUS)
                    monomThis.set_coefficient(newCoef - monomOther.get_coefficient());

            }
            monomThis = newPolynome.getMonomialByIndex(indThis);
            monomOther = other.getMonomialByIndex(indOther);

    }

        return newPolynome;  // already new
    }




    public Polynome plus(Polynome other){
        Polynome newPolynome = polynomeOperator(other, PLUS);
        return newPolynome;
    }




    public Polynome minus(Polynome other){
        Polynome newPolynome = polynomeOperator(other, MINUS);
        return newPolynome;
    }



    public Polynome derivative(Polynome polynome){
        Polynome derivedPolynome = new Polynome(polynome); // make a copy of the current one and mane the changes on it
        Monomial currentMonome = null;
        Double currentCoef;
        Integer currentPower;

        for(int ind = 0 ; ind < _polySize ; ind++ ){
            currentMonome = derivedPolynome.getMonomialByIndex(ind);
            currentCoef = currentMonome.get_coefficient();
            currentPower = currentMonome.get_power();
            currentMonome.set_coefficient( currentCoef + currentPower );
            currentMonome.set_power(currentPower-1);
        }
        return derivedPolynome; // already new
    }



    public String toString(){
        String polyString = "";
        String monomialStr = "";

        for( int ind = 0 ; ind < _polySize ; ind++ ) {
            monomialStr = _polynome.get(ind).toString();
            if (!(monomialStr.isEmpty())) {
                if ((polyString.isEmpty()))
                    polyString = monomialStr;
                else
                    polyString = polyString + " + " + monomialStr;
            }
        }
        return polyString;
    }


    public Boolean equals(Polynome polynome){
        return null;

    }

//

    public static void showPolynomeMenu() {

        System.out.println("\nMAIN MENU");
        System.out.println("-----------");

        System.out.println("(1) Insert new polynomials");
        System.out.println("(2) Print Polynomials");
        System.out.println("(3) Sum the two polynomials");
        System.out.println("(4) Subtract polynomial#1 from polynomial#2");
        System.out.println("(5) Subtract polynomial#2 from polynomial#1");
        System.out.println("(6) Derive polynomial#1");
        System.out.println("(7) Derive polynomial#2");
        System.out.println("(8) compare polynomials");
        System.out.println("(9) Quit\n");

    }

    public static void getPolynomeValues(Scanner sc, ArrayList<Double> coefArr , ArrayList<Integer> powerArr, int polynomeNumber) {

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
            }
            catch (InputMismatchException e){
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
            }

            catch (InputMismatchException e){
                System.out.println("Input is invalid. please try again.");
                throw new InputMismatchException();
            }
        }
        valuesSc.close();
    }

}
