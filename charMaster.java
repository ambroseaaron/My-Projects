import java.util.Scanner;
/**
 * @author Aaron Ambrose
 * Program Description: This program manipulates an array in the following ways:
 *                      - Converts the array to a string
 *                      - Reverses and clones the array
 *                      - Checks if a character appears in an array
 * Date: 2/10/16
 */
class charMaster {
     public static void main(String[] args){
          Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a word as an array to be manipulated...");
        char[] word=sc.next().toCharArray(); //creates an array that you input

        System.out.println("The array converted to a string reads:");

   	for ( int i = 0; i < word.length; i++ ){
            System.out.println( "word[" + i + "] = " + word[i] );//prints the array element by element
        }

        String master;
        master = charMaster.char2str(word); //creates a string named master from the character array
        System.out.print("The inputted word as a string reads: ");
        System.out.println(master);

        word = charMaster.str2char(master); //creates the character array from the string
        System.out.println("The string converted back to the array reads:");

   	for ( int i = 0; i < word.length; i++ ){
            System.out.println( "word[" + i + "] = " + word[i] ); //prints the array element by element
        }

        System.out.println("Type a character to see how many times it appears in the word.");

        Scanner tScan = new Scanner(System.in);
        String tBuffer = tScan.next();
        char oneChar = tBuffer.charAt(0); //accepts an inputted character

        int counted = charMaster.count(oneChar, word); //counts how many times the character appears in the word
        System.out.format("%c Appears %d times.\n",oneChar, counted);

        char[] reversedword = charMaster.reverse(word); //reverses the character array
        System.out.print("The reversed word is: ");
        System.out.println(reversedword);

        char[] cloned = charMaster.clone(word); //clones the original character array
        System.out.print("The cloned word is: ");
        System.out.println(cloned);

        System.out.println("Is the original word the same as the reversed word?");
        System.out.println(charMaster.equals(word,reversedword)); //checs if the reversed word is the same as the original word

        System.out.println("Is the original word the same as the cloned word?");
        System.out.println(charMaster.equals(word, cloned)); //checks if the cloned word is the same as the original word
     }
    public static String char2str(char[] x){
       String words = new String(x);
       return words;
    }

    public static char[] str2char(String x){
        char[] A = x.toCharArray();
        return A;
    }
    public static char[] reverse(char[] x){
        char[] reversed = new char[x.length];
        for(int i = 0; i<x.length; i++){
            reversed[i] = x[x.length -1 -i];
        }
        return reversed;
    }

    public static int count(char c, char[] x){
        int count = 0;
        for(int i = 0; i<x.length; i++){
            if(x[i] == c){
            count = count + 1;
            }
        }
        return count;
    }

    public static char[] clone(char[] x){
        char[] clone = new char[x.length];
        System.arraycopy(x, 0, clone, 0, x.length);
        return clone;
    }

    public static boolean equals(char[] x, char[] y){
        int length1 = x.length;
        int length2 = y.length;
        int a = 0;
        if(length1 != length2){
            return false;
        }
        for(int i=0; i<length1; i++){
            if(x[i] == y[i]){
                a = a + 1;
            }
        }
        if(length1 == a){
            return true;
        }
        else{
            return false;
        }
    }
}
