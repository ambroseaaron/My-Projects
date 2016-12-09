/**
 * MergeSortTest that creates a random array of given size
 * and sorts it using mergeSort
 * @author Aaron Ambrose
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class MergeSortTest {
    public static void main(String[] args) {

        BufferedReader read;
        Random randomGenerator;
        int size = 0;
        int random;
        int[] array = new int[50];

        read = new BufferedReader(new InputStreamReader(System.in));

        randomGenerator = new Random();

        try {
            System.out.print("Please enter the array size : ");
            size = Integer.parseInt(read.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Array to be sorted: ");

        // fill array
        random = size * 10;
        for (int i = 0; i < size; i++) {
            array[i] = randomGenerator.nextInt(random);
            System.out.print(array[i]);
            System.out.print(" ");
        }

        System.out.println();

        MergeSort sort = new MergeSort(array);
        System.out.println("Merge Sorting Array...");
        sort.mergeSort(0,size);
        System.out.println("Array after sorted: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }
}
