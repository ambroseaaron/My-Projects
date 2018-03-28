//make sure to include the package!!! e.g., package <hawkid>hw3
package mbrshw3;

public class Pizza
{
    private String size;
    private int numCheeseToppings;
    private int numHamToppings;
    private int numPepperoniToppings;

    public Pizza() {
        this.size = "";
        this.numCheeseToppings = 0;
        this.numHamToppings = 0;
        this.numPepperoniToppings = 0;
    }

    public Pizza(String pizzaSize, int cheese, int ham, int pepperoni) {
        this.size = pizzaSize;
        this.numCheeseToppings = cheese;
        this.numHamToppings = ham;
        this.numPepperoniToppings = pepperoni;
    }

    public void setPizzaInfo(String newSize, int cheese, int ham, int pepperoni) {
        this.size = newSize;
        this.numCheeseToppings = cheese;
        this.numHamToppings = ham;
        this.numPepperoniToppings = pepperoni;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setNumHamToppings(int numHamToppings) {
        this.numHamToppings = numHamToppings;
    }

    public void setNumPepperoniToppings(int numPepperoniToppings) {
        this.numPepperoniToppings = numPepperoniToppings;
    }

    public void setNumCheeseToppings(int numCheeseToppings) {
        this.numCheeseToppings = numCheeseToppings;
    }

    public int getNumCheeseToppings() {
        return this.numCheeseToppings;
    }

    public int getNumHamToppings() {
        return this.numHamToppings;
    }

    public int getNumPepperoniToppings() {
        return this.numPepperoniToppings;
    }

    public double calcCost() {
        this.size = this.size.toLowerCase();
        int size = 0;
        double cost = 0;
        if(this.size == "small"){
            size = 10;
        }
        else if(this.size == "medium"){
            size = 12;
        }
        else if(this.size == "large"){
            size = 14;
        }
        cost = (size + (2)*(this.numCheeseToppings + this.numPepperoniToppings + this.numHamToppings));
        return cost;
    }

    public String getDescription() {
        String description = "Size: " + this.size.toString() +
                " Cheese Toppings: " + this.numCheeseToppings +
                " Pepperoni Toppings: " + this.numPepperoniToppings +
                " Ham Toppings: " + this.numHamToppings +
                " Cost: " + "$" + calcCost();

        return description;
    }

    public static void main(String[] args) {
        // Create a few sample pizzas and output their prices
        Pizza supreme = new Pizza("Large",1,2,1);
        Pizza cheese = new Pizza("Medium",2,0,0);
        Pizza pepperoni = new Pizza("Small",0,0,2);

        System.out.println(supreme.getDescription());
        System.out.println(cheese.getDescription());
        System.out.println(pepperoni.getDescription());
    }
}
