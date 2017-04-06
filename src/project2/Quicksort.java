//Charles Justin Reusnow • CS 3130

package project2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

//This is the main class of the program which facilitates the various sorts.
public class Quicksort {
    /* The way I have the data set up is in a series of 1D and 2D arrays, and those can be hard to keep track of with numbers alone. So to make things more readable, when accessing
     * data in these arrays, I use these variables as index accessors instead of straight integers to help make it more human readable. */
    private static final int RANDOM = 0; //Randomly ordered array is first.
    private static final int ASCENDING = 1; //Ascending order array is second.
    private static final int DESCENDING = 2; //Descending order array is third.

    private static final int ORIGINAL = 0; //Arrays for the original quicksort come first.
    private static final int RANDOMIZATION = 1; //Arrays for the randomization quicksort come second.
    private static final int MEDIAN_OF_3 = 2; //Arrays for the median of 3 quicksort come third.
    private static final int MEDIAN_OF_5 = 3; //Arrays for the median of 5 quicksort come fourth.

    private static final int LEFT_BOUND = 0; //Left bound is first in the bounds array.
    private static final int RIGHT_BOUND = 1; //Right bound is second in the bounds array.

    //Main function.
    public static void main(String[] args) {
        //GENERATE DATA

        //Random array data.
        int[] randomIntArray = generateArrayData("random"); //Generate the random array values.
        int randomIntArrayComparisons = 0; //Initialize the comparisons counter to 0.
        int randomIntArraySwaps = 0; //Initialize the swaps counter to 0.
        long randomIntArrayDuration = 0; //Initialize the duration counter to 0.

        //Ascending array data.
        int[] ascendingIntArray = generateArrayData("ascending"); //Generate the ascending array values.
        int ascendingIntArrayComparisons = 0; //Repeat the process from above.
        int ascendingIntArraySwaps = 0;
        long ascendingIntArrayDuration = 0;

        //Descending array data.
        int[] descendingIntArray = generateArrayData("descending"); //Generate the descending array values.
        int descendingIntArrayComparisons = 0; //Repeat the process from above.
        int descendingIntArraySwaps = 0;
        long descendingIntArrayDuration = 0;

        //Original quicksort arrays.
        int[][] originalQuicksortArrays = new int[][]{randomIntArray, ascendingIntArray, descendingIntArray}; //Create an array to hold the 3 types of arrays.
        int[] originalQuicksortComparisons = new int[]{randomIntArrayComparisons, ascendingIntArrayComparisons, descendingIntArrayComparisons}; //Create an array to hold the 3 comparison counters.
        int[] originalQuicksortSwaps = new int[]{randomIntArraySwaps, ascendingIntArraySwaps, descendingIntArraySwaps}; //Create an array to hold the 3 swaps counters.
        long[] originalQuicksortDurations = new long[]{randomIntArrayDuration, ascendingIntArrayDuration, descendingIntArrayDuration}; //Create an array to hold the 3 duration counters.

        //Randomization quicksort arrays.
        int[][] randomizationQuicksortArrays = new int[][]{Arrays.copyOf(randomIntArray, randomIntArray.length), Arrays.copyOf(ascendingIntArray, ascendingIntArray.length), Arrays.copyOf(descendingIntArray, descendingIntArray.length)}; //Create an array to hold COPIES of the 3 types of arrays (avoids pass by reference issues).
        int[] randomizationQuicksortComparisons = Arrays.copyOf(originalQuicksortComparisons, originalQuicksortComparisons.length); //Create a COPY of the array containing the 3 comparisons counters for the original quicksort.
        int[] randomizationQuicksortSwaps = Arrays.copyOf(originalQuicksortSwaps, originalQuicksortSwaps.length); //Create a COPY of the array containing the 3 swap counters for the original quicksort.
        long[] randomizationQuicksortDurations = Arrays.copyOf(originalQuicksortDurations, originalQuicksortDurations.length); //Create a COPY of the array containing the 3 duration counters for the original quicksort.

        //Median of 3 quicksort arrays.
        int[][] medianOf3QuicksortArrays = new int[][]{Arrays.copyOf(randomIntArray, randomIntArray.length), Arrays.copyOf(ascendingIntArray, ascendingIntArray.length), Arrays.copyOf(descendingIntArray, descendingIntArray.length)}; //Repeat the same process from above.
        int[] medianOf3QuicksortComparisons = Arrays.copyOf(originalQuicksortComparisons, originalQuicksortComparisons.length);
        int[] medianOf3QuicksortSwaps = Arrays.copyOf(originalQuicksortSwaps, originalQuicksortSwaps.length);
        long[] medianOf3QuicksortDurations = Arrays.copyOf(originalQuicksortDurations, originalQuicksortDurations.length);

        //Median of 5 quicksort arrays.
        int[][] medianOf5QuicksortArrays = new int[][]{Arrays.copyOf(randomIntArray, randomIntArray.length), Arrays.copyOf(ascendingIntArray, ascendingIntArray.length), Arrays.copyOf(descendingIntArray, descendingIntArray.length)}; //Repeat the same process from above.
        int[] medianOf5QuicksortComparisons = Arrays.copyOf(originalQuicksortComparisons, originalQuicksortComparisons.length);
        int[] medianOf5QuicksortSwaps = Arrays.copyOf(originalQuicksortSwaps, originalQuicksortSwaps.length);
        long[] medianOf5QuicksortDurations = Arrays.copyOf(originalQuicksortDurations, originalQuicksortDurations.length);

        //QuicksortObject array.
        QuicksortObject[][] quicksortObjects = new QuicksortObject[][]{ //Create a 2D QuicksortObject array to hold information for each quicksort implementation.
                new QuicksortObject[]{ //Create a 1D QuicksortObject array to hold a QuicksortObject for each type of array (random, ascending , and descending).
                        new QuicksortObject("Original Quicksort on Random Array", Arrays.copyOf(originalQuicksortArrays[RANDOM], originalQuicksortArrays[RANDOM].length), originalQuicksortComparisons[RANDOM], originalQuicksortSwaps[RANDOM], originalQuicksortDurations[RANDOM]), //Create a new QuicksortObject for the random array data
                        new QuicksortObject("Original Quicksort on Array in Ascending Order", Arrays.copyOf(originalQuicksortArrays[ASCENDING], originalQuicksortArrays[ASCENDING].length), originalQuicksortComparisons[ASCENDING], originalQuicksortSwaps[ASCENDING], originalQuicksortDurations[ASCENDING]), //Create a new QuicksortObject for the ascending array data.
                        new QuicksortObject("Original Quicksort on Array in Descending Order", Arrays.copyOf(originalQuicksortArrays[DESCENDING], originalQuicksortArrays[DESCENDING].length), originalQuicksortComparisons[DESCENDING], originalQuicksortSwaps[DESCENDING], originalQuicksortDurations[DESCENDING]) //Create a new QuicksortObject for the descending array data.
                },
                new QuicksortObject[]{
                        new QuicksortObject("Randomization Quicksort on Random Array", Arrays.copyOf(randomizationQuicksortArrays[RANDOM], randomizationQuicksortArrays[RANDOM].length), randomizationQuicksortComparisons[RANDOM], randomizationQuicksortSwaps[RANDOM], randomizationQuicksortDurations[RANDOM]), //Repeat the process above.
                        new QuicksortObject("Randomization Quicksort on Array in Ascending Order", Arrays.copyOf(randomizationQuicksortArrays[ASCENDING], randomizationQuicksortArrays[ASCENDING].length), randomizationQuicksortComparisons[ASCENDING], randomizationQuicksortSwaps[ASCENDING], randomizationQuicksortDurations[ASCENDING]),
                        new QuicksortObject("Randomization Quicksort on Array in Descending Order", Arrays.copyOf(randomizationQuicksortArrays[DESCENDING], randomizationQuicksortArrays[DESCENDING].length), randomizationQuicksortComparisons[DESCENDING], randomizationQuicksortSwaps[DESCENDING], randomizationQuicksortDurations[DESCENDING])
                },
                new QuicksortObject[]{
                        new QuicksortObject("Median of 3 Quicksort on Random Array", Arrays.copyOf(medianOf3QuicksortArrays[RANDOM], medianOf3QuicksortArrays[RANDOM].length), medianOf3QuicksortComparisons[RANDOM], medianOf3QuicksortSwaps[RANDOM], medianOf3QuicksortDurations[RANDOM]), //Repeat the process above.
                        new QuicksortObject("Median of 3 Quicksort on Array in Ascending Order", Arrays.copyOf(medianOf3QuicksortArrays[ASCENDING], medianOf3QuicksortArrays[ASCENDING].length), medianOf3QuicksortComparisons[ASCENDING], medianOf3QuicksortSwaps[ASCENDING], medianOf3QuicksortDurations[ASCENDING]),
                        new QuicksortObject("Median of 3 Quicksort on Array in Descending Order", Arrays.copyOf(medianOf3QuicksortArrays[DESCENDING], medianOf3QuicksortArrays[DESCENDING].length), medianOf3QuicksortComparisons[DESCENDING], medianOf3QuicksortSwaps[DESCENDING], medianOf3QuicksortDurations[DESCENDING])
                },
                new QuicksortObject[]{
                        new QuicksortObject("Median of 5 Quicksort on Random Array", Arrays.copyOf(medianOf5QuicksortArrays[RANDOM], medianOf5QuicksortArrays[RANDOM].length), medianOf5QuicksortComparisons[RANDOM], medianOf5QuicksortSwaps[RANDOM], medianOf5QuicksortDurations[RANDOM]), //Repeat the process above.
                        new QuicksortObject("Median of 5 Quicksort on Array in Ascending Order", Arrays.copyOf(medianOf5QuicksortArrays[ASCENDING], medianOf5QuicksortArrays[ASCENDING].length), medianOf5QuicksortComparisons[ASCENDING], medianOf5QuicksortSwaps[ASCENDING], medianOf5QuicksortDurations[ASCENDING]),
                        new QuicksortObject("Median of 5 Quicksort on Array in Descending Order", Arrays.copyOf(medianOf5QuicksortArrays[DESCENDING], medianOf5QuicksortArrays[DESCENDING].length), medianOf5QuicksortComparisons[DESCENDING], medianOf5QuicksortSwaps[DESCENDING], medianOf5QuicksortDurations[DESCENDING])
                },
        };

        //BEGIN SORTING

        System.out.println("Array containing 1000 randomly generated integers between 1 and 1000 (inclusive) used in each of the implementations of Quicksort:");
        displayArrayData(quicksortObjects[ORIGINAL][RANDOM]); //Display the random array before it is sorted so we can verify that it is, indeed, random.

        //ORIGINAL QUICKSORTS
        System.out.println("\n========== ORIGINAL QUICKSORT IMPLEMENTATION ==========");
        //RANDOM ARRAY
        introduce(quicksortObjects[ORIGINAL][RANDOM]); //Display information about this sort.
        timeQuicksortOriginal(quicksortObjects[ORIGINAL][RANDOM]); //Preform the sort.
        displayResultsFor(quicksortObjects[ORIGINAL][RANDOM]); //Display results for the sort.
        displayArrayData(quicksortObjects[ORIGINAL][RANDOM]); //Show the sorted array.
        //ASCENDING ARRAY
        introduce(quicksortObjects[ORIGINAL][ASCENDING]); //Same process as above.
        timeQuicksortOriginal(quicksortObjects[ORIGINAL][ASCENDING]);
        displayResultsFor(quicksortObjects[ORIGINAL][ASCENDING]);
        displayArrayData(quicksortObjects[ORIGINAL][ASCENDING]);
        //DESCENDING ARRAY
        introduce(quicksortObjects[ORIGINAL][DESCENDING]); //Same process as above.
        timeQuicksortOriginal(quicksortObjects[ORIGINAL][DESCENDING]);
        displayResultsFor(quicksortObjects[ORIGINAL][DESCENDING]);
        displayArrayData(quicksortObjects[ORIGINAL][DESCENDING]);

        //RANDOMIZATION QUICKSORTS
        System.out.println("\n========== RANDOMIZATION QUICKSORT IMPLEMENTATION ==========");
        //RANDOM ARRAY
        introduce(quicksortObjects[RANDOMIZATION][RANDOM]); //Same process as original quicksort.
        timeQuicksortRandomization(quicksortObjects[RANDOMIZATION][RANDOM]);
        displayResultsFor(quicksortObjects[RANDOMIZATION][RANDOM]);
        displayArrayData(quicksortObjects[RANDOMIZATION][RANDOM]);
        //ASCENDING ARRAY
        introduce(quicksortObjects[RANDOMIZATION][ASCENDING]);
        timeQuicksortRandomization(quicksortObjects[RANDOMIZATION][ASCENDING]);
        displayResultsFor(quicksortObjects[RANDOMIZATION][ASCENDING]);
        displayArrayData(quicksortObjects[RANDOMIZATION][ASCENDING]);
        //DESCENDING ARRAY
        introduce(quicksortObjects[RANDOMIZATION][DESCENDING]);
        timeQuicksortRandomization(quicksortObjects[RANDOMIZATION][DESCENDING]);
        displayResultsFor(quicksortObjects[RANDOMIZATION][DESCENDING]);
        displayArrayData(quicksortObjects[RANDOMIZATION][DESCENDING]);

        //MEDIAN OF 3 QUICKSORTS
        System.out.println("\n========= MEDIAN OF 3 QUICKSORT IMPLEMENTATION ==========");
        /* The following line is the nicest solution I could find for a massive bug that plagues me to this day. I do not understand the behavior observed, but this is the best I can do
         * to resolve it. If you were to uncomment the following line of code, the next segment's timer will be incorrect by orders of magnitude. It has me absolutely dumbfounded. Go
         * ahead and try it if you feel so obliged. When you comment the line out and run the program, the duration section of the results will list "Median of 3 Quicksort on Random
         * Array" as having a time ~15 times worse than the next worse time. Uncomment the line and you will find this issue is resolved. It makes no sense. It appears to be a bug in
         * Java or the System itself, because when you shift these blocks of 4 lines of code around, the bug attaches to whichever block of code would have been following this comment
         * (the 7th block to execute). The reason I believe that this line fixes this issue is because it really doesn't, it simply takes advantage of the fact that the bug shifts to
         * whichever quicksort block is executing, so this mimics a quicksort block by doing a sort on a dummy QuicksortObject to appease the bug, then moving on bug-free. */
        timeQuicksortMedianOf3(new QuicksortObject());
        //RANDOM ARRAY
        introduce(quicksortObjects[MEDIAN_OF_3][RANDOM]); //Same process as original quicksort.
        timeQuicksortMedianOf3(quicksortObjects[MEDIAN_OF_3][RANDOM]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_3][RANDOM]);
        displayArrayData(quicksortObjects[MEDIAN_OF_3][RANDOM]);
        //ASCENDING ARRAY
        introduce(quicksortObjects[MEDIAN_OF_3][ASCENDING]);
        timeQuicksortMedianOf3(quicksortObjects[MEDIAN_OF_3][ASCENDING]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_3][ASCENDING]);
        displayArrayData(quicksortObjects[MEDIAN_OF_3][ASCENDING]);
        //DESCENDING ARRAY
        introduce(quicksortObjects[MEDIAN_OF_3][DESCENDING]);
        timeQuicksortMedianOf3(quicksortObjects[MEDIAN_OF_3][DESCENDING]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_3][DESCENDING]);
        displayArrayData(quicksortObjects[MEDIAN_OF_3][DESCENDING]);

        //MEDIAN OF 5 QUICKSORTS
        System.out.println("\n========== MEDIAN OF 5 QUICKSORT IMPLEMENTATION ==========");
        //RANDOM ARRAY
        introduce(quicksortObjects[MEDIAN_OF_5][RANDOM]); //Same process as original quicksort.
        timeQuicksortMedianOf5(quicksortObjects[MEDIAN_OF_5][RANDOM]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_5][RANDOM]);
        displayArrayData(quicksortObjects[MEDIAN_OF_5][RANDOM]);
        //ASCENDING ARRAY
        introduce(quicksortObjects[MEDIAN_OF_5][ASCENDING]);
        timeQuicksortMedianOf5(quicksortObjects[MEDIAN_OF_5][ASCENDING]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_5][ASCENDING]);
        displayArrayData(quicksortObjects[MEDIAN_OF_5][ASCENDING]);
        //DESCENDING ARRAY
        introduce(quicksortObjects[MEDIAN_OF_5][DESCENDING]);
        timeQuicksortMedianOf5(quicksortObjects[MEDIAN_OF_5][DESCENDING]);
        displayResultsFor(quicksortObjects[MEDIAN_OF_5][DESCENDING]);
        displayArrayData(quicksortObjects[MEDIAN_OF_5][DESCENDING]);

        displayTotalResults(quicksortObjects); //Output total results for all quicksorts.
    }

    //Entry point for the original quicksort.
    private static void timeQuicksortOriginal(QuicksortObject quicksortObject) {
        quicksortObject.duration = System.nanoTime(); //Start the timer.
        quicksortOriginal(quicksortObject, new int[]{0, quicksortObject.array.length - 1}); //Do the sort, set the left bound to 0, and right bound to the size of the array.
        quicksortObject.duration = System.nanoTime() - quicksortObject.duration; //Stop the timer and calculate the duration.
    }

    //The original implementation of quicksort.
    private static void quicksortOriginal(QuicksortObject quicksortObject, int[] bounds) {
        //0. Variable setup.
        int temp; //Temporary variable used for swapping elements.
        int[] array = quicksortObject.array; //The array. This variable is just to save keystrokes and improve readability.
        int leftBound = bounds[LEFT_BOUND]; //The left bound of the array, again for readability.
        int rightBound = bounds[RIGHT_BOUND]; //The right bound of the array, again for readability.

        //1. Determine pivot.
        //In original quicksort, pivot is always the right most element, so this step is already complete.

        //2. Prepare pivot.
        int pivot = array[rightBound];
        int[] lesserValues = new int[]{leftBound, leftBound}; //This is the less-than-pivot subarray, it starts as {left, left} and grows to {left, left + n} where n is the number of elements less than the pivot.
        int[] greaterValues = new int[]{leftBound, leftBound}; //This is the greater-than-pivot subarray, it starts at {left, left} and grows to {left + n + 1, left + n + 1 + m} where n is the number of elements less than the pivot, and m is the number of elements greater than the pivot.

        //3. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) { //For every element in the array from leftBound to rightBound - 1 inclusive...
            if (array[currentIndex] < pivot) { //If the current element is less than the pivot...
                temp = array[greaterValues[LEFT_BOUND]]; //Swap the left-most element in the greater-than-pivot subarray with the current element.
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;
                quicksortObject.swaps++; //That was a swap, so increment.

                lesserValues[RIGHT_BOUND]++; //An element was added to the less-than-pivot subarray, so this subarray grew, so extend its right bound by 1.
                greaterValues[LEFT_BOUND]++; //If the less-than-pivot subarray grows, it pushes the greater-than-pivot subarray over 1 space, so increase both bounds by 1.
                greaterValues[RIGHT_BOUND]++;
            } else { //If the current element is greater than or equal to the pivot...
                greaterValues[RIGHT_BOUND]++; //An element is to be added to the greater-than-pivot subarray, so this subarray grew, so extend its right bound by 1.
                //This does not affect the less-than-pivot subarray.
            }
            quicksortObject.comparisons++; //Every iteration of the for loop executes a comparison, so increment the comparison counter.
        }

        //4. Insert the pivot.
        temp = array[rightBound]; //Swap the pivot with the left-mpost element in the greater-than-pivot subarray.
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;
        quicksortObject.swaps++; //This was a swap, so increment.

        greaterValues[RIGHT_BOUND]++; //Since we swapped the pivot with an element in the greater-than-pivot subarray, we need to adjust the bounds of the greater-than-pivot subarray, so increase both bounds by 1.
        greaterValues[LEFT_BOUND]++;

        //5. Repeat for less-than-pivot subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) { //If the size of the less-than-pivot subarray is greater than 1 element...
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1}; //This subarray also needs to be sorted, so set the bounds for it.
            quicksortOriginal(quicksortObject, bounds); //Then execute this same sorting routine on the subarray.
        }

        //6. Repeat for greater-than-pivot subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) { //If the isze of the greater-than-pivot subarray is greater than 1 element...
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1}; //This subarray also needs to be sorted, so set the bounds for it.
            quicksortOriginal(quicksortObject, bounds); //Then execute this same sorting routine on the subarray.
        }
    }

    //Entry point for the randomization quicksort.
    private static void timeQuicksortRandomization(QuicksortObject quicksortObject) {
        quicksortObject.duration = System.nanoTime(); //Same process as timeQuicksortOriginal.
        quicksortRandomization(quicksortObject, new int[]{0, quicksortObject.array.length - 1});
        quicksortObject.duration = System.nanoTime() - quicksortObject.duration;
    }

    //The randomization implementation of quicksort. Note that everything except for step 1 is the same as in original quicksort.
    private static void quicksortRandomization(QuicksortObject quicksortObject, int[] bounds) {
        //0. Variable setup.
        int temp;
        int[] array = quicksortObject.array;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Determine pivot.
        int randomIndex = (int) (Math.random() * (rightBound - leftBound + 1)) + leftBound; //Pick a random index from the array.
        temp = array[randomIndex]; //Swap the rightmost element with the element at the randomly chosen index.
        array[randomIndex] = array[rightBound];
        array[rightBound] = temp;
        quicksortObject.swaps++; //This is a swap.

        //2. Prepare pivot.
        int pivot = array[rightBound];
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //3. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;
                quicksortObject.swaps++;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
            quicksortObject.comparisons++;
        }

        //4. Insert the pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;
        quicksortObject.swaps++;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //5. Repeat for less-than-pivot subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            quicksortRandomization(quicksortObject, bounds);
        }

        //6. Repeat for greater-than-pivot subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            quicksortRandomization(quicksortObject, bounds);
        }
    }

    //The entry point for the median of 3 quicksort.
    private static void timeQuicksortMedianOf3(QuicksortObject quicksortObject) {
        quicksortObject.duration = System.nanoTime(); //Same process as timeQuicksortOriginal.
        quicksortMedianOf3(quicksortObject, new int[]{0, quicksortObject.array.length - 1});
        quicksortObject.duration = System.nanoTime() - quicksortObject.duration;
    }

    //The median of 3 implementation of quicksort. Note that everything except for step 1 is the same as in original quicksort.
    private static void quicksortMedianOf3(QuicksortObject quicksortObject, int[] bounds) {
        //0. Variable setup.
        int temp;
        int[] array = quicksortObject.array;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Determine pivot.
        if (rightBound - leftBound > 2) { //If there are at least three elements in the array of which to find a median of...
            final int[] randomIndices = new Random() //Create a random int array (representing random indices of the current array) using Java's random number generator...
                    .ints(leftBound, rightBound + 1) //Generate ints between the bounds...
                    .distinct() //Ensure they are distinct...
                    .limit(3) //Get 3 random ints...
                    .toArray(); //Send them to an array.
            int medianOf3Index = medianOf3Index(array, randomIndices, quicksortObject); //Determine the index of the median of the 3 values in the array at the randomly chosen indices.

            temp = array[medianOf3Index]; //Swap the rightmost element with the element at the medianOf3Index.
            array[medianOf3Index] = array[rightBound];
            array[rightBound] = temp;
            quicksortObject.swaps++; //This is a swap.
        } //If there are not at least three elements in the array, sort it as if this were original quicksort; assume the right most element as default pivot.

        //2. Prepare pivot.
        int pivot = array[rightBound];
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //3. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;
                quicksortObject.swaps++;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
            quicksortObject.comparisons++;
        }

        //4. Insert the pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;
        quicksortObject.swaps++;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //5. Repeat for less-than-pivot subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            quicksortMedianOf3(quicksortObject, bounds);
        }

        //6. Repeat for greater-than-pivot subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            quicksortMedianOf3(quicksortObject, bounds);
        }
    }

    //This function finds the index of the median of the 3 values at each of the randomIndices in the array in 3 comparisons and returns it.
    private static int medianOf3Index(int[] array, int[] randomIndices, QuicksortObject quicksortObject) {
        int[] elements = new int[]{array[randomIndices[0]], array[randomIndices[1]], array[randomIndices[2]]}; //Create a new array containing the 3 values (abstract away from the original array).

        //Helper variables to make the code more readable.
        final int A = 0;
        final int B = 1;
        final int C = 2;

        int medianOf3Index; //This will contain the index of the median of 3 of the elements array by the end.

        //If A > B...
        if (elements[A] > elements[B]) { //First comparison.
            int tmp = elements[A]; //Swap A & B
            elements[A] = elements[B];
            elements[B] = tmp;
            quicksortObject.swaps++; //This is a swap.
        }
        quicksortObject.comparisons++; //This is a comparison.

        //A is now < B

        //If C > B
        if (elements[C] > elements[B]) { //Second comparison
            elements[C] = elements[B]; //Eliminate the current value of C from the game (Not a swap, this is an overwrite).
        }
        quicksortObject.comparisons++; //This is a comparison.

        //C is now < B

        //If A > C
        if (elements[A] > elements[C]) { //Third comparison
            //C < A < B
            medianOf3Index = A; //A is the middle element, so A is the median of 3 index.
        } else {
            //A < C < B
            medianOf3Index = C; //C is the middle element, so C is the median of 3 index.
        }
        quicksortObject.comparisons++; //This is a comparison.

        //Find which of the randomIndices were the medianOf3Index (un-abstract back to the original array). This is needed because we are overwriting elements in the "elements" array to
        //aid our median algorithm, and thus we cannot depend solely on A, B, or C, we need to check that the array value matches the elements value.
        for (int i = 0; i < randomIndices.length; i++) { //For every random index in the randomIndices array...
            if (elements[medianOf3Index] == array[randomIndices[i]]) { //If the value of the array at the current random index equals the medianOf3 element...
                return randomIndices[i]; //This is the randomIndex corresponding to the median of 3.
            }
        }

        throw new RuntimeException("ERROR: Something went wrong with the Median of 3 function!"); //This should never happen.
    }

    //The entry point the for median of 5 quicksort.
    private static void timeQuicksortMedianOf5(QuicksortObject quicksortObject) {
        quicksortObject.duration = System.nanoTime(); //Same process as timeQuicksortOriginal.
        quicksortMedianOf5(quicksortObject, new int[]{0, quicksortObject.array.length - 1});
        quicksortObject.duration = System.nanoTime() - quicksortObject.duration;
    }

    //The median of 5 implementation of quicksort. Note that everything except for step 1 is the same as in original quicksort.
    private static void quicksortMedianOf5(QuicksortObject quicksortObject, int[] bounds) {
        //0. Variable setup.
        int temp;
        int[] array = quicksortObject.array;
        int leftBound = bounds[LEFT_BOUND];
        int rightBound = bounds[RIGHT_BOUND];

        //1. Determine pivot.
        if (rightBound - leftBound > 4) { //If there are at least five elements in the array of which to find a median of...
            final int[] randomIndices = new Random() //Create a random int array (representing random indices of the current array) using Java's random number generator...
                    .ints(leftBound, rightBound + 1) //Generate ints between the bounds...
                    .distinct() //Ensure they are distinct...
                    .limit(5) //Get 5 random ints...
                    .toArray(); //Send them to an array.
            int medianOf5Index = medianOf5Index(array, randomIndices, quicksortObject); //Determine the index of the median of the 5 values in the array at the randomly chosen indices.

            temp = array[medianOf5Index]; //Swap the rightmost element with the element at the medianOf5Index.
            array[medianOf5Index] = array[rightBound];
            array[rightBound] = temp;
            quicksortObject.swaps++; //This is a swap.
        } //If there are not at least five elements in the array, sort it as if this were original quicksort; assume the right most element as default pivot.

        //2. Prepare pivot.
        int pivot = array[rightBound];
        int[] lesserValues = new int[]{leftBound, leftBound};
        int[] greaterValues = new int[]{leftBound, leftBound};

        //3. Partition the array.
        for (int currentIndex = leftBound; currentIndex < rightBound; currentIndex++) {
            if (array[currentIndex] < pivot) {
                temp = array[greaterValues[LEFT_BOUND]];
                array[greaterValues[LEFT_BOUND]] = array[greaterValues[RIGHT_BOUND]];
                array[greaterValues[RIGHT_BOUND]] = temp;
                quicksortObject.swaps++;

                lesserValues[RIGHT_BOUND]++;
                greaterValues[LEFT_BOUND]++;
                greaterValues[RIGHT_BOUND]++;
            } else {
                greaterValues[RIGHT_BOUND]++;
            }
            quicksortObject.comparisons++;
        }

        //4. Insert the pivot.
        temp = array[rightBound];
        array[rightBound] = array[greaterValues[LEFT_BOUND]];
        array[greaterValues[LEFT_BOUND]] = temp;
        quicksortObject.swaps++;

        greaterValues[RIGHT_BOUND]++;
        greaterValues[LEFT_BOUND]++;

        //5. Repeat for less-than-pivot subarray recursively.
        if (lesserValues[RIGHT_BOUND] - lesserValues[LEFT_BOUND] > 1) {
            bounds = new int[]{lesserValues[LEFT_BOUND], lesserValues[RIGHT_BOUND] - 1};
            quicksortMedianOf5(quicksortObject, bounds);
        }

        //6. Repeat for greater-than-pivot subarray recursively.
        if (greaterValues[RIGHT_BOUND] - greaterValues[LEFT_BOUND] > 1) {
            bounds = new int[]{greaterValues[LEFT_BOUND], greaterValues[RIGHT_BOUND] - 1};
            quicksortMedianOf5(quicksortObject, bounds);
        }
    }

    private static int medianOf5Index(int[] array, int[] randomIndices, QuicksortObject quicksortObject) {
        int[] elements = new int[]{array[randomIndices[0]], array[randomIndices[1]], array[randomIndices[2]], array[randomIndices[3]], array[randomIndices[4]]}; //Create a new array containing the 5 values (abstract away from the original array).

        //Helper variables to make the code more readable.
        final int A = 0;
        final int B = 1;
        final int C = 2;
        final int D = 3;
        final int E = 4;

        int medianOf5Index; //This will contain the index of the median of 5 of the elements array by the end.

        //If A > B...
        if (elements[A] > elements[B]) { //First comparison.
            int tmp = elements[A]; //Swap A & B
            elements[A] = elements[B];
            elements[B] = tmp;
            quicksortObject.swaps++; //This is a swap.
        }
        quicksortObject.comparisons++; //This is a comparison.
        //A is now < B

        //If C > D...
        if (elements[C] > elements[D]) { //Second comparison.
            int tmp = elements[C]; //Swap C & D
            elements[C] = elements[D];
            elements[D] = tmp;
            quicksortObject.swaps++; //This is a swap.
        }
        quicksortObject.comparisons++; //This is a comparison.
        //C is now < D
        
        //If A > C...
        if (elements[A] > elements[C]) { //Third comparison.
            int tmp = elements[B]; //Swap B & D because A is now eliminated, but we need to keep D behind C.
            elements[B] = elements[D];
            elements[D] = tmp;
            quicksortObject.swaps++; //This is a swap.

            elements[C] = elements[A]; //A is now eliminated, it cannot be the median.
        }
        quicksortObject.comparisons++; //This is a comparison.
        //A is now < C which is < D

        //If E > B...
        if (elements[E] > elements[B]) { //Fourth comparison.
            int tmp = elements[E]; //Swap E & B
            elements[E] = elements[B];
            elements[B] = tmp;
            quicksortObject.swaps++; //This is a swap.
        }
        quicksortObject.comparisons++; //This is a comparison.
        //E is now < B

        //If C > E...
        if (elements[C] > elements[E]) { //Fifth comparison.
            int tmp = elements[B]; //Swap B & D because E is now eliminated, but we need to keep D behind C.
            elements[B] = elements[D];
            elements[D] = tmp;
            quicksortObject.swaps++; //This is a swap.

            elements[E] = elements[C];
        }
        quicksortObject.comparisons++; //This is a comparison.
        //C is now < E

        //At this point we have:
        //A < C < E ? D < B
        //So all thats left is to determine which of E or D is the middle index.

        //If D < E...
        if (elements[D] > elements[E]) { //Sixth comparison.
            //A < C < E < D < B
            medianOf5Index = E; //E is the median of 5 index.
        } else {
            //A < C < D < E < B
            medianOf5Index = D; //D is the median of 5 index.
        }
        quicksortObject.comparisons++; //This is a comparison.

        //Find which of the randomIndices were the medianOf5Index (un-abstract back to the original array). This is needed because we are overwriting elements in the "elements" array to
        //aid our median algorithm, and thus we cannot depend solely on A, B, C, D, or E, we need to check that the array value matches the elements value.
        for (int i = 0; i < randomIndices.length; i++) { //For every random index in the randomIndices array...
            if (elements[medianOf5Index] == array[randomIndices[i]]) { //If the value of the array at the current random index equals the medianOf5 element...
                return randomIndices[i]; //This is the randomIndex corresponding to the median of 5.
            }
        }

        throw new RuntimeException("ERROR: Something went wrong with the Median of 5 function!"); //This should never happen.
    }

    //This function is responsible for generating the data as desired. All hard coded values are per the project specification.
    private static int[] generateArrayData(String option) {
        final int[] array = new int[1000]; //Create a new int array of size 1000.

        switch (option) { //Examine the value of the option parameter...
            case "random": //If it is "random"...
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = (int) (1 + Math.random() * 1000); //Generate a random number between 1 and 1000 inclusive and store it in the int array at the current index.
                }
                break;
            case "ascending": //If it is "ascending"...
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = index + 1; //Generate a the number corresponding to the current index + 1 to create an array in ascending order and store it in the int array at the current index.
                }
                break;
            case "descending": //If it is "descending"...
                for (int index = 0; index < 1000; index++) { //For each index in the int array...
                    array[index] = 1000 - index; //Generate the number corresponding to 1000 - the current index to create an array in descending order and store it in the int array at the current index.
                }
                break;
            default: //Otherwise...
                throw new RuntimeException("Error: \"" + option + "\" is an unrecognized array generator option!"); //This should never happen if the program is being used as intended.
        }

        return array; //Return the now populated int array.
    }

    //This function is responsible for outputting the data to the user in a meaningful way. Hardcoded values are per the project specification.
    private static void displayArrayData(final QuicksortObject quicksortObject) {
        final int[] array = quicksortObject.array;

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
    private static boolean arrayIsSortedInAscendingOrder(final QuicksortObject quicksortObject) {
        final int[] array = quicksortObject.array;

        for (int index = 1; index < array.length; index++) { //For every pair of indices in the int array (sequentially)...
            if (array[index - 1] > array[index]) { //If the first int in this pair is greater than the second int in this pair...
                return false; //Then this array is not arrayIsSortedInAscendingOrder in ascending order. Return false.
            }
        }

        //If we made it here...
        return true; //Then this array is sorted in ascending order. Return true.
    }

    //This function displays the data in a QuicksortObject after it has had its array sorted.
    private static void displayResultsFor(QuicksortObject quicksortObject) {
        System.out.println(" done.");
        System.out.println("Results:");
        System.out.println("----- Comparisons: " + quicksortObject.comparisons);
        System.out.println("----- Swaps: " + quicksortObject.swaps);
        System.out.println("----- Duration: " + quicksortObject.duration / 1000000.0); //For milliseconds.
        System.out.println("----- Resulting Array is sorted: " + arrayIsSortedInAscendingOrder(quicksortObject));
        System.out.println("----- Resulting Array:");
    }

    //This function displays the results for all of the QuicksortObjects in an easily digestible format.
    private static void displayTotalResults(QuicksortObject[][] quicksortObjects) {
        QuicksortObject[] collapsedQuicksortObjectArray = new QuicksortObject[]{ //"Collapse" the 2D array of QuicksortObjects int a 1D array of QuicksortObjects so they are more easily streamable.
                quicksortObjects[ORIGINAL][RANDOM],
                quicksortObjects[ORIGINAL][ASCENDING],
                quicksortObjects[ORIGINAL][DESCENDING],
                quicksortObjects[RANDOMIZATION][RANDOM],
                quicksortObjects[RANDOMIZATION][ASCENDING],
                quicksortObjects[RANDOMIZATION][DESCENDING],
                quicksortObjects[MEDIAN_OF_3][RANDOM],
                quicksortObjects[MEDIAN_OF_3][ASCENDING],
                quicksortObjects[MEDIAN_OF_3][DESCENDING],
                quicksortObjects[MEDIAN_OF_5][RANDOM],
                quicksortObjects[MEDIAN_OF_5][ASCENDING],
                quicksortObjects[MEDIAN_OF_5][DESCENDING]
        };

        System.out.println("\n========== TOTAL RESULTS ==========");

        //Output comparison results.
        System.out.println("----- Number of comparisons in each Quicksort -----");
        Arrays.stream(collapsedQuicksortObjectArray) //Create a new stream (Java 8) out of the collapsedQuicksortObjectArray...
                .sorted(Comparator.comparingInt(quicksortObject -> quicksortObject.comparisons)) //Sort each QuicksortObject based on its comparisons...
                .map(quicksortObject -> quicksortObject.comparisons + " - " + quicksortObject.name) //Map each QuicksortObject to a String consisting of the QuicksortObjects comparisons, a dash, and its name...
                .forEach(System.out::println); //Output this String to the console.

        //Output swap results.
        System.out.println("\n----- Number of swaps in each Quicksort -----");
        Arrays.stream(collapsedQuicksortObjectArray) //Do the same thing, except for each QuicksortObject's swaps.
                .sorted(Comparator.comparingInt(quicksortObject -> quicksortObject.swaps))
                .map(quicksortObject -> quicksortObject.swaps + " - " + quicksortObject.name)
                .forEach(System.out::println);

        //Output Duration results.
        System.out.println("\n----- Duration (in milliseconds) in each Quicksort -----");
        Arrays.stream(collapsedQuicksortObjectArray) //Do the same thing, except for each QuicksortObject's duration.
                .sorted(Comparator.comparingLong(quicksortObject -> quicksortObject.duration))
                .map(quicksortObject -> quicksortObject.duration / 1000000.0 + " - " + quicksortObject.name)
                .forEach(System.out::println);
    }

    //This function simply outputs a basic String to show what sort we are about to do.
    private static void introduce(QuicksortObject quicksortObject) {
        System.out.print("\nBegginning " + quicksortObject.name + "..."); //DON'T print LINE, so that we can add " done" when the sort completes.
    }
}

//The QuicksortObject class. Used to store data about the various quicksort implementations.
class QuicksortObject {
    String name; //What quicksort implementation and array this object corresponds to.
    int[] array; //This objects array.
    int comparisons; //The number of comparisons this object has made.
    int swaps; //The number of swaps this object has made.
    long duration; //The duration this object took to have its array sorted.

    QuicksortObject(String name, int[] array, int comparisons, int swaps, long duration) { //Standard constructor.
        this.name = name;
        this.array = array;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.duration = duration;
    }

    QuicksortObject() { //Dummy constructor for creating blank QuicksortObjects (used for the monster bug above).
        this.name = "";
        this.array = new int[1000];
        this.comparisons = 0;
        this.swaps = 0;
        this.duration = 0;
    }
}
