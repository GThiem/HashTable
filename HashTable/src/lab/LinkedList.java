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
        int counter = 0;
        if(_head == _nil) {
            return 0;
        }
        ListNode temp = _head;
        while(temp.next() != _nil) { 
            temp = temp.next();
            counter++;
        }
        return counter+1;
    }
	
	/**
	 * Insert an entry into the linked list at the position before the given node.
	 */
	public void insertBefore(TableEntry entry, ListNode node) {
        // TODO
        if(node == _head) {
            ListNode newNode = new ListNode(entry,_nil,_head);
            _head.setPrev(newNode);
            _head = newNode;
            return;
        }
        if(node == _nil) {
            append(entry);
            return;
        }
        ListNode temp = _head;
        while(temp.next()!= _nil) {
            if(temp == node) {
                ListNode newNode = new ListNode(entry,temp.prev(),node);
                temp.prev().setNext(newNode);
                node.setPrev(newNode);
                return;
            } else
                temp = temp.next();
        }
    }
	
	/**
	 * Append an entry to the end of the list.
	 */
	public void append(TableEntry entry) {
        if(_head == _nil) {
            _head = new ListNode(entry,_nil,_nil);
            return;
        }
        ListNode temp = _head;
        while(temp.next()!=_nil) {
            temp = temp.next();
        }
        ListNode newNode = new ListNode(entry, temp,_nil);
        temp.setNext(newNode);

    }
	
	/**
	 * Delete the given node from the linked list.
	 */
	public void delete(ListNode node) {
        ListNode temp = _head;
        if(_head == _nil) {
        	return;
        }
        if(node == _head) {
        	_head.next().setPrev(_nil);
        	_head = _head.next();
        }
        while(temp.next()!=_nil) {
            if(temp == node) {
                temp.prev().setNext(temp.next());
                temp.next().setPrev(temp.prev());
                return;
            }else
                temp = temp.next();
        }
    }
}
