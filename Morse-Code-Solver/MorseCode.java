/**
 * MorseCode - Medium
 * This class contains methods to convert a typed phrase into its morse code equivalent, and then
 * convert that morse code back to its english equivalent.  The two methods that make this happen
 * are getMorseCode and getEnglish and they each implement the methods toMorse and toEnglish
 * @author Aaron Ambrose
 */

public class MorseCode {
    private String phrase;
    private String morseCode;
    private String[] morse;

    MorseCode(String morse){
        this.phrase = morse;
    }

    /**
     * toMorse method that converts a specific character into its morse code equivalent
     * @param c char to change to morse code
     * @return string of the characters morse code equivalent
     */
    public String toMorse(char c){
        switch(c){
            case '0': return "-----";
            case '1': return ".----";
            case '2': return "..---";
            case '3': return "...--";
            case '4': return "....-";
            case '5': return ".....";
            case '6': return "-....";
            case '7': return "--...";
            case '8': return "---..";
            case '9': return "----.";
            case 'A': return ".-" ;
            case 'B': return "-..." ;
            case 'C': return "-.-." ;
            case 'D': return "-.." ;
            case 'E': return "." ;
            case 'F': return "..-." ;
            case 'G': return "--." ;
            case 'H': return "...." ;
            case 'I': return "..";
            case 'J': return ".---" ;
            case 'K': return "-.-" ;
            case 'L': return ".-.." ;
            case 'M': return "--" ;
            case 'N': return "-." ;
            case 'O': return "---" ;
            case 'P': return ".--." ;
            case 'Q': return "--.-" ;
            case 'R': return ".-." ;
            case 'S': return "..." ;
            case 'T': return "-" ;
            case 'U': return "..-" ;
            case 'V': return "...-" ;
            case 'W': return ".--" ;
            case 'X': return "-..-" ;
            case 'Y': return "-.--" ;
            case 'Z': return "--.." ;
            default: return "  ";
        }
    }

    /**
     * Method that converts the whole phrase to morse code by calling toMorse on individual characters
     * @return morseCode message in morse code
     */
    public String getMorseCode(){
        morseCode = "";
        morse = new String[50];
        for(int i = 0; i < phrase.length(); i++){
            morseCode = morseCode + toMorse(phrase.charAt(i)) + " ";
            morse[i] = toMorse(phrase.charAt(i));
        }
        return morseCode;
    }

    /**
     * Method to convert a string of morse code into its equivalent character
     * @param m string of morse code
     * @return char of the morse code equivalent
     */
    public char toEnglish(String m){
        switch(m){
            case "-----": return '0';
            case ".----": return '1';
            case "..---": return '2';
            case "...--": return '3';
            case "....-": return '4';
            case ".....": return '5';
            case "-....": return '6';
            case "--...": return '7';
            case "---..": return '8';
            case "----.": return '9';
            case ".-": return 'A';
            case "-...": return 'B';
            case "-.-.": return 'C';
            case "-..": return 'D';
            case ".": return 'E';
            case "..-.": return 'F';
            case "--.": return 'G';
            case "....": return 'H';
            case "..": return 'I';
            case ".---": return 'J';
            case "-.-": return 'K';
            case ".-..": return 'L';
            case "--": return 'M';
            case "-.": return 'N';
            case "---": return 'O';
            case ".--.": return 'P';
            case "--.-": return 'Q';
            case ".-.": return 'R';
            case "...": return 'S';
            case "-": return 'T';
            case "..-": return 'U';
            case "...-": return 'V';
            case ".--": return 'W';
            case "-..-": return 'X';
            case "-.--": return 'Y';
            case "--..": return 'Z';
            default: return ' ';
        }
    }

    /**
     * Method that converts a string array of morse code into a character phrase
     * @return String of the english equivalent phrase
     */
    public String getEnglish(){
        String english = "";
        for(int i = 0; i < phrase.length(); i++){
            english = english + toEnglish(morse[i]);
        }

        return english;
    }

}
