import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;

public class Polynome {

    private ArrayList<Monomial> _polynome;
    private int _polySize;


    public Polynome(double[] coefArr, int[] powerArr){
        _polySize = 0;
        buildPolynome(coefArr, powerArr);
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



    private void buildPolynome(double[] coefArr, int[] powerArr) throws ArrayIndexOutOfBoundsException{
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

    }


    public Polynome plus(Polynome other){
       // Monomial monom1 =  this.get;
       // Monomial monom2 =  other
       // while (monom1 != null && monom2 != null){
       // }
        return null;

    }

    public Polynome minus(Polynome polynome){
        return null;

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
