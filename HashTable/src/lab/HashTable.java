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
	
	//DELETEpowefjqwfoqjefopqwempdoppppppppppppppppppppppppppppppppppppwwwwwwwwwwwwwwwwwwww
	//dqwiodfqowfpqwonqponvnnnnnnnnnnnnnnnnnnn
	public static void main(String[] args) {
		HashTable a = new HashTable(4);
		System.out.println(a.capacity);
		System.out.println(a.hash_a);
		System.out.println(a.hash_b);
		
	}
	
	 /**
     * The constructor
     * 
     * @param initialCapacity
     *            represents the initial size of the Hash Table.
     * 
     * The Hash-Table itself should be implemented in the array entryLists. When the
     * load factor exceeds 70%, the capacity of the Hash-Table should be
     * increased as described in the method rehash below.
     */
	public HashTable(int initialCapacity) {
		// TODO
		int inCap = initialCapacity;
        while(!isPrime(inCap)) {
            inCap++;
        }
        
        capacity = inCap;
        
        Random random = new Random();
        hash_a = random.nextInt(capacity-1)+1;
        hash_b = random.nextInt(capacity);
        
        entryLists = new LinkedList[capacity];
	}
	
	public boolean isPrime(int num) {
        boolean isPrime = true;
        
        if(num == 1)
        	return false;
        
        for(int i = 2; i <= num/2; i++) {
            if(num % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
	
	/**
	 * Search for an TableEntry with the given key. If no such TableEntry is found, return null.
	 */
	public TableEntry find(String key) {
		// TODO
		ListNode node = null;
		
		for(LinkedList list : entryLists) {
			if(list != null) {
				ListNode e = list.head();
				while(e != list.nil() && e.entry().getKey() != key) {
					e = e.next();
				}
				
				if(e != list.nil()) {
					node = e;
					break;
				} else {
					node = null;
				}
			}
		}
		
		if(node != null) {
			return node.entry();
		}
		
		return null;
		
	}
	
	/**
	 * Insert the given entry in the hash table. If there exists already an entry with this key,
	 * this entry should be overwritten. After inserting a new element, it might be necessary
	 * to increase the capacity of the hash table.
	 */
	public void insert(TableEntry entry) {
		// TODO
		String key = entry.getKey();
		TableEntry found = find(key);
		
		if(found == null) {
			for(int i = 0; i < entryLists.length; i++) {
				if(hash(key) == i) {
					if(entryLists[i] != null) {
						entryLists[i].insertBefore(entry, entryLists[i].head());
					}
					else {
						entryLists[i] = new LinkedList();
						entryLists[i].append(entry);
					}
				}
			}
		} else {
			found.setData(entry.getData());
		}
		
		double max = capacity * 0.7;
		
		if(size() >= max) {
			rehash();
		}
		
	}
	
	/**
	 * Delete the TableEntry with the given key from the hash table and return the entry.
	 * Return null if key was not found.  
	 */
	public TableEntry delete(String key) {
		// TODO
			ListNode node = null;
			
			for(LinkedList list : entryLists) {
				if(list != null) {
					//FALSCH
					ListNode e = list.head();
					while(e != list.nil()){
						if(e.entry().getKey().equals(key)) {
							node = e;
			                list.delete(e);
							return node.entry();
							
						}
						e = e.next();
					}
				}
			}
			
			return null;
		}
	
	/**
	 * The hash function used in the hash table.
	 */
	public int hash(String x) {
        int result = 0;

        for (int i = 0; i < x.length(); i++) {
            result += (Math.pow(3, i) + i) % Math.pow(2, 16) * (int) (x.charAt(i)) % Math.pow(2, 16);
        }
        
        result = (((hash_a * result % capacity) + (hash_b % capacity)) % capacity); 
        return result;
    }
	
	/**
	 * Return the number of TableEntries in this hash table.
	 */
	public int size() {
		// TODO
		int sum = 0;
		
		for(LinkedList list : entryLists) {
			if(list != null) {
				sum += list.length();	
			}
		}
		
		return sum;
	}
	
	/**
	 * Increase the capacity of the hash table and reorder all entries.
	 */
	private void rehash() {
		// TODO
		int num = capacity * 5;
		while(!isPrime(num)) {
            num++;
        }
		capacity = num;
		
		Random random = new Random();
		hash_a = random.nextInt(capacity-1)+1;
        hash_b = random.nextInt(capacity);
        
        HashTable newTable = new HashTable(capacity);
        newTable.hash_a = hash_a;
        newTable.hash_b = hash_b;
        
        for(LinkedList list : entryLists) {
        	if(list != null) {
				ListNode e = list.head();
				while(e != list.nil()) {
					ListNode temp = e.next();
					newTable.insert(e.entry());
					list.delete(e);
					e = temp;
				}
        	}
        }
        
        entryLists = newTable.entryLists;
        
	}
	
	/**
	 * Return the current "quality" of the hash table. The quality is measured by calculating
	 * the maximum number of collisions between entries in the table (i.e., the longest linked
	 * list in the hash table).
	 */
	public int quality() {
		// TODO
		int len = 0;
		
		for(LinkedList list : entryLists) {
			if(list != null) {
				if(list.length() > len)
					len = list.length();
			}
		}
		
		return len;
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
