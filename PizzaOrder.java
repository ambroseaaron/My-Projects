//make sure to include the package!!! e.g., package <hawkid>hw3
package mbrshw3;

public class PizzaOrder {
    private Pizza pizza1, pizza2, pizza3;
    private int numPizzas;

    public PizzaOrder()	{
        this.numPizzas = 0;
    }

    // Copy constructor
    public PizzaOrder(PizzaOrder order)	{
        numPizzas = order.numPizzas;
        pizza1 = null;
        pizza2 = null;
        pizza3 = null;
        // Make copies of the Pizza objects -- this is an example of using
        // one of the accessor methods you need to write!!
        Pizza orig = order.getPizza1();
        //You write the rest. Hint: you will need to do an "if orig not null...
        // do stuff" for each of the three possible pizzas....
        if(orig != null){
            pizza1.setPizzaInfo(orig.getSize(), orig.getNumCheeseToppings(), orig.getNumHamToppings(), orig.getNumPepperoniToppings());
        }
        orig = order.getPizza2();
        if(orig != null){
            pizza2.setPizzaInfo(orig.getSize(), orig.getNumCheeseToppings(), orig.getNumHamToppings(), orig.getNumPepperoniToppings());
        }
        orig = order.getPizza3();
        if(orig != null){
            pizza3.setPizzaInfo(orig.getSize(), orig.getNumCheeseToppings(), orig.getNumHamToppings(), orig.getNumPepperoniToppings());
        }
    }

    public void setNumPizzas(int num){
        this.numPizzas = num;
    }

    public int getNumPizzas(){
        return this.numPizzas;
    }

    public void setPizza1(Pizza pizza){
        this.pizza1 = pizza;
    }

    public void setPizza2(Pizza pizza){
        this.pizza2 = pizza;
    }

    public void setPizza3(Pizza pizza){
        this.pizza3 = pizza;
    }

    public Pizza getPizza1(){
        return this.pizza1;
    }

    public Pizza getPizza2(){
        return this.pizza2;
    }

    public Pizza getPizza3(){
        return this.pizza3;
    }

    public double calcTotal(){
        double cost = 0;
        if(this.numPizzas == 1){
            cost = this.pizza1.calcCost();
        }
        else if(this.numPizzas == 2){
            cost = (this.pizza1.calcCost() + this.pizza2.calcCost());
        }
        else if(this.numPizzas == 3){
            cost = (this.pizza1.calcCost() + this.pizza2.calcCost() + this.pizza3.calcCost());
        }
        return cost;
    }

	public static void main(String[] args) {
		Pizza pizza1 = new Pizza("Large",1,1,0);
		Pizza pizza2 = new Pizza("Medium",2,0,2);
		PizzaOrder order1 = new PizzaOrder();
		order1.setNumPizzas(2);
		order1.setPizza1(pizza1);
		order1.setPizza2(pizza2);
		double total = order1.calcTotal();	// Should be 38
		System.out.println("Total of original order: " +
			total);

		// Copy the order
		PizzaOrder order2 = new PizzaOrder(order1);
		// Change one topping to new order
		order2.getPizza1().setNumCheeseToppings(3);
		total = order2.calcTotal();
		// total should be 22 + 20 = 44
		double origTotal = order1.calcTotal();
		// origTotal should still be 38

		System.out.println("Total of copied and " +
			"modified order: " + total);
		System.out.println("Original total unchanged at: " +
			origTotal);
	}
}