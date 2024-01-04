import java.util.Arrays;

// Here we sort elements in arr1 such that the order of the elements in arr1 is the same as in arr2. Elements that are in
// arr1 but not in arr2 are put at the end. Consider that each elem in arr2 occurs once and every elem is contained in arr1
public class otherSort {
    public static void main(String[] args) {
        int[] arr1 = new int[] {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = new int[] {2, 1, 4, 3, 9, 6};
        int[] sortedArr = Sort(arr1, arr2);
        System.out.println(Arrays.toString(sortedArr));
    }

    public static int[] Sort(int[] arr1, int[] arr2) {
        int[] arr3 = new int[arr1.length];
        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    arr3[index++] = arr2[i];
                }
            }
        }
        for (int i = 0; i < arr1.length; i++) { //to add the elements that weren't in arr2
            boolean found = false;
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                arr3[index++] = arr1[i];
            }
        }
        return arr3;
    }
}
