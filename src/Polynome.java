import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;

public class Polynome {
    ArrayList<Monomial> _polynome;


    public Polynome(double[] coefArr, int[] powerArr){
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
        return _polynome.size();
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
        if (coefArr.length != powerArr.length)
            throw new ArrayIndexOutOfBoundsException("Coeffitient values  array and power values array are not of same length");
        for (int ind =0; ind < size ; ind++){
            try{
                currentCoef = _polynome.get(ind).get_coefficient();
                _polynome.get(ind).set_coefficient(currentCoef + coefArr[ind]);
            }
            catch Exception{
                _polynome.add(new Monomial(coefArr[ind],powerArr[ind]));
            }
        }
    }


    public Polynome plus(Polynome other){
        Monomial monom1 =  this.get;
        Monomial monom2 =  other
        while (monom1 != null && monom2 != null){
        }
        return null;

    }

    public Polynome minus(Polynome polynome){
        return null;

    }

    public Polynome derivative(Polynome polynome){
    return null;
    }



    public String toString(){
        int size = _polynome.size();
        String polyString = "";
        for( int ind = 0 ; ind < size ; ind++ )
            polyString += _polynome.get(ind).toString();

        return new String(polyString);
    }


    public Boolean equals(Polynome polynome){
        return null;

    }




}
