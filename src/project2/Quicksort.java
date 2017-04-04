//Charles Justin Reusnow • CS 3130

package project2;

import java.util.Arrays;
import java.util.Random;

public class Quicksort {
    private static final int RANDOM = 0;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;
    private static final int ORIGINAL = 0;
    private static final int RANDOMIZATION = 1;
    private static final int MEDIAN_OF_3 = 2;
    private static final int MEDIAN_OF_5 = 3;
    private static final int LEFT_BOUND = 0;
    private static final int RIGHT_BOUND = 1;

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

        quicksortArrays[ORIGINAL][RANDOM] = quicksortOriginal(quicksortArrays[ORIGINAL][RANDOM]);

        displayArrayData(quicksortArrays[ORIGINAL][RANDOM]);

        System.out.println(arrayIsSortedInAscendingOrder(quicksortArrays[ORIGINAL][RANDOM]));
    }

    private static int[] quicksortOriginal(int[] array) {
        return quicksortOriginal(array, new int[]{0, array.length - 1});
    }

    private static int[] quicksortOriginal(int[] array, int[] bounds) {
        int temp;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Pick a pivot.
        int pivot = array[rightBound]; //Pivot is last element in array.
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //2. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
        }

        //3. Insert Pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //4. Repeat for lesserValues subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        //5. Repeat for greaterValues subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        return array;
    }

    private static int[] quicksortRandomization(int[] array) {
        return quicksortRandomization(array, new int[]{0, array.length - 1});
    }

    private static int[] quicksortRandomization(int[] array, int[] bounds) {
        int temp;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Pick a pivot randomly.
        int randomIndex = (int) (Math.random() * (rightBound - leftBound + 1)) + leftBound;
        temp = array[randomIndex];
        array[randomIndex] = array[rightBound];
        array[rightBound] = temp;

        int pivot = array[rightBound];
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //2. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
        }

        //3. Insert Pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //4. Repeat for lesserValues subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        //5. Repeat for greaterValues subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        return array;
    }

    private static int[] quicksortMedianOf3(int[] array) {
        return quicksortMedianOf3(array, new int[]{0, array.length - 1});
    }

    private static int[] quicksortMedianOf3(int[] array, int[] bounds) {
        int temp;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Pick a pivot.
        if (rightBound - leftBound > 2) {
            final int[] randomIndices = new Random().ints(leftBound, rightBound + 1).distinct().limit(3).toArray();
            int medianOf3Index = medianOf3Index(array, randomIndices);

            temp = array[medianOf3Index];
            array[medianOf3Index] = array[rightBound];
            array[rightBound] = temp;
        }

        int pivot = array[rightBound]; //Pivot is last element in array.
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //2. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
        }

        //3. Insert Pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //4. Repeat for lesserValues subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        //5. Repeat for greaterValues subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        return array;
    }

    private static int[] quicksortMedianOf5(int[] array) {
        return quicksortMedianOf5(array, new int[]{0, array.length - 1});
    }

    private static int[] quicksortMedianOf5(int[] array, int[] bounds) {
        int temp;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Pick a pivot.
        if (rightBound - leftBound > 4) {
            final int[] randomIndices = new Random().ints(leftBound, rightBound + 1).distinct().limit(5).toArray();
            int medianOf5Index = medianOf5Index(array, randomIndices);

            temp = array[medianOf5Index];
            array[medianOf5Index] = array[rightBound];
            array[rightBound] = temp;
        }

        int pivot = array[rightBound]; //Pivot is last element in array.
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //2. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
        }

        //3. Insert Pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //4. Repeat for lesserValues subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        //5. Repeat for greaterValues subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            array = quicksortOriginal(array, bounds);
        }

        return array;
    }

    private static int medianOf3Index(int[] array, int[] randomIndices) {
        int[] elements = new int[]{array[randomIndices[0]], array[randomIndices[1]], array[randomIndices[2]]};

        final int A = 0;
        final int B = 1;
        final int C = 2;

        int medianOf3Index;

        if (elements[A] < elements[B]) { //1
            if (elements[B] < elements[C]) { //2
                medianOf3Index = B;
            } else {
                if (elements[A] < elements[C]) { //3
                    medianOf3Index = C;
                } else {
                    medianOf3Index = A;
                }
            }
        } else {
            if (elements[B] < elements[C]) { //2
                if (elements[A] < elements[C]) { //3
                    medianOf3Index = A;
                } else {
                    medianOf3Index = C;
                }
            } else {
                medianOf3Index = B;
            }
        }

        return randomIndices[medianOf3Index];
    }

    private static int medianOf5Index(int[] array, int[] randomIndices) {
        int[] elements = new int[]{array[randomIndices[0]], array[randomIndices[1]], array[randomIndices[2]], array[randomIndices[3]], array[randomIndices[4]]};

        final int A = 0;
        final int B = 1;
        final int C = 2;
        final int D = 3;
        final int E = 4;

        int medianOf5Index;

        if (elements[A] > elements[B]) { //1
            int tmp = elements[A];
            elements[A] = elements[B];
            elements[B] = tmp;
        }

        if (elements[C] > elements[D]) { //2
            int tmp = elements[C];
            elements[C] = elements[D];
            elements[D] = tmp;
        }

        if (elements[A] > elements[C]) { //3
            int tmp = elements[B];
            elements[B] = elements[D];
            elements[D] = tmp;

            elements[C] = elements[A];
        }

        elements[A] = elements[E];

        if (elements[A] > elements[B]) { //4
            int tmp = elements[A];
            elements[A] = elements[B];
            elements[B] = tmp;
        }

        if (elements[C] > elements[A]) { //5
            int tmp = elements[B];
            elements[B] = elements[D];
            elements[D] = tmp;

            elements[A] = elements[C];
        }

        if (elements[D] > elements[A]) { //6
            medianOf5Index = A;
        } else {
            medianOf5Index = D;
        }

        return randomIndices[medianOf5Index];
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

    //This is an extra function I added to help ensure data integrity. It checks that the passed int array is arrayIsSortedInAscendingOrder in ascending order.
    private static boolean arrayIsSortedInAscendingOrder(final int[] array) {
        for (int index = 1; index < array.length; index++) { //For every pair of indices in the int array (sequentially)...
            if (array[index - 1] > array[index]) { //If the first int in this pair is greater than the second int in this pair...
                return false; //Then this array is not arrayIsSortedInAscendingOrder in ascending order. Return false.
            }
        }

        //If we made it here...
        return true; //Then this array is sorted in ascending order. Return true.
    }
}
