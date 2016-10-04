/**
 * @author Aaron Ambrose
 * Program Description: This program takes two inputted vectors and manipulates them by
 *                      adding, subtracting, multiplying, and taking the cross and dot product
 *                      of the two vectors.
 * Date: 2/10/16
 */


import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


class Vex {
    int x,y,z;

    public Vex( int x, int y, int z){ //constructs a vector
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static void main(String[] args){

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Enter the x value for the vector"); //Accepts the inputs for the two vectors
        int x = inputReader.nextInt();
        System.out.println("Enter the y value for the vector");
        int y = inputReader.nextInt();
        System.out.println("Enter the y value for the vector");
        int z = inputReader.nextInt();

        Vex vector = new Vex(x,y,z); //creates new vector

        System.out.println("Enter the x value for the second vector");
        x = inputReader.nextInt();
        System.out.println("Enter the y value for the second vector");
        y = inputReader.nextInt();
        System.out.println("Enter the y value for the second vector");
        z = inputReader.nextInt();

        Vex othervex = new Vex(x,y,z); //creates new vector

        System.out.println("Your first created vector is:");
        System.out.println(vector); //prints off the first vector

        System.out.println("Your second created vector is:");
        System.out.println(othervex); //prints off the second vector

        vector.toString();

        vector = vector.add(othervex); //add the two vectors together
        System.out.println("Adding the two vectors creates:");
        System.out.println(vector);

        System.out.println("Enter the factor you want to scale the vector by");
        z = inputReader.nextInt();

        vector = vector.scalarMult(z); //multiplys the vector by your inputted factor
        System.out.println("Multiplying the new vector by your scalar creates:");
        System.out.println(vector);

        vector = vector.subtract(othervex); //subtracts the second vector from the new vector
        System.out.println("Subtracting the second vector from the newly created vector creates:");
        System.out.println(vector);

        int num = vector.innerMult(othervex); //multiplys the new vector by the second vector
        System.out.println("The dot product of the new vector and the original second vector creates:");
        System.out.println(num);

        num = vector.norm1(); //calls the method to add the elements of the vector
        System.out.println("Adding each element of the vector together without regards to sign creates::");
        System.out.println(num);

        double num2 = vector.norm2(); //calls the method to find the Euclidean norm
        System.out.println("The Euclidean norm equals:");
        System.out.println(num2);

        System.out.println("The two vectors are:");
        System.out.println(othervex);
        System.out.println(vector);

        System.out.println("Are the two vectors equal?");
        System.out.println(vector.equals(othervex)); //checks if the two vectors are the same

        System.out.println("Cloning vector...");
        othervex = vector.clone(); //clones the vector

        System.out.println("The two vectors now are:");
        System.out.println(othervex);
        System.out.println(vector);

        System.out.println("Are the two vectors equal?");
        System.out.println(vector.equals(othervex)); //checks if they are now equal
    }

    public String toString(){
        return "Vex( " + x + ", " + y + ", " + z + ")";
    }

    public Vex add( Vex other ){
        int new_x = this.x + other.x,
            new_y = this.y + other.y,
            new_z = this.z + other.z;
        return new Vex( new_x, new_y, new_z );
    }

    public Vex subtract( Vex other ){
        int new_x = this.x - other.x,
            new_y = this.y - other.y,
            new_z = this.z - other.z;
        return new Vex( new_x, new_y, new_z );
    }

     public Vex scalarMult( int scalar ){
        int new_x = this.x * scalar,
            new_y = this.y * scalar,
            new_z = this.z * scalar;
        return new Vex( new_x, new_y, new_z );
     }

     public int innerMult( Vex other ){
        int new_x = this.x * other.x,
            new_y = this.y * other.y,
            new_z = this.z * other.z;

        new_x = new_x + new_y + new_z;
        return ( new_x );
         }

     public int norm1(){
         if(this.x < 0){
             this.x = this.x * -1;
         }
         if(this.y < 0){
             this.y = this.y * -1;
         }
         if(this.z < 0){
             this.z = this.z * -1;
         }

         int num = this.x + this.y + this.z;
         return ( num );
     }

     public double norm2 (){
         double new_x, new_y, new_z;
         new_x = this.x * this.x;
         new_y = this.y * this.y;
         new_z = this.z * this.z;
         new_x = new_x + new_y + new_z;
         new_x = Math.sqrt(new_x);
         return ( new_x );
     }

     public boolean equals( Vex other ){
         int x = 0;
         if(this.x == other.x){
             x = x + 1;
         }
         if(this.y == other.y){
             x = x + 1;
         }
         if(this.z == other.z){
             x = x + 1;
         }
         if(x == 3){
             return true;
         }
         else{
             return false;
         }
     }

     public Vex clone(){
         int x = this.x,
             y = this.y,
             z = this.z;
         Vex cloned = new Vex(x,y,z);
         return cloned;
     }
}
