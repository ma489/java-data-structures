package com.mansourahmed.datastructures.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	TrieNode root;

	private static class TrieNode {
		public String key;
		public Integer value;
		Map<String, TrieNode> edges; //map of key to child node
		
		public TrieNode(String key, Integer value) {
			this.key = key;
			this.value = value;
			edges = new HashMap<>();
		}

		public TrieNode traverse(char c) {
			return edges.get(String.valueOf(c));
		}
	}
	
	public void insert(final String key, final Integer value) {
		TrieNode currentNode = root;
		for (final char c : key.toCharArray()) {
			TrieNode child = currentNode.traverse(c);
			if (child == null) {
				child = new TrieNode(key, value);
				currentNode.edges.put(String.valueOf(c), child);
			} else {
				currentNode = child;
			}
		}
	}
	
	public Integer search(final String key) {
		TrieNode currentNode = root;
		for (final char c : key.toCharArray()) {
			TrieNode childNode = currentNode.traverse(c);
			if (childNode == null) {
				return null;
			} else {
				currentNode = childNode;
			}
		}
		if (key.equals(currentNode.key)) {
			return currentNode.value;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args){
		final Trie trie = new Trie();
		trie.root = new TrieNode("", null);
		trie.insert("a", 15);
		trie.insert("t", null);
		trie.insert("i", 11);
		trie.insert("to", 7);
		trie.insert("te", null);
		trie.insert("tea", 3);
		trie.insert("ted", 4);
		trie.insert("ten", 12);
		trie.insert("in", 5);
		trie.insert("inn", 9);
		System.out.println(trie.search("a")); //print 15
		System.out.println(trie.search("to")); //print 7
		System.out.println(trie.search("tea")); //print 3
		System.out.println(trie.search("ted")); //print 4
		System.out.println(trie.search("ten")); //print 12
		System.out.println(trie.search("inn")); //print 9
		System.out.println(trie.search("in")); //print 5
		System.out.println(trie.search("i")); //print 11
		//null
		System.out.println(trie.search("t")); //print null
		System.out.println(trie.search("te")); //print null
	}
	
}
