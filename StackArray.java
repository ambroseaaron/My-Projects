/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackarray;

/**
 *
 * @author mtlash
 */
public class StackArray {

    /**
     * @param args the command line arguments
     */
    //Fields
    static final int capacity = 15;
    int data[] = new int[capacity];
    int top = -1;
    
    public void push(int element) {
        if (top < capacity - 1) {
            top++;
            data[top] = element;
            
            System.out.println("Element: " + element + " is pushed onto stack.");
            printElements(); 
        }
        else {
            System.out.println("Error: Cannot add to a full stack!");
        }
    }
    
    public void printElements() {
        if (top >= 0) {
            System.out.print("Elements in stack: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(" " + data[i]);
            }
            System.out.println();
        }
    }            
    
    public void pop() {
        if (top != -1) {
            top--;
            System.out.println("Element " + data[top+1] + " removed from stack.");
        }
        else {
            System.out.println("Error: Cannot pop from an empty stack!");
        }
    }
    
    public int getNumberOfSlotsAvailable(){
        int slots = capacity - top - 1;
        System.out.println(slots + " Slots Available");
        return (capacity - top - 1);
    }
    
    public void pushArrayUsingLoop(int[] arrayToPush){
        for(int i = 0; i < arrayToPush.length; i++){
            if(getNumberOfSlotsAvailable() > 0){
                push(arrayToPush[i]);
            }
            else{
                int elements = arrayToPush.length - i;
                System.out.println("Stack Overflow. " + elements + " elements not pushed");
                i = arrayToPush.length;
            }
        }
    }
    public void pushArrayUsingCopy(int[] arrayToPush){
        int num1 = getNumberOfSlotsAvailable();
        int num2 = arrayToPush.length;
        int length = 0;
        if (num1 > num2){
            length = num2;
        }
        else{
            length = num1;
        }
        System.arraycopy(arrayToPush, 0, data, top+1, length);
        if(num1 > num2){
            top = top + num2;
        }
        else{
            top = top + num1;
        }
    }
    
    public void pushArrayUsingLoopReverse(int[] arrayToPush){
        for(int i = arrayToPush.length-1; i > 0; i--){
            if(getNumberOfSlotsAvailable() > 0){
                push(arrayToPush[i]);
            }
            else{
                int elements = i;
                System.out.println("Stack Overflow. " + elements + " elements not pushed");
                i = 0;
            }
        }
    }
    
    public void popMultiple(int k){
        if(k > top){
            top = -1;
            System.out.println("System Underflow");
        }
        else{
            while(k > 0 && k != -1){
                pop();
                k = k - 1;
            }
        }
    }
    
    public static void main(String[] args) {
        // Usage of stack
        StackArray stackDemo = new StackArray();
        System.out.println(stackDemo.getNumberOfSlotsAvailable());
        stackDemo.pop();
        stackDemo.push(23);
        stackDemo.push(2);
        stackDemo.push(3);
        stackDemo.getNumberOfSlotsAvailable();
        stackDemo.pushArrayUsingCopy(new int[]{2,5,8,4,6,9});
        stackDemo.printElements();
        stackDemo.printElements();
        stackDemo.popMultiple(12);
        stackDemo.printElements();
        stackDemo.pushArrayUsingCopy(new int[]{100,200,300,400,500});
        stackDemo.printElements();
        System.out.println(stackDemo.getNumberOfSlotsAvailable());
        stackDemo.pushArrayUsingLoopReverse(new
                int[]{100,200,300,400,500,600,700});
        stackDemo.printElements();
        
    }
    
}
