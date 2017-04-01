//Charles Justin Reusnow • CS 3130

package project2;

import java.util.Arrays;

public class asdf {
    private static final int RANDOM = 0;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;
    private static final int ORIGINAL = 0;
    private static final int RANDOMIZATION = 1;
    private static final int MEDIAN_OF_3 = 2;
    private static final int MEDIAN_OF_5 = 3;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public static void main(String[] args) {
        int[] randomIntArray = generateArrayData("random");
        int[] ascendingIntArray = generateArrayData("ascending");
        int[] descendingIntArray = generateArrayData("descending");

        int[][] originalQuicksortArrays = new int[][]{randomIntArray, ascendingIntArray, descendingIntArray};
        int[][] randomizationQuicksortArrays = Arrays.copyOf(originalQuicksortArrays, originalQuicksortArrays.length);
        int[][] medianOf3QuicksortArrays = Arrays.copyOf(originalQuicksortArrays, originalQuicksortArrays.length);
        int[][] medianOf5QuicksortArrays = Arrays.copyOf(originalQuicksortArrays, originalQuicksortArrays.length);

        int[][][] quicksortArrays = new int[][][]{originalQuicksortArrays, randomizationQuicksortArrays, medianOf3QuicksortArrays, medianOf5QuicksortArrays};

        displayArrayData(quicksortArrays[ORIGINAL][RANDOM]);

        quicksortArrays[ORIGINAL][RANDOM] = quicksortOriginal(quicksortArrays[ORIGINAL][RANDOM], 0, quicksortArrays[ORIGINAL][RANDOM].length - 1);

        displayArrayData(quicksortArrays[ORIGINAL][RANDOM]);
    }

    private static int[] quicksortOriginal(int[] array, int leftIndex, int rightIndex) {
        //1. Pick a pivot.
        int pivot = array[rightIndex]; //Pivot is last element in array.
        int[] left = new int[]{leftIndex, leftIndex};
        int[] right = new int[]{leftIndex, leftIndex};

        //2. Partition the array.
        for (int i = leftIndex; i < rightIndex - 1; i++) {
            if (array[i] < pivot) {
                int tmp = array[right[LEFT]];
                array[right[LEFT]] = array[right[RIGHT]];
                array[right[RIGHT]] = tmp;

                left[RIGHT]++;
                right[LEFT]++;
                right[RIGHT]++;
            } else {
                right[RIGHT]++;
            }
        }

        //3. Insert Pivot.
        int tmp = array[rightIndex];
        array[rightIndex] = array[right[LEFT]];
        array[right[LEFT]] = tmp;

        right[LEFT]++;
        right[RIGHT]++;

        //4. Repeat for left subarray recursively.
        if (left[RIGHT] - left[LEFT] > 1) {
            array = quicksortOriginal(array, left[LEFT], left[RIGHT] - 1);
        }

        //5. Repeat for right subarray recursively.
        if (right[RIGHT] - right[LEFT] > 1) {
            array = quicksortOriginal(array, right[LEFT], right[RIGHT] - 1);
        }

        return array;
    }

    private static int[] quicksortRandomization(int[] array) {
        return array;
    }

    private static int[] quicksortMedianOf3(int[] array) {
        return array;
    }

    private static int[] quicksortMedianOf5(int[] array) {
        return array;
    }

    //This function is responsible for randomly generating the data. All hard coded values are per the project specification.
    private static int[] generateArrayData(String option) {
        final int[] array = new int[1000]; //Create a new int array of size 1000.

        switch (option) {
            case "random":
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = (int) (1 + Math.random() * 1000); //Generate a random number between 1 and 1000 inclusive and store it in the int array at the current index.
                }
                break;
            case "ascending":
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = index + 1; //Generate a the number corresponding to the current index + 1 to create an array in ascending order and store it in the int array at the current index.
                }
                break;
            case "descending":
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = 1000 - index; //Generate the number corresponding to 1000 - the current index to create an array in descending order and store it in the int array at the current index.
                }
                break;
            default:
                throw new RuntimeException("Error: \"" + option + "\" is an unrecognized array generator option!");
        }

        return array; //Return the now randomly populated int array.
    }

    //This function is responsible for outputting the data to the user in a meaningful way. Hardcoded values are per the project specification.
    private static void displayArrayData(final int[] array) {
        StringBuilder output; //This is the object responsible for creating the String that will ultimately be displayed to the user.
        int rowCounter = 0; //This variable is used to display the multiples of 20 along the rows.

        output = new StringBuilder("================================================= ARRAY =================================================\n"); //Attach an upper border.
        output.append("INDEX {00} {01} {02} {03} {04} {05} {06} {07} {08} {09} {10} {11} {12} {13} {14} {15} {16} {17} {18} {19}\n"); //Append a basic index track along the top to keep track of the column number.

        for (int rowNumber = 0; rowNumber < 50; rowNumber++) { //For every row to be displayed (50 rows)...
            StringBuilder rowCounterAsString = new StringBuilder(String.valueOf(rowCounter)); //Create a new StringBuilder around the string value of the rowCounter variable (will be used for padding to keep things square).
            int lengthOfRowCounterString = rowCounterAsString.length(); //Record the initial length of the rowCounterString (because it changes as we pad it, and that messes with the for loop).

            for (int paddingCharacterIndex = 0; paddingCharacterIndex < "###".length() - lengthOfRowCounterString; paddingCharacterIndex++) { //For every character in the rowCounterAsString variable LESS than the length of a String with 3 characters...
                rowCounterAsString.insert(0, "0"); //Pad the front of the String with a 0 (This makes "0" -> "000", "80" -> "080", and "200" -> "200" to keep everything the same length).
            }

            output.append("{").append(rowCounterAsString).append("} "); //Attach the row counter String to the front of the current row.

            rowCounter += 20; //Increment the rowCounter variable by 20 (since we have moved on to the next row).

            for (int columnNumber = 0; columnNumber < 20; columnNumber++) { //For every column to be displayed (20 columns)...
                StringBuilder cellValueAsString = new StringBuilder(String.valueOf(array[20 * rowNumber + columnNumber])); //Create a new StringBuilder around the String value of the current int to be displayed (will be used for padding to keep things square).
                int lengthOfCellValueString = cellValueAsString.length(); //Record the initial length of the cellValueString (because it changes as we pad it, and that messes with the for loop).

                for (int paddingCharacterIndex = 0; paddingCharacterIndex < "####".length() - lengthOfCellValueString; paddingCharacterIndex++) { //For every character in the cellValueAsString variable LES than the length of a String with 4 characters...
                    cellValueAsString.insert(0, " "); //Pad the front of the Sting with a " " (This makes "0" -> "   0", "378" -> " 378", and "1000" -> "1000" to keep everything the same length).
                }

                output.append(cellValueAsString).append(" "); //Attach the cellValue to the current String.
            }

            output.append("\n"); //Add a new line at the end of this row.
        }

        output.append("========================================================================================================="); //Attach a lower border.

        System.out.println(output.toString()); //Output the completed String to the user.
    }
}
