/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplaneseatreservation;

/**
 *
 * @author mtlash
 */
public class AirplaneSeatReservation {

    /**
     * @param args the command line arguments
     */
    char[][] seats;
    
    // Constructor method
    AirplaneSeatReservation(int numOfRows, int numOfColumns) {
        seats = new char[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++) 
            for(int j = 0; j < numOfColumns; j++)
                seats[i][j] = (char)(65+j);
    }
    
    void reserveSeat(int row, char col) {
        switch (col) {
            case 'A':
                seats[row-1][0] = (char)(88);
                break;
            case 'B':
                seats[row-1][1] = (char)(88);
                break;
            case 'C':
                seats[row-1][2] = (char)(88);
                break;
            case 'D':
                seats[row-1][3] = (char)(88);
                break;
        }
    }
    
    void freeSeat(int row, char col) {
        switch (col) {
            case 'A':
                seats[row-1][0] = (char)(65);
                break;
            case 'B':
                seats[row-1][1] = (char)(66);
                break;
            case 'C':
                seats[row-1][2] = (char)(67);
                break;
            case 'D':
                seats[row-1][3] = (char)(68);
                break;
        }
    }
    
    int getNumberOfSeatsAvailable() {
        int num = 0;
        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 4; j++)
                if(seats[i][j] != (char)(88))
                    num = num + 1;
        return num;
    }
    
    void displaySeats() {
        for(int i = 0; i < 8; i++){
            System.out.print(i+1 + " ");
            for(int j = 0; j < 4; j++)
                if(seats[i][j] == 'X'){
                    System.out.print("X ");
                }
                else{
                    System.out.print((char)(65 + j) + " ");
                }
            System.out.println(" ");
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        AirplaneSeatReservation airplane1 = new AirplaneSeatReservation(8,4);
        airplane1.displaySeats();
        System.out.println(airplane1.getNumberOfSeatsAvailable());
        airplane1.reserveSeat(2, 'C');
        airplane1.reserveSeat(2, 'D');
        airplane1.reserveSeat(6, 'B');
        System.out.println(airplane1.getNumberOfSeatsAvailable());
        airplane1.displaySeats();
        airplane1.freeSeat(2, 'C');
        System.out.println(airplane1.getNumberOfSeatsAvailable());
        airplane1.displaySeats();
    }
    
}
