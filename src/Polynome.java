import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Polynome {
    static final int FIRST = 1;
    static final int SECOND = 2;
    private static final char PLUS = '+' ;
    private static final char MINUS = '-';

    private ArrayList<Monomial> _polynome;
    private int _polySize;


    public Polynome(ArrayList<Double> coefArr, ArrayList<Integer> powerArr){
        int size = coefArr.size();
        Double currentCoef;
        _polySize = size;

        if (coefArr.size() != powerArr.size())
            throw new ArrayIndexOutOfBoundsException("Coefficient values  array and power values array are not of same length");

        for (int ind =0; ind < size ; ind++){
            try{
                currentCoef = _polynome.get(ind).get_coefficient();
                _polynome.get(ind).set_coefficient(currentCoef + coefArr.get(ind));
            }
            catch (Exception e){
                _polynome.add(new Monomial(coefArr.get(ind),powerArr.get(ind)));
            }
        }
        sortPolynome();
        //throws exception of different lengths
    }

    // copy constructor
    public Polynome(Polynome other){
        int size = other.get_size();
        for (int ind = 0; ind < size ;  ind++){
            _polynome.add(new Monomial(other.getMonomial(ind)));
        }
    }


    private int get_size() {
        return _polySize;
    }


    private Monomial getMonomial(int ind) {
        return _polynome.get(ind);
    }


    private void sortPolynome() {
        Collections.sort(_polynome);
    }

    private Polynome polynomeOperator(Polynome other, char operation){
        int indThis = 0, indOther = 0;
        Polynome newPolynome = new Polynome(this);
        Double newCoef;
        Monomial monom1 =  newPolynome.getMonomial(indThis);
        Monomial monom2 =  other.getMonomial(indOther);

        while (monom1 != null && monom2 != null){
            if (monom1.get_power() < monom2.get_power())
                indThis++;
            else if (monom2.get_power() < monom1.get_power())
                indOther++;
            else{
                newCoef = monom1.get_coefficient();

                if (operation == PLUS)
                    monom1.set_coefficient(newCoef + monom2.get_coefficient());
                else if (operation == MINUS)
                    monom1.set_coefficient(newCoef - monom2.get_coefficient());

            }
            monom1 = newPolynome.getMonomial(indThis);
            monom2 =  other.getMonomial(indOther);
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
            currentMonome = derivedPolynome.getMonomial(ind);
            currentCoef = currentMonome.get_coefficient();
            currentPower = currentMonome.get_power();
            currentMonome.set_coefficient( currentCoef + currentPower );
            currentMonome.set_power(currentPower-1);
        }
        return derivedPolynome; // already new
    }



    public String toString(){
        StringBuilder polyString = new StringBuilder();
        for( int ind = 0 ; ind < _polySize ; ind++ ) {
            polyString.append(_polynome.get(ind).toString());
        }
        return new String(polyString.toString());
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
        Double coefVal;
        Integer powerVal;
        if (coefArr != null)
            coefArr.clear();
        if (powerArr != null)
            powerArr.clear();


        System.out.println("Lets fill polynomial #" + polynomeNumber + ": ");
        System.out.println("Please insert a set of double-type values to be used as coefficients in your polynomial");
        coefVal = sc.nextDouble();

        while (true) {
            try{
                coefArr.add(coefVal);
                System.out.println("Please add another coefficient value or press 'Q' to finish");
                coefVal = sc.nextDouble();
            }
            catch (Exception e){
                break;
            }
        }

        System.out.println("Now please insert a set of integer-type values to be used as powers in your polynomial");
        powerVal = sc.nextInt();
        while (true) {
            try{
                powerArr.add(powerVal);
                System.out.println("Please add another power value or press 'Enter' to finish");
                powerVal = sc.nextInt();
            }
            catch (Exception e){
                break;
            }
        }

    }

}
