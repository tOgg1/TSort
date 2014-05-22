import java.util.*;

/**
 * A class containing all infeasible sorting algorithms you could possibly think of.
 * As many algorithms as possible are done in-place.
 */
public class Sort {

    /*
    ALGORITMHS USING BOGO-SWAP OR BOGO-INSERT
     */

    public static long one = 0;
    public static long two = 0;

    /**
     * BubgoSort: This algorithm implements bubblesort, using bogosort to swap the elements.
     * Runtime: O(n^3 * n!)
     * @param list
     */
    public static <T extends Comparable> void bubgoSort(List<T> list){
        for(int i = 0; i < list.size()-1; i++){
            for(int j = 0; j < list.size()-1; j++){

                // Generate new list
                List<T> oldList = new ArrayList<T>();
                copy(oldList, list);

                if(oldList.get(j).compareTo(oldList.get(j+1)) > 0){
                    bogoSwap(oldList, list, j, j+1);
                }
            }
        }
    }

    /**
     * BozgoboSort: This algorithm implements bozosort, using bogosort to swap the elements.
     * Runtime: O(n * n!^2)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void bozgoboSort(List<T> list){
        int i1, i2;
        Random ran = new Random();

        while(!isSorted(list)){
            i1 = ran.nextInt(list.size());
            i2 = ran.nextInt(list.size());

            // Checks and resolves equality between i1 and i2;
            i2 = i1 == i2 ? i1+1 > list.size() ? i1-1 : i1-1 < 0 ? i1+1 : i1-1 : i2;
            List<T> olist = new ArrayList<T>();

            copy(olist, list);

            bogoSwap(olist, list, i1, i2);
        }
    }

    /**
     * A combination of stooge sort and bogosort. Uses bogosort to swap the elements in stoogeSort.
     * Runtime: O(n^3.71 * n!)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void stoogeboSort(List<T> list){

    }


    /*
     OTHER SLOW ALGORITHMS
     */

    /**
     * Takes in a list and checks if it is sorted. If not, it sleeps for a bit and checks again. In theory,
     * alpha particles entering the computer will eventually flip enough bits to make sure the list is sorted.
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void alphaParticleSort(List<T> list, long sleepPeriod){
        while(!isSorted(list)){
            /*Sleep a bit*/
            long a, b;
            a = System.currentTimeMillis();
            while((System.currentTimeMillis() - a) < sleepPeriod){
                // Lets do something else for a bit

                int[] primes = new int[list.size()];
                for (int i = 2; i < list.size(); i++) {
                    primes[i] = i;
                }

                for (int i = 0; i < list.size(); i++) {
                    int j = primes[i];

                    if(j < 2 || j == -1)
                        continue;

                    for (int k = j; k < list.size(); k += j) {
                        primes[k] = -1;
                    }
                }

                // Print out all the awesome prime numbers
                for (int i = 0; i < list.size(); i++) {
                    if(primes[i] > 2 && primes[i] != -1){
                        System.out.println("Prime number: " + i);
                    }
                }
            }
        }
    }

    /*
    PLAIN ALGORITHMS
     */

    /**
     * BogoSort: Takes in a list and checks if it is in order; if not, the list is randomly permutated and rechecked.
     * Runtime: O(n * n!)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void bogoSort(List<T> list){
        while(!isSorted(list)){
            shuffle(list);
        }
    }

    /**
     * BozoSort: Takes in a list and checks if it is in order; if not, two random elements are swapped, and the list is checked again.
     * Runtime: O(n!)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void bozoSort(List<T> list){
        int i1, i2;
        Random ran = new Random();

        while(!isSorted(list)){
            i1 = ran.nextInt(list.size());
            i2 = ran.nextInt(list.size());
            swap(list, i1, i2);
        }
    }

    /**
     * Entry point for plain stoogesort
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void stoogeSort(List<T> list){
        stoogeSort(list, 0, list.size()-1);
    }

    /**
     * StoogeSort: The list is input with two indexes, if the element at index2 is larger than the element at index1, the elements are swapped.
     * The first two thirds of the list is then sorted using... you guessed it: StoogeSort. Then the last two thirds of the list is sorted using StoogeSort, and then the first thirds again.
     * Runtime: O(n^(log(3)/log(1.5)) ~ O(n^2.71)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void stoogeSort(List<T> list, int index1, int index2){
        if(list.get(index2).compareTo(list.get(index1)) < 0){
            swap(list, index1, index2);
        }

        if(index2 - index1 >= 2){
            int newindex = (index2 - index1 + 1)/3;
            stoogeSort(list, index1, index2-newindex);
            stoogeSort(list, index1 + newindex, index2);
            stoogeSort(list, index1, index2-newindex);
        }
    }

    /**
     * BubbleSort: Iterates through the list n^2 times, swapping two adjacent elements if element j+1 is smaller than element j.
     * This implements the effect of checking every item with every other item.
     * Runtime: O(n^2)
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void bubbleSort(List<T> list){
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = 0; j < list.size()-1; j++) {
                if(list.get(j).compareTo(list.get(j+1)) > 0){
                    swap(list, j, j+1);
                }
            }
        }
    }

    /**
     * For every element in the list, the element is moved "left" until it encounters an item smaller than itself.
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void insertionSort(List<T> list){
        for (int i = 1; i < list.size(); i++) {
            int j = i;

            while(list.get(j).compareTo(list.get(i)) > 0 && j > 0){
                j--;
            }
            
            if(j != i){
                insert(list, j, i);
            }
        }
    }

    /**
     * Plain and simple selectionSort
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void selectionSort(List<T> list){
        int min;
        for (int i = 0; i < list.size()-1; i++) {
            min = i;

            for (int j = i+1; j < list.size(); j++) {
                min = list.get(j).compareTo(list.get(min)) < 0 ? j : min;
            }

            if(i != min){
                swap(list, i, min);
            }
        }
    }

    /*
    HELPER METHODS BELOW
     */
    /**
     * Shuffles list2 until list2 is the same as list1, just with the element at index1 swapped with the element at index2.
     * @param list1
     * @param list2
     * @param index1
     * @param index2
     * @param <T>
     */
    public static <T extends Comparable> void bogoSwap(List<T> list1, List<T> list2, int index1, int index2){
        if(index1 > index2){
            int temp = index2;
            index2 = index1;
            index1 = temp;
        }

        if(index2-index1 == 1){
            while(!list1.get(index1).equals(list2.get(index2)) || !list1.get(index2).equals(list2.get(index1)) ||
                  !isEqual(list2, list1, 0, index1) || !isEqual(list2, list1, index2+1, list2.size())) {
                shuffle(list2);
            }
        } else {
            while(!list1.get(index1).equals(list2.get(index2)) || !list1.get(index2).equals(list2.get(index1)) ||
                  !isEqual(list2, list1, 0, index1) || !isEqual(list2, list1, index1+1, index2) ||
                  !isEqual(list2, list1, index2+1, list2.size())) {
                shuffle(list2);
            }
        }
    }

    /**
     * Copies a list to another list
     * @param target
     * @param copyable
     * @param <T>
     */
    public static <T extends Comparable> void copy(List<T> target, List<T> copyable){
        target.addAll(copyable);
    }

    /**
     * Inserts element at index2 into index1, all elements between index1 and index2 is shifted one place to the right, and the element at index2 is deleted.
     * @param list
     * @param index1
     * @param index2
     * @param <T>
     */
    public static <T extends Comparable> void insert(List<T> list, int index1, int index2){
        T element = list.get(index2);
        list.remove(index2);
        list.add(index1, element);
    }

    /**
     * Swaps the element at index1 with the element at index2
     * @param list
     * @param index1
     * @param index2
     * @param <T>
     */
    public static <T extends Comparable> void swap(List<T> list, int index1, int index2){
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Takes a list and shuffles it
     * @param list
     * @param <T>
     */
    public static <T extends Comparable> void shuffle(List<T> list){
        Collections.shuffle(list);
    }


    /**
     * Checks of a list is sorted
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable> boolean isSorted(List<T> list){
        Comparable cur = list.get(0);
        for (Comparable comparable : list) {
            if(comparable.compareTo(cur) < 0) {
                return false;
            }
            cur = comparable;
        }
        return true;
    }

    /**
     * Checks if two lists are equal from minIndex to maxIndex
     * @param list
     * @param olist
     * @param minIndex
     * @param maxIndex
     * @param <T>
     * @return
     */
    public static <T extends Comparable> boolean isEqual(List<T> list, List<T> olist, int minIndex, int maxIndex){
        if(maxIndex < 0)
            return true;
        if(minIndex == maxIndex)
            return true;
        for (int i = minIndex; i < maxIndex; i++){
            if(olist.get(i).compareTo(list.get(i)) != 0){
                return false;
            }
        }
        return true;
    }

    public static void startTimer(){
        one = System.nanoTime();
    }

    public static void endTimer(String name){
        two = System.nanoTime();
        System.out.println(name + "- Time elapsed: " + (two-one)*1e-9 + " seconds.");
    }

    public static void main(String[] args){
        Random ran = new Random();
        int numCount = 10;

        List<Integer> ints = new ArrayList<Integer>();

        for (int i = 0; i < numCount; i++) {
            ints.add(ran.nextInt(0xFFFF));
        }


        testBubbleSort(ints);
        shuffle(ints);

        testInsertionSort(ints);
        shuffle(ints);

        testSelectionSort(ints);
        shuffle(ints);

        testBubgoSort(ints);
    }

    public static <T extends Comparable> void testBubbleSort(List<T> list){
        startTimer();
        bubbleSort(list);
        System.out.println(isSorted(list));
        endTimer("BubbleSort");
    }

    public static <T extends Comparable> void testInsertionSort(List<T> list){
        startTimer();
        insertionSort(list);
        System.out.println(isSorted(list));
        endTimer("InsertionSort");
    }

    public static <T extends Comparable> void testSelectionSort(List<T> list){
        startTimer();
        selectionSort(list);
        System.out.println(isSorted(list));
        endTimer("SelectionSort");
    }

    public static <T extends Comparable>void testBubgoSort(List<T> list){
        startTimer();
        bubgoSort(list);
        System.out.println(isSorted(list));
        endTimer("BubgoSort");
    }

    public static <T extends Comparable> void testBozbogoSort(List<T> list){

    }

    public static <T extends Comparable> void testBogoSort(List<T> list){

    }
    

}
