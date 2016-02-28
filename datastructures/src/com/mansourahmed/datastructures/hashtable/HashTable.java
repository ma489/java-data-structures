package com.mansourahmed.datastructures.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HashTable {
	
	private final Vector<List<Integer>> vectorBehindHashTable = new Vector<>();
	
	/**
	 * Knuth's suggested constant factor
	 */
	private static final double A = (Math.sqrt(5) - 1) / 2; 
	
	private Integer hashFunction(final Integer item) {
		return (int)(Math.floor((vectorBehindHashTable.capacity() * ((item * A) % 1))));
	}
	
	public void insert(final Integer item) {
		final Integer hashOfItem = hashFunction(item);
		if (hashOfItem >= vectorBehindHashTable.size()) {
			vectorBehindHashTable.setSize(hashOfItem + 1);
			vectorBehindHashTable.insertElementAt(new ArrayList<Integer>(), hashOfItem);
		}
		if (vectorBehindHashTable.get(hashOfItem) == null) {
			vectorBehindHashTable.set(hashOfItem, new ArrayList<Integer>());
		}
		vectorBehindHashTable.get(hashOfItem).add(item);
	}
	
	public Integer search(final Integer item) {
		final Integer hashOfItem = hashFunction(item);
		List<Integer> bucketAtHashOfItem = vectorBehindHashTable.get(hashOfItem);
		if (bucketAtHashOfItem == null) {
			return null;
		} else {
			for (final Integer bucketElement : vectorBehindHashTable.get(hashOfItem)) {
				if (bucketElement.equals(item)) {
					return bucketElement;
				} 
			}
			return null;
		}
	}
	
	public boolean delete(final Integer item) {
		final Integer hashOfItem = hashFunction(item);
		List<Integer> bucketAtHashOfItem = vectorBehindHashTable.get(hashOfItem);
		if (bucketAtHashOfItem == null) {
			return false;
		} else {
			vectorBehindHashTable.get(hashOfItem).remove(item);
			return true;
		}
	}
	
	public static void main(String[] args) {
		final HashTable hashTable = new HashTable(); 
		hashTable.insert(1);
		hashTable.insert(2);
		hashTable.insert(3);
		hashTable.insert(4);
		hashTable.insert(8);
		System.out.println(hashTable.search(2));
	}
}
