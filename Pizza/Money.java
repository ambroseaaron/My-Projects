//make sure to include the package!!! e.g., package <hawkid>hw3
package mbrshw3;

import java.text.DecimalFormat;

public class Money {
    private int dollars;
    private int cents;

    public Money(){
        this.dollars = 0;
        this.cents = 0;

    }

    public Money(int dollars) {
        this.dollars = dollars;
        this.cents = 0;
    }

    public Money(int dollars, int cents) {
        this.dollars = dollars;
        this.cents = cents;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public static Money add(Money first, Money second){
        int cents = first.cents + second.cents;
        int dollars = 0;
        while(cents >= 100){
            dollars++;
            cents -= 100;
        }
        dollars = dollars + first.dollars + second.dollars;
        Money ans = new Money(dollars, cents);
        return ans;
    }

    public Money add(Money money){
        int cents = money.cents + this.cents;
        int dollars = 0;
        while(cents >= 100){
            dollars++;
            cents -= 100;
        }
        dollars = dollars + money.dollars + this.dollars;
        Money ans = new Money(dollars, cents);
        return ans;
    }

    public static Money subtract(Money first, Money second){
        int f = first.dollars * 100;
        int s = second.dollars * 100;
        f = f + first.cents;
        s = s + second.cents;
        f = f - s;
        s = 0;
        while(f >= 100){
            s++;
            f -= 100;
        }
        Money ans = new Money(s, f);
        return ans;
    }

    public Money subtract(Money money){
        int f = this.dollars * 100;
        int s = money.dollars * 100;
        f = f + this.cents;
        s = s + money.cents;
        f = f - s;
        s = 0;
        while(f >= 100){
            s++;
            f -= 100;
        }
        Money ans = new Money(s, f);
        return ans;
    }

    public boolean equals(Money first, Money second){
        if(first.dollars == second.dollars && first.cents == second.cents){
            return true;
        }
        return false;
    }

    public boolean equals(Money money){
        if(this.dollars == money.dollars && this.cents == money.cents){
            return true;
        }
        return false;
    }

    public String toString(){
        String ans = "$";
        int dollars = this.dollars;
        int cents = this.cents;
        ans = ans + dollars + "." + cents;
        //System.out.println(ans);
        return ans;
    }
}

class Part2 {
    // Sample Main
    public static void main(String[] args) {
        Money money1 = new Money();
        System.out.println("Money is: " + money1);

        Money money2 = new Money(5);
        System.out.println("Money is: " + money2);

        Money money3 = new Money(10, 99);
        System.out.println("Money is: " + money3);

        System.out.println("Money1's dollars are: " + money1.getDollars());
        System.out.println("Money1's cents are: " + money1.getCents());

        System.out.println("Changing money1's dollars.");
        money1.setDollars(3);
        System.out.println("Money1 is : " + money1);

        System.out.println("Chaning money1's cents.");
        money1.setCents(50);
        System.out.println("Money1 is: " + money1);

        System.out.println("Money1 is equal to money2: " + money1.equals(money2));

        Money money2copy = new Money(money2.getDollars(),
                money2.getCents());
        System.out.println("Money2 is equal to a copy of money2: " + money2.equals(money2copy));

        Money result = Money.add(money1, money2);
        System.out.println("Adding money1 and money2: " + result);
        System.out.println("Adding result from above and money3: " + Money.add(result, money3));

        System.out.println("Subtracting money1 from money2: " + Money.subtract(money2, money1));
        System.out.println("Subtracting money1 from money3: " + Money.subtract(money3, money1));

        result = money1.add(money1);
        System.out.println("Using non-static add for money1 and money2: " + result);
        System.out.println("Using non-static add for adding result from before and money3: " + result.add(money3));

        System.out.println("Using non-static subtract, money1 from money2: " + money2.subtract(money1));
        System.out.println("Using non-static subtract, money1 from money3: " + money3.subtract(money1));
    }
}

