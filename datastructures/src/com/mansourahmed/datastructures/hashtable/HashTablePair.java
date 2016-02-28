package com.mansourahmed.datastructures.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HashTablePair<T, U> {
	
	private final int MAX_CHAIN_SIZE;
	
	private Vector<List<Pair<T, U>>> vectorBehindHashTable;
	private Integer numberOfItemsInTable;
	
	public HashTablePair(final int initialTableSize, final int maxChainSize) {
		vectorBehindHashTable = new Vector<>();
		for (int i = 0; i < initialTableSize; i++) {
			vectorBehindHashTable.add(new ArrayList<Pair<T,U>>());
		}
		this.numberOfItemsInTable = 0;
		this.MAX_CHAIN_SIZE = maxChainSize;
	}
	
	private static class Pair<T, U> {
		private T key;
		private U value;
		
		public Pair(final T key, final U value) {
			this.key = key;
			this.value = value;
		}

		public T getKey() {
			return key;
		}

		public U getValue() {
			return value;
		}
	}
	
	/**
	 * Knuth's suggested constant factor
	 */
	private static final double A = (Math.sqrt(5) - 1) / 2; 
	
	private Integer hashFunction(final T key, final Integer size) throws Exception {
		System.out.println("key: "+ key + ", hash: " + (int)(Math.floor((size * ((makeIntoNumber(key) * A) % 1)))));
		return (int)(Math.floor((size * ((makeIntoNumber(key) * A) % 1))));
	}
	
	private void resizeAndRehash() throws Exception {
		System.out.println("Resize and rehash");
		final Vector<List<Pair<T, U>>> newVectorBehindHashTable = new Vector<>();
		newVectorBehindHashTable.setSize(vectorBehindHashTable.size() * 2);
		for (int i = 0; i < vectorBehindHashTable.size() * 2; i++) {
			newVectorBehindHashTable.add(new ArrayList<Pair<T,U>>());
		}
		for (List<Pair<T,U>> bucketChain : vectorBehindHashTable) {
			if (bucketChain != null && !bucketChain.isEmpty()) {
				final Integer newHash = hashFunction(bucketChain.get(0).getKey(), newVectorBehindHashTable.size());
				newVectorBehindHashTable.setElementAt(bucketChain, newHash);
			}
		}
		vectorBehindHashTable = newVectorBehindHashTable;
	}
	
	private Integer makeIntoNumber(final T key) throws Exception {
		if (key.getClass() == Integer.class) {
			return (Integer) key;
		} else {
			throw new Exception("Compatibility with keys of type other than Integer not yet implemented");
		}
	}
	
	public void insert(final Pair<T, U> item) throws Exception {
		vectorBehindHashTable.get(hashFunction(item.getKey(), vectorBehindHashTable.size())).add(item);
		numberOfItemsInTable++;
		if (numberOfItemsInTable / vectorBehindHashTable.size() > MAX_CHAIN_SIZE) {
			resizeAndRehash();
		}
	}
	
	public U search(final T key) throws Exception {
		final Integer hashOfItem = hashFunction(key, vectorBehindHashTable.size());
		List<Pair<T, U>> bucketAtHashOfItem = vectorBehindHashTable.get(hashOfItem);
		if (bucketAtHashOfItem == null) {
			return null;
		} else {
			for (final Pair<T, U> bucketElement : vectorBehindHashTable.get(hashOfItem)) {
				if (bucketElement.getKey().equals(key)) {
					return bucketElement.getValue();
				} 
			}
			return null;
		}
	}
	
	public boolean delete(final Pair<T, U> item) throws Exception {
		final Integer hashOfItem = hashFunction(item.getKey(), vectorBehindHashTable.size());
		List<Pair<T, U>> bucketAtHashOfItem = vectorBehindHashTable.get(hashOfItem);
		if (bucketAtHashOfItem == null) {
			return false;
		} else {
			vectorBehindHashTable.get(hashOfItem).remove(item);
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		final HashTablePair<Integer, String> hashTable = new HashTablePair<>(10,1); 
		hashTable.insert(new Pair<>(1, "Hello"));
		hashTable.insert(new Pair<>(2, "World"));
		hashTable.insert(new Pair<>(4, "Foo"));
		hashTable.insert(new Pair<>(5, "Bar"));
		hashTable.insert(new Pair<>(8, "42"));
		hashTable.insert(new Pair<>(13, "Hello"));
		hashTable.insert(new Pair<>(84, "World"));
		hashTable.insert(new Pair<>(7, "Foo"));
		hashTable.insert(new Pair<>(3, "Bar"));
		hashTable.insert(new Pair<>(6, "42"));
		hashTable.insert(new Pair<>(19, "Hello"));
		hashTable.insert(new Pair<>(20, "World"));
		hashTable.insert(new Pair<>(40, "Foo"));
		hashTable.insert(new Pair<>(50, "Bar"));
		hashTable.insert(new Pair<>(18, "42"));
		hashTable.insert(new Pair<>(14, "Hello"));
		hashTable.insert(new Pair<>(74, "World"));
		hashTable.insert(new Pair<>(17, "Foo"));
		hashTable.insert(new Pair<>(31, "Bar"));
		hashTable.insert(new Pair<>(61, "42"));
		System.out.println("Lookup");
		System.out.println(hashTable.search(2)); //world
		System.out.println(hashTable.search(1)); //hello
		System.out.println(hashTable.search(4)); //foo
		System.out.println(hashTable.search(5)); //bar
		System.out.println(hashTable.search(8)); //42
	}
}
