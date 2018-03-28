/*
 *
 */
package temperature;

/**
 *
 * @author Aaron Ambrose
 */
public class Temperature {

    // Instance Variables
    private double value;
    private char scale;

    // Constructors
    public Temperature () {
        this.value = 0.0;
        this.scale = 'C';
    }

    public Temperature(double val) {
        this.value = val;
        this.scale = 'C';
    }

    public Temperature(char scl) {
        this.value = 0.0;
        this.scale = scl;
    }

    public Temperature(double val, char scl) {
        this.value = val;
        this.scale = scl;
    }

    // Accessor Methods
    public double getTemperatureFahrenheit() {
        double amount = 0.0;
        if( scale == 'F'){
            return this.value;
        }
        else {
            amount = (9*(this.value / 5)) + 32;
            return amount;
        }
    }

    public double getTemperatureCelsius() {
        double amount = 0.0;
        if( scale == 'C'){
            return this.value;
        }
        else{
            amount = ((5*(this.value - 32)) / 9);
            return amount;
        }
    }

    // Mutator Methods
    public void setValue(double val) {
        this.value = val;
    }

    public void setScale(char scl) {
        this.scale = scl;
    }

    public void setValueAndScale(double val, char scl) {
        this.value = val;
        this.scale = scl;
    }
    public boolean equals (Temperature other) {
        boolean result = true;
        if(scale == 'F'){
            if(this.value != other.getTemperatureFahrenheit()){
                result = false;
            }
        }
        else{
            if( this.value != other.getTemperatureCelsius()){
                result = false;
            }
        }
        return result;
    }

    public boolean isGreaterThan(Temperature other) {
        boolean result = true;
        if(scale == 'F'){
            if(this.value < other.getTemperatureFahrenheit()){
                result = false;
            }
        }
        else{
            if( this.value < other.getTemperatureCelsius()){
                result = false;
            }
        }
        return result;
    }

    public boolean isLessThan(Temperature other) {
        boolean result = true;
        if(scale == 'F'){
            if(this.value > other.getTemperatureFahrenheit()){
                result = false;
            }
        }
        else{
            if( this.value > other.getTemperatureCelsius()){
                result = false;
            }
        }
        return result;
    }
// end Temperature Class

    public static void main(String[] args) {
        // Testing the constructor methods
        Temperature one = new Temperature();
        System.out.println("Temp one: " + one.value);

        Temperature two = new Temperature(56.8);
        System.out.println("Temp two: " + two.value);

        Temperature three = new Temperature('F');
        System.out.println("Temp three: " + three.value);

        Temperature four = new Temperature(33.5, 'F');
        System.out.println("Temp four: " + four.value);

        // Testing the accessor methods
        System.out.println("Temp one as Fahrenheit: "
                + one.getTemperatureFahrenheit());
        System.out.println("Temp three as Celsius: "
                + three.getTemperatureCelsius());

        // Testing the mutator methods
        System.out.println("Changing temp two's degrees.");
        two.setValue(34.6);
        System.out.println(two.value);

        System.out.println("Changing temp four's scale.");
        four.setScale('C');
        System.out.println(four.value);

        System.out.println("Changing scale and value of temp three.");
        three.setValueAndScale(100, 'C');
        System.out.println(three.value);

        // Testing the comparison methods
        System.out.println("Are temp two and three equal: "
                + two.equals(three));
        System.out.println("Creating a duplicate of temp four.");
        Temperature fourDup = new Temperature();
        fourDup.setValueAndScale(four.getTemperatureFahrenheit(), 'F');
        System.out.println(fourDup.value);
        System.out.println("Temp four is equal to fourDup: " +
                four.equals(fourDup));

        System.out.println("Temp two greater than temp three: "
                + two.isGreaterThan(three));
        System.out.println("Temp three greater than temp two: "
                + three.isGreaterThan(two));

        System.out.println("Temp two less than temp three: "
                + two.isLessThan(three));
        System.out.println("Temp three less than temp two: "
                + three.isLessThan(two));
    }

}
