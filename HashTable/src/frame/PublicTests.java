package frame;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lab.HashTable;
import lab.LinkedList;

/**
 * Do NOT change anything in this class!
 * 
 * The test cases defined by this class are used to test if your program
 * works correctly. This class is also responsible for outputting to the
 * console.
 * 
 */

@DisplayName("Exercise 8 - Hash Tables")
class PublicTests {
	
	protected int correct = 0;
	protected Duration timeout = Duration.ofSeconds(20);
	
	protected byte[] readFile(String inputFile) {
		try {
			File f = new File(inputFile);
			return Files.readAllBytes(f.toPath());
		} catch (IOException e) {
			throw new RuntimeException("IOError");
		}
	}
	
	protected ArrayList<TableEntry> readEntries(String inputFile) {
		try {
			ArrayList<TableEntry> entries = new ArrayList<TableEntry>(); 
			FileReader fr = new FileReader(inputFile);
			BufferedReader in = new BufferedReader(fr);
	
			String line;
			while ((line = in.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] data = line.split(":");
				TableEntry entry = new TableEntry(data[0], data[1]);
				entries.add(entry);
			}
	
			in.close();
			fr.close();
	
			return entries;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Testfile is broken!");
		}
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("A: Linked List tests")
	class A_LinkedListTests {
		
		@DisplayName("LinkedList: Insert, delete and length")
		@Test
		@Order(1)
		void test1() {
			System.out.println("\nStarting test: LinkedList: Insert, delete and length");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					LinkedList list = new LinkedList();
					assertEquals(0, list.length());
					list.append(new TableEntry("asd", "asd"));
					assertEquals(1, list.length());
					list.insertBefore(new TableEntry("asd2", "qwe"), list.head());
					list.insertBefore(new TableEntry("asd3", "qwe"), list.head());
					assertEquals(3, list.length());
					assertEquals("asd3", list.head().entry().getKey());
					assertEquals("asd2", list.head().next().entry().getKey());
					assertEquals("asd", list.head().next().next().entry().getKey());
					list.insertBefore(new TableEntry("qwe", "123"), list.head().next());
					assertEquals("qwe", list.head().next().entry().getKey());
					assertEquals(4, list.length());
					list.delete(list.head().next());
					assertEquals("asd2", list.head().next().entry().getKey());
					assertEquals(3, list.length());
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: LinkedList: Insert, delete and length");
		}
		
		@DisplayName("LinkedList: next, prev")
		@Test
		@Order(2)
		void test2() {
			System.out.println("\nStarting test: LinkedList: next, prev");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					LinkedList list = new LinkedList();
					list.append(new TableEntry("1", "1"));
					list.append(new TableEntry("2", "2"));
					list.append(new TableEntry("3", "3"));
					list.append(new TableEntry("4", "4"));
					list.append(new TableEntry("5", "5"));
					ListNode node = list.head();
					assertEquals(list.nil(), node.prev());
					assertEquals(node, node.next().prev());
					node = node.next().next();
					assertEquals("3", node.entry().getKey());
					list.insertBefore(new TableEntry("2,5", "2,5"), node);
					assertEquals("2,5", node.prev().entry().getKey());
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: LinkedList: next, prev");
		}
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("B: Hash function tests")
	class B_HashFunctionTests {
		
		@DisplayName("Test the hash function")
		@Test
		@Order(1)
		void test1() {
			System.out.println("\nStarting test: Hash function");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					HashTable table = new HashTable(100);
					int hash = table.hash("test");
					assertTrue(0<=hash);
					assertTrue(hash<table.getCapacity());
					hash = table.hash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
					assertTrue(0<=hash);
					assertTrue(hash<table.getCapacity());
					assertTrue(table.getHash_a()>=0);
					assertTrue(table.getHash_a()<table.getCapacity());
					assertTrue(table.getHash_b()>=0);
					assertTrue(table.getHash_b()<table.getCapacity());
					
					HashTable table2 = new HashTable(100000);
					int hash2 = table2.hash("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
					int hash3 = table2.hash("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
					assertTrue(hash2 != hash3);
					
					int hash4 = table2.hash("ff");
					int hash5 = table2.hash("vb");
					assertEquals(hash4, hash5);
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: Hash function");
		}
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("C: Hash Table tests")
	class C_HashTableTests {
		
		@DisplayName("HashTable: basic insert, find, delete")
		@Test
		@Order(1)
		void test1() {
			System.out.println("\nStarting test: HashTable: basic insert, find, delete");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					HashTable table = new HashTable(100);
					table.insert(new TableEntry("abcdefghi", "asd"));
					table.insert(new TableEntry("qwerty", "asd"));
					table.insert(new TableEntry("lll", "ppp"));
					table.insert(new TableEntry("[]{}./", "asd"));
					table.insert(new TableEntry("2931", "asd"));
					assertEquals(5, table.size());
					assertTrue(table.find("qwerty")!=null);
					assertEquals(null, table.find("qwertz"));
					table.insert(new TableEntry("lll", "123"));
					assertEquals(5, table.size());
					assertEquals("123", table.find("lll").getData());
					TableEntry entry = table.delete("abcdefghi");
					assertEquals("asd", entry.getData());
					assertEquals(null, table.find("abcdefghi"));
					assertEquals(null, table.delete("asd"));
					assertEquals(4, table.size());
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: HashTable: basic insert, find, delete");
		}
		
		@DisplayName("HashTable: capacity & rehashing")
		@Test
		@Order(2)
		void test2() {
			System.out.println("\nStarting test: HashTable: capacity & rehashing");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					HashTable table = new HashTable(2);
					assertEquals(2, table.getCapacity());
					table.insert(new TableEntry("abcdefghi", "asd"));
					assertEquals(2, table.getCapacity());
					assertEquals(1, table.quality());
					table.insert(new TableEntry("qwerty", "asd"));
					assertEquals(11, table.getCapacity());
					for( int i=0; i<6; i++ ) {
						table.insert(new TableEntry("id"+i, "asd"));
					}
					assertEquals(59, table.getCapacity());
					int hash_a = table.getHash_a();
					int hash_b = table.getHash_b();
					for( int i=0; i<34; i++ ) {
						table.insert(new TableEntry("id_"+i, "asd"));
					}
					assertEquals(307, table.getCapacity());
					assertTrue( hash_a != table.getHash_a() || hash_b != table.getHash_b());
					int hash = table.hash("abcdefghi");
					assertTrue( table.getEntryLists()[hash] != null );
					assertTrue( table.getEntryLists()[hash].length() != 0 );
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: HashTable: capacity & rehashing");
		}
		
		@DisplayName("HashTable: Collisions")
		@Test
		@Order(3)
		void test3() {
			System.out.println("\nStarting test: HashTable: Collisions");
			assertTimeoutPreemptively(timeout, () -> {
				try {
					HashTable table = new HashTable(10);
					table.insert(new TableEntry("ff", "asd"));
					table.insert(new TableEntry("je", "asd"));
					table.insert(new TableEntry("bg", "asd"));
					table.insert(new TableEntry("vb", "asd"));
					table.insert(new TableEntry("nd", "asd"));
					table.insert(new TableEntry("rc", "asd"));
					assertEquals(6, table.size());
					assertEquals(6, table.quality());
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: HashTable: Collisions");
		}
		
		@DisplayName("HashTable: Large Tests")
		@ParameterizedTest(name = "Input file: {0}")
		@ValueSource(strings = { "tests/public/test1.txt", "tests/public/test2.txt" })
		@Order(4)
		void test4( String inputFile ) {
			System.out.println("\nStarting test: HashTable: with Input: "+inputFile);
			assertTimeoutPreemptively(timeout, () -> {
				try {
					ArrayList<TableEntry> entries = readEntries(inputFile);
					HashTable table = new HashTable(10);
					for( int i=0; i<entries.size(); i++) {
						table.insert(entries.get(i));
					}
					for( int i=0; i<entries.size(); i++) {
						TableEntry entry = entries.get(i);
						assertEquals( entry.getKey(), table.find(entry.getKey()).getKey());
					}
				} catch (Exception e) {
					System.out.println("Error: An exception was thrown: " + e.getMessage());
					throw e;
				}
			});
			System.out.println("Finished test: HashTable: with Input: "+inputFile);
		}
	}
}
	