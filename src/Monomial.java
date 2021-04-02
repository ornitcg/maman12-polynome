


public class Monomial implements Comparable{

    private Double _coefficient;
    private Integer _power;


    // copy constructor
    public Monomial(Monomial other){
        _coefficient = other.get_coefficient();
        _power = other.get_power();
    }


    public Monomial(double coeffitient, int power){
        _coefficient = coeffitient;
        _power = power;
    }



    public String toString(){
        String coefStr = "",  powerStr = "";

        if (_coefficient > 1)
            coefStr = String.valueOf(_coefficient);

        if (_coefficient != 0 && _power == 1)
            powerStr = "x";
        else if (_coefficient != 0 && _power > 1)
            powerStr = "x^"+ _power;
        return coefStr + powerStr;
    }



    public int compareTo(Object monomial) {
        return _power.compareTo(((Monomial)monomial).get_power());
    }


    public int get_power() {
        return _power;
    }

    public double get_coefficient() {
        return _coefficient;
    }

    public void set_coefficient(double newCoeffitient) {
        _coefficient = newCoeffitient;
    }

    public void  set_power(int newPower){
        _power = newPower;
    }

    public void add_coefficient(Double coef) {
        _coefficient += coef;
    }
}
