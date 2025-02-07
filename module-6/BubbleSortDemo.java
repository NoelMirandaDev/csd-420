/* Developer: Noel Miranda
 * Date of creation: February 7, 2025
 * Program objective: Write a program with the two following generic methods using a bubble sort. 
 * The first method sorts the elements using the Comparable interface, and the second uses the 
 * Comparator interface.
 * 
 * public static <E extends Comparable<E>>
 * void bubbleSort(E[] list)
 * 
 * public static <E> void bubbleSort(E[] list,
 * Comparator<? super E> comparator)
*/

// Declared Package
package edu.advancedjava.module6;

// Imported Java classes
import java.util.Arrays;
import java.util.Comparator;

// Bubbles the elements to the correct position after each pass of the sorting loop
public class BubbleSortDemo {
	
	// Bubble sort using Comparable (Elements must implement Comparable)
	public static <E extends Comparable<E>> void bubbleSort(E[] list) {
		
		// Input validation to check if list is empty or has one element
		// This are cases that do not need sorting
		if (list == null || list.length <= 1) {
			return;
		}
		
		int n = list.length;
		boolean isListSorted; // Will be tracking if the array is sorted
		
		// Outer loop: Controls the number of passes (at most n - 1)
		for (int i = 0; i < n - 1; i++) {
			isListSorted = true; // Assumes List is sorted
			
			// Inner loop: Compares and swaps adjacent elements
			// Stops at n - 1 - i since the last i elements are already sorted
			for (int j = 0; j < n - 1 - i; j++) {
				if (list[j].compareTo(list[j + 1]) > 0) {
					// Swap elements if they are in the wrong order
					E temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					isListSorted = false; // Marks it as not sorted to continue sorting
				}
			}
			
			// Stops the method if no swaps occurred in this pass
			if (isListSorted) {
				break;
			}
		}
	}
	
	// Bubble sort utilizing Comparator
	public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
		// Input validation to check if list is empty or has one element
		// This are cases that do not need sorting
		if (list == null || list.length <= 1) {
			return;
		}
		// Verifies that comparator argument is not null
		if (comparator == null) {
			throw new IllegalArgumentException("Comparator cannot be null");
		}
		
		int n = list.length;
		boolean isListSorted; // Will be tracking if the array is sorted
		
		// Outer loop: Controls the number of passes 
		for (int i = 0; i < n - 1; i++) {
			// Assumes list is sorted
			isListSorted = true;
			
			// Inner loop: Swaps elements based on the comparator
			for (int j = 0; j < n - 1 - i; j++) {
				if (comparator.compare(list[j], list[j + 1]) > 0) {
					// Swap elements if in the wrong order
					E temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					isListSorted = false; // Marks it as not sorted to continue sorting
				}
			}
			
			// Stops the method if no swaps occurred in this pass
			if (isListSorted) {
				break;
			}
		}
	}
	
	// Main method for test code
	public static void main(String[] args) {
		System.out.println("************************************ Test Code ************************************");
		
		// Test Bubble sort with comparable
		Integer[] numbers = {10, 25, 100, 91, 0, 3, 45, 66, 91};
		System.out.println("\nOriginal array: " + Arrays.toString(numbers));
		bubbleSort(numbers);
		System.out.println("Sorted array using Comparable: " + Arrays.toString(numbers));
		
		// Test Bubble sort with Comparator
		String[] cars = {"Honda", "Toyota", "chevrolet", "Audi", "mercedes", "BMW", "Nissan", "lexus"};
		System.out.println("\nOriginal array: " + Arrays.toString(cars));
		bubbleSort(cars, (a, b) -> a.compareToIgnoreCase(b));
		System.out.println("Sorted array using comparator: " + Arrays.toString(cars));
		
		// Test edge cases 
		Integer[] emptyArray = {};
		bubbleSort (emptyArray);
		System.out.println("\nAfter evoking comparable bubble sort with empty array: " + Arrays.toString(emptyArray));
		
		Integer[] singleElement = {55};
		bubbleSort(singleElement, (a, b) -> a.compareTo(b));
		System.out.println("\nAfter evoking comparator bubble sort with single element array: " + Arrays.toString(singleElement));
		
		try {
			bubbleSort(numbers, null); // Should throw an exception
		} catch (IllegalArgumentException e) {
			System.out.println("\nException caugth: " + e.getMessage());
		}
	}
}