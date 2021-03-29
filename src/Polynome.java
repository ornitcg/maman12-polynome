import java.util.ArrayList;
import java.lang.String;
import java.util.Collections;

public class Polynome {
    ArrayList<Monomial> _polynome;


    public Polynome(double[] coefArr, int[] powerArr){
        buildPolynome();
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

    private void buildPolynome() {

    }


    public Polynome plus(Polynome other){
        Monomial monom1 =  this.get;
        Monomial monom2 =  other
        while (monom1 != null && monom2 != null){


        }

    }

    public Polynome minus(Polynome polynome){

    }

    public Polynome derivative(Polynome polynome){

    }

    public String toString(Polynome polynome){


    }

    public Boolean equals(Polynome polynome){

    }




}
