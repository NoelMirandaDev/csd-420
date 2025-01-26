/* Developer: Noel Miranda
 * Date of creation: January 25, 2025
 * Program objective: Write a test program that stores 50,000 integers in LinkedList and test the time to 
 * traverse the list using an iterator vs. using the get(index) method.
 * 
 * Then, test your program storing 500,000 integers.
 * 
 * After completing this program and having tested both values, in your comments, explain the results and 
 * discuss the time taken using both values and their difference with the get(index) approach.
 * 
 * Write test code that ensures the code functions correctly.
*/

// Declared Package
package edu.advancedjava.module4;

// Imported Java Classes
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class TestLinkedListTraversingMethods {
	
	public static void main(String[] args) {
		// Test with 50,000 integers
		System.out.println("LinkedList Performance Test with 50,000 Integers");
		System.out.println("***************************************************************");
		linkedListPerformanceTest(50000);
		
		// Test with 500,000 integers
		System.out.println("\n\n\n\nLinkedList Performance Test with 500,000 Integers");
		System.out.println("***************************************************************");
		linkedListPerformanceTest(500000);
		
		// Additional test with 500 integers 
		System.out.println("\n\n\n\nLinkedList Performance Test with 500 Integers");
		System.out.println("***************************************************************");
		linkedListPerformanceTest(500);
	}

	private static void linkedListPerformanceTest(int size) {
		// Creates LinkedList
		List<Integer> list = new LinkedList<>();
		
		// Adds specified amount of integers
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		
		// Traverses list utilizing an Iterator
		long iteratorStartTime = System.nanoTime(); // records start time
		Iterator<Integer> iterator = list.iterator(); // Instance of iterator for list
		while (iterator.hasNext()) {
			iterator.next();
		}
		long iteratorEndTime = System.nanoTime(); // records end time
		
		
		// Traverses list utilizing the get(index) method
		long getMethodStartTime = System.nanoTime(); // records start time
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
		}
		long getMethodEndTime = System.nanoTime(); // records end time
		
		// Calculates the time it took for each traversing method
		long iteratorPerformanceTime = iteratorEndTime - iteratorStartTime;
		long getMethodPerformanceTime = getMethodEndTime - getMethodStartTime;
		
		// Outputs the tests' results
		System.out.println("List size: " + list.size());
		System.out.println("Iterator time: " + iteratorPerformanceTime / 1_000_000.0 + " ms"); // conversion for nano to ms
		System.out.println("get(index) time: " + getMethodPerformanceTime / 1_000_000.0 + " ms"); // conversion for nano to ms
	}
	
	 /* *************** EXPLANATION OF RESULTS ***************
	 *
	 * Iterator Traversal:
	 *    - The Iterator instance moves through the list one item at a time, starting from the beginning and going to the next item 
	 *    	in order. However, it is faster because it avoids the overhead of having to start over for each call (which the get(index) 
	 *    	method does), so it just keeps moving forward from where it left off. 
	 *    	***** Time Complexity: O(n) (Linear time) *****
	 *
	 * get(index) Traversal:
	 *    - The get(index) method is much slower for LinkedLists because it traverses the list from the start (each call) 
	 *    	to find each index. This becomes really inefficient as the list grows, resulting in longer execution times.
	 *    	***** Time Complexity: O(n^2) (Quadratic time) *****
	 *
	 * 50,000 integers result analysis:
	 * - Iterator: Fast and works well with larger lists.
	 * - get(index): Slow because it keeps starting over from the beginning.
	 *
	 * 500,000 integers result analysis:
	 * - The difference is way significant, mainly for the get(index) method because it is exponentially getting slower. The 
	 * 	 iterator instance keeps a linear efficiency.
	 *  
	 * Conclusion:
	 * Based of the performance results, an Iterator makes traversing a LinkedList efficient.
	 */
}