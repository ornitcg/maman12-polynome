import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;

public class Polynome {

    private static final char PLUS = '+' ;
    private static final char MINUS = '-';

    private ArrayList<Monomial> _polynome;
    private int _polySize;


    public Polynome(double[] coefArr, int[] powerArr){
        int size = coefArr.length;
        double currentCoef;
        _polySize = size;

        if (coefArr.length != powerArr.length)
            throw new ArrayIndexOutOfBoundsException("Coeffitient values  array and power values array are not of same length");

        for (int ind =0; ind < size ; ind++){
            try{
                currentCoef = _polynome.get(ind).get_coefficient();
                _polynome.get(ind).set_coefficient(currentCoef + coefArr[ind]);
            }
            catch (Exception e){
                _polynome.add(new Monomial(coefArr[ind],powerArr[ind]));
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
        double newCoef;
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
            Monomial monom1 =  newPolynome.getMonomial(indThis);
            Monomial monom2 =  other.getMonomial(indOther);
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
        double currentCoef;
        int currentPower;

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




}
