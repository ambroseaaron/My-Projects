/*
 * This program tests different string manipulations
 */
package stringmanip;

/**
 *
 * @author Aaron Ambrose
 */
public class StringManip {

    // Parallel arrays of characters and code equivalents
    private char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ' ' };
    private String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--..", ".----", "..---", "...--", "....-", ".....", "-....",
            "--...", "---..", "----.", "-----", "/" };

    public int countInstances(String input, String target){
        int count = 0;
        StringBuffer instance = new StringBuffer();
        int j = 0;
        // loops for each letter in the input string
        for( int i = 0; i < input.length();){
            j = 0;
            //loops while the lengths and characters of the target match
            while( j < target.length() && i < input.length() && input.charAt(i) == target.charAt(j)){
                instance.append(input.charAt(i));
                j = j + 1;
                i = i + 1;
            }
            i = i - j;
            // checks if the current instance matches the target string
            if(instance.toString().equals(target)){
                count = count + 1;
            }
            i = i + 1;
            instance.setLength(0);
        }
        return count;
    }

    public String removeSpaces(String input){
        char[] inputChars = input.toCharArray();
        StringBuffer buff = new StringBuffer();
        // loops and adds character to string if its not a space
        for( int i = 0; i < inputChars.length; i++){
            if (inputChars[i] != ' '){
                buff.append(inputChars[i]);
            }
        }
        String output = buff.toString();
        return output;
    }

    public boolean isPalindrome(String input){
        int front = 0;
        int back = input.length()-1;
        char[] word = input.toCharArray();
        // checks if the front letter matches the back letter
        while( back > front){
            if(word[front] != word[back]){
                return false;
            }
            back = back - 1;
            front = front + 1;
        }
        return true;
    }

    public String toMorse(String input) {
        char[] sentence = input.toUpperCase().toCharArray();
        char currentLetter;
        StringBuffer buff = new StringBuffer();
        // changes each letter to its morse code equivalent
        for(int i = 0; i < sentence.length; i++){
            for(int j = 0; j < letters.length; j ++) {
                if(sentence[i] == letters[j]){
                    buff.append(morse[j]);
                }
            }
        }
        String output = buff.toString();
        return output;
    }

    public static void main(String[] args) {
        StringManip example = new StringManip();

        System.out.println(example.countInstances("whilehihhils", "hi"));
        System.out.println(example.removeSpaces("hell o wo rld"));
        System.out.println(example.isPalindrome("stanleyyelnats"));
        System.out.println(example.isPalindrome("helloworld"));
        System.out.println(example.toMorse("hello world"));
    }

}