package lab;

/**
 * Aufgabe H1b)-H1c)
 *
 * Abgabe von: <name>, <name> und <name>
 */

import java.util.Random;

import frame.ListNode;
import frame.TableEntry;

public class HashTable {
	
	protected int capacity;
	protected int hash_a;
	protected int hash_b;
	protected LinkedList[] entryLists;
	
	 /**
     * The constructor
     * 
     * @param initialCapacity
     *            represents the initial size of the Hash Table.
     * 
     * The Hash-Table itself should be implemented in the array entryLists. When the
     * load factor exceeds 75%, the capacity of the Hash-Table should be
     * increased as described in the method rehash below.
     */
	public HashTable(int initialCapacity) {
		// TODO
	}
	
	/**
	 * Search for an TableEntry with the given key. If no such TableEntry is found, return null.
	 */
	public TableEntry find(String key) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Insert the given entry in the hash table. If there exists already an entry with this key,
	 * this entry should be overwritten. After inserting a new element, it might be necessary
	 * to increase the capacity of the hash table.
	 */
	public void insert(TableEntry entry) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Delete the TableEntry with the given key from the hash table and return the entry.
	 * Return null if key was not found.  
	 */
	public TableEntry delete(String key) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * The hash function used in the hash table.
	 */
	public int hash(String x) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Return the number of TableEntries in this hash table.
	 */
	public int size() {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Increase the capacity of the hash table and reorder all entries.
	 */
	private void rehash() {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Return the current "quality" of the hash table. The quality is measured by calculating
	 * the maximum number of collisions between entries in the table (i.e., the longest linked
	 * list in the hash table).
	 */
	public int quality() {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}

	public int getHash_a() {
		return hash_a;
	}
	
	public int getHash_b() {
		return hash_b;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public LinkedList[] getEntryLists() {
		return entryLists;
	}

}
