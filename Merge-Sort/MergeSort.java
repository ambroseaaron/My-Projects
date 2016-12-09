/**
 * MergeSort class that sorts a given array using the merge sort algorithm
 * @author Aaron Ambrose
 */
public class MergeSort {
    int[] array;

    public MergeSort(int[] array) {
        this.array = array;
    }

    /**
     * Recursive mergeSort that splits the array and merges the two halves
     * @param low lowest element in the array
     * @param high highest element in the array
     */
    public void mergeSort(int low, int high) {
        // sort arr[low, high-1]
        // Check if low is smaller then high, if not then the array is sorted
        if (low < high-1) {
            // Get the index of the element which is in the middle
            int middle = (high + low) / 2;
            // Sort the left side of the array
            mergeSort(low, middle);
            // Sort the right side of the array
            mergeSort(middle, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    /**
     * Merge method that merges the two sorted halves into one array
     * @param low lowest element in the array
     * @param middle middle element that splits the two halves
     * @param high highest element in the array
     */
    public void merge(int low, int middle, int high) {
        // merge arr[low, middle-1] and arr[middle, high-1] into arr[low, high-1]

        // Copy first part into the arrCopy array
        int[] mergeArr = new int[50];

        for (int i = low; i < middle; i++) mergeArr[i] = array[i];

        int i = low;
        int j = middle;
        int k = low;

        // Copy the smallest values from either the left or the right side back        // to the original array
        while (i < middle && j < high)
            if (mergeArr[i] <= array[j])
                array[k++] = mergeArr[i++];
            else
                array[k++] = array[j++];

        // Copy the rest of the left part of the array into the original array
        while (i < middle) array[k++] = mergeArr[i++];
    }
}
