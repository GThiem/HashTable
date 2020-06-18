package lab;

/**
 * Aufgabe H1a)
 *
 * Abgabe von: <name>, <name> und <name>
 */

import frame.ListNode;
import frame.TableEntry;

public class LinkedList {
	
	private ListNode _head;
	private ListNode _nil;
	
	public LinkedList() {
		_nil = new ListNode(null, null, null);
		_nil.setNext(_nil);
		_nil.setPrev(_nil);
		_head = _nil;
	}
	
	public ListNode head() {
		return _head;
	}
	
	public ListNode nil() {
		return _nil;
	}
	
	/**
	 * Return the number of elements in the linked list.
	 */
	public int length() {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Insert an entry into the linked list at the position before the given node.
	 */
	public void insertBefore(TableEntry entry, ListNode node) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Append an entry to the end of the list.
	 */
	public void append(TableEntry entry) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
	
	/**
	 * Delete the given node from the linked list.
	 */
	public void delete(ListNode node) {
		// TODO
		throw new RuntimeException("Not implemented yet!");
	}
}
