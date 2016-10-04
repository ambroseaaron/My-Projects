/* Aaron Ambrose
 * Homework 4, Computer Science 2
 * Program Description: This program recieves a text string as an input, parses and evaluates it
 *                      as a mathematical expression, and returns the result of the evaluation
 *                      My program requires that the inputted text file has spaces between each operation
 * Date: 5/6/16
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MyParser{

    public static int computate(String expression){

        int zero = 0; //flag
        char[] text = expression.toCharArray();
	Stack<Integer> numbers = new Stack<Integer>(); //Stack for all the numbers
	Stack<Character> operations = new Stack<Character>(); //Stack for all the math operations

        for (int i = 0; i < text.length; i++){
            if (text[i] == ' '){ //If the text is blank, then skip it
		continue;
            }

            if (text[i] >= '0' && text[i] <= '9'){            //If the text is a number, check if its more than
		StringBuffer fullnumber = new StringBuffer(); // one digit and push the full number to the stack of numbers

                while (i < text.length && text[i] >= '0' && text[i] <= '9'){ //checks if there is more than one digit
                    fullnumber.append(text[i++]);
                }
		numbers.push(Integer.parseInt(fullnumber.toString()));
                if(numbers.peek() == -999999999){ //checks if there was an error that cant be computed in the text file
                    zero = 1;
                }
            }

            else if (text[i] == '('){ //if text is a bracket, push it to the stack of operations
		operations.push(text[i]);
            }
            else if (text[i] == ')'){
		while (operations.peek() != '(') //runs until every operation in the brackets are completed
		numbers.push(compute(operations.pop(), numbers.pop(), numbers.pop()));
		operations.pop();
                if(numbers.peek() == -999999999){ //checks if there was an error that cant be computed in the text file
                    zero = 1;
                }
            }

            else if (text[i] == '+' || text[i] == '-' || text[i] == '*' || text[i] == '/'){   //sorts the operations and does
                while (!operations.empty() && orderofoperations(text[i], operations.peek())){ //them all in the correct order using
                    numbers.push(compute(operations.pop(), numbers.pop(), numbers.pop()));    //an order of operations method
                    if(numbers.peek() == -999999999){ //checks if there was an error that cant be computed in the text file
                        zero = 1;
                    }
                }
                operations.push(text[i]);
            }
            else{
                System.out.println("Error: Invalid Expression, Automatically return 0"); //if the text is not a math operation or a number
                zero = 1;                                                               //the program exits and automatically returns 0
            }
        }

        while (!operations.empty()){ //Operations have been sorted and are in order
            numbers.push(compute(operations.pop(), numbers.pop(), numbers.pop()));
            if(numbers.peek() == -999999999){ //checks if there was an error that cant be computed in the text file
                zero = 1;
            }
        }

        if(zero == 1){ //zero is a flag that makes sure the text file could be computed without errors
            return 0;
        }
        else{
            return numbers.pop(); //returns the result of the math expression
        }

    }

    //Checks the order of operations to determine which needs to
    //be computed first
    public static boolean orderofoperations(char a, char b){

        if (b == '(' || b == ')'){
            return false;
        }
	if ((a == '*' || a == '/') && (b == '+' || b == '-')){
            return false;
        }
        else{
            return true;
        }
    }

    //Method that does the math operations between two numbers
    public static int compute(char operation, int b, int a){

        switch (operation){

            case '+':
		return a + b;
            case '-':
                return a - b;
            case '*':
		return a * b;
            case '/':
		if (b == 0){
                    System.out.println("Error: Division by 0, automatically return 0");
                    return -999999999; //return a random number so its easier to check
                }
            return a / b;
	}
	return 0;
    }

    public static void main(String[] args) throws FileNotFoundException{
        String content = new Scanner(new File("TextFile.txt")).useDelimiter("\\Z").next();
        System.out.println(MyParser.computate(content));
    }
}
