
public class Monomial implements Comparable{

    private Double _coefficient;
    private Integer _power;

    // Constructor

    /**
     * Constructor of a monomial
     * @param coeffitient - The coefficient of the monomial
     * @param power - The power of the monomial
     */
    public Monomial(double coeffitient, int power){
        _coefficient = coeffitient;
        _power = power;
    }


    /**
     * Copy Construcor
     * @param other - the monomial to copy
     */
    public Monomial(Monomial other){
        _coefficient = other.get_coefficient();
        _power = other.get_power();
    }



    /**
     * Creates a string that represents a single monomial
     * @return a String
     */
    public String toString(){
        String coefStr = "",  powerStr = "";

        if ((_coefficient != 1 && _coefficient != -1) || _power == 0)
            coefStr = String.format("%.2f", Math.abs(_coefficient));

        if (_coefficient != 0 && _power == 1)
            powerStr = "x";
        else if (_coefficient != 0 && _power > 1)
            powerStr = "x^"+ _power;

        return   coefStr + powerStr;
    }


    // Compares two monomials. returns true if both monomial powers are equal.
    // and false otherwise. Does not compare coefficients.
    public int compareTo(Object monomial) {
        return _power.compareTo(((Monomial)monomial).get_power());
    }

    // Returns the power of calling monomial


    /**
     * Gets the power value of the calling monomial
     * @return - The calling monomials' power value
     */
    public int get_power() {
        return _power;
    }



    /**
     * Gets the coefficient value of calling monomial
     * @return double - the coefficient value
     */
    public double get_coefficient() {
        return _coefficient;
    }


    /**
     * Sets the calling monomial's coefficient to be newCoefficient
     * @param newCoeffitient - The new value of coefficient to set
     */
    public void set_coefficient(double newCoeffitient) {
        _coefficient = newCoeffitient;
    }

    /**
     * Sets the power value of the calling monomial
     * @param newPower - The new value of power, to set
     */
    public void  set_power(int newPower){
        _power = newPower;
    }

    /**
     * Adds coef value to the calling monomial existing coefficient value
     * @param coef - the value to add
     */
    public void add_coefficient(Double coef) {
        _coefficient += coef;
    }

    /**
     * Checks if calling monomial and 'other' are similar in both coefficient and power values
     * @param other - the other monomial to compare to
     * @return - true if both coefficient and power values are equal in both monomials
     * and, false, otherwise
     */
    public Boolean equals(Monomial other){
        return _coefficient == other.get_coefficient() && _power == other.get_power();
    }

    /**
     * Changes the sign of the coefficient in the calling monomial
     */
    public void changeSign() {
        _coefficient = (-1) * _coefficient;
    }
}
