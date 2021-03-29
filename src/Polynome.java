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


    private void sortPolynome() {
        Collections.sort(_polynome);
    }

    private void buildPolynome() {

    }


    public Polynome plus(Polynome polynome){
        Monomial monom1, monom2;
        while

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
