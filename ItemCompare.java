package parse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * Allows the comparison/sorting of items stored in HashMaps, using the compareTo method. 
 * Sorts by multiple keys, descending or ascending. 
 * The sorting is done according to the order of what is contained in the array keysToSortWith.
 * The reverse[] array reverses the corresponding results in the keysToSortWith[] array.
 */
public class ItemCompare<K extends Comparable<K>, V extends Comparable<V>>
		implements Comparator<HashMap<K, V>> {

	K[] keysToSortWith; // The keys to sort by, in order from first
								// to last
	boolean[] reverse; // Array that shows whether to sort the
						// corresponding key in keysToSortWith
						// ascending or descending

	/*
	 * Constructor that takesin the keys to sort with and assumes that they will
	 * be sorted ascending
	 */

	public ItemCompare(K[] keysToSortWith) {
		this.keysToSortWith = keysToSortWith;
		reverse = new boolean[keysToSortWith.length];
		Arrays.fill(reverse, false);
	}

	/*
	 * Constructor that takes in the keys to sort with, as well as a customized
	 * reverse array that can be used to sort fields descending
	 */
	public ItemCompare(K[] keysToSortWith, boolean[] reverse) {
		this.keysToSortWith = keysToSortWith;
		this.reverse = reverse;
	}

	/*
	 * Function that explains how to compare two HashMaps in the Collection that
	 * is passed in
	 */
	@Override
	public int compare(HashMap<K, V> a, HashMap<K, V> b) {
		int comparison = 0;
		int counter = 0;
		/*
		 * If the first keyToSortWith is the same in both HashMaps, sort by the
		 * next keyToSortWith
		 */
		while ((comparison == 0) && (counter < keysToSortWith.length)) {
			comparison = a.get(keysToSortWith[counter]).compareTo(
					b.get(keysToSortWith[counter]));
			counter++;
		}
		/*
		 * If the current keyToSortWith is supposed to be sorted negatively
		 * (according to the reverse array), returns the opposite result
		 */
		if (reverse[counter - 1] == true) {
			return comparison * (-1);
		}
		return comparison;
	}

}
