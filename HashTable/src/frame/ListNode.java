package frame;

public class ListNode {
	
	private ListNode _next;
	private ListNode _prev;
	private TableEntry _entry;
	
	public ListNode(TableEntry entry, ListNode prev, ListNode next) {
		_entry = entry;
		_next = next;
		_prev = prev;
	}
	
	public void setNext(ListNode next) {
		_next = next;
	}
	
	public ListNode next() {
		return _next;
	}
	
	public void setPrev(ListNode prev) {
		_prev = prev;
	}
	
	public ListNode prev() {
		return _prev;
	}
	
	public TableEntry entry() {
		return _entry;
	}

}
