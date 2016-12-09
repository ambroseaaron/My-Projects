/**
 * This class tests the MorseCode class by asking the user for a message, then converting that phrase
 * into morse code, and then back to english
 * @author Aaron Ambrose
 */

import java.util.*;

public class MorseCodeTest {

    public static void main(String[] args){

        System.out.println("Enter a message to convert to morse code.");
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        phrase = phrase.toUpperCase();
        MorseCode message = new MorseCode(phrase); //saves the typed phrase in the morseCode class

        System.out.println("The message in morse code is now: " + message.getMorseCode());
        System.out.println("Switching the message to English gives: " + message.getEnglish());

    }
}
