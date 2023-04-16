package aston;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PersonalArrayList<E extends Comparable<? super E>> {

    /** Default capacity for an array */
    private static final int DEFAULT_CAPACITY = 10;

    /** A crutch for a quicksort, don't judge me =D */
    private Class<E> clazz;

    /** A 'dynamic' array in witch all the elements are stored
     * once it hits full capacity it uses resize() method to increase it*/
    private E[] array;


    /** This value shows the amount of elements in the array */
    private int pointer = 0;

    /** Constructor
     * @param clazz is the crutch for a quicksort to cast to an appropriate form*/
    public PersonalArrayList(Class<E> clazz) {
        this.clazz = clazz;
        array = (E[]) Array.newInstance(clazz, DEFAULT_CAPACITY);
    }



    /** Method for changing the array capacity
     * @param newLength desired length of the new array */
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = (E[]) newArray;
    }

    /** Adding a new element to the array
     * @param e is the value which is added*/
    public boolean add(E e) {
        if(pointer == array.length - 1)
            resize(array.length*2);
        array[pointer++] = e;
        return true;
    }

    /** Trims the array to the exact number of elements it contains */
    private void trimToSize() {
        if(array.length > pointer) {
            array = Arrays.copyOf(array, pointer);
        }
    }

    /** Adds the value to specific location in the array
     * @param index the index where the new element is added
     * @param e the value which is added to the array*/
    public void add(int index, E e) {
        if(pointer == array.length - 1)
            resize(array.length*2);

        Object[] temp = array;
        System.arraycopy(array, index, array, index + 1, pointer - index);
        array[index] = e;
        pointer++;
    }

    /** Returns the number of elements in the array */
    public int size() {
        return pointer;
    }

    /** Returns the element by index
     * @param index  */
    public E get(int index) {
        return  array[index];
    }

    /** Do I really need to explain this =D */
    public String toString() {
        String string = "";
        for(int i = 0; i < pointer; i++) {
            string += array[i] + " ";
        }
        return string;
    }

    /** Delete element by index
     * @param index  */
    public void delete(int index) {
        Object[] temp = array;
        System.arraycopy(temp, 0, array, 0, index-1);
        int amountElementsAfterIndex = temp.length - index -1;
        System.arraycopy(temp, index +1, array, index, amountElementsAfterIndex);
        pointer--;
    }


    /** Changes the value of element
     * @param index
     * @param element new value */
    public E set(int index, E element) {
        array[index] = element;
        return element;
    }

    public boolean contains(E element) {
        return Arrays.asList(array).contains(element);
    }

    /** A quick way to use quicksort on our array, you just call
     * without giving any parameters and it sorts the whole array */
    private void quicksort() {
        trimToSize();
        quicksortImpl(array, 0, pointer-1);
    }

    /** Main quicksort implementation
     * @param arr array of elements that need to be sorted
     * @param begin starting point of sorting
     * @param end end point of sorting*/
    private void quicksortImpl(E[] arr, int begin, int end) {
        if(begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quicksortImpl(arr, begin, partitionIndex);
            quicksortImpl(arr, partitionIndex+1, end);
        }
    }
    private int partition(E[] arr, int begin, int end) {
        E pivot = arr[end];
        begin--;
        end++;

        while (true) {
            do begin++; while (arr[begin].compareTo(pivot) < 0);

            do end--; while (arr[end].compareTo(pivot) > 0);

            if (begin >= end) return end;

            E temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
        }

    }


}
