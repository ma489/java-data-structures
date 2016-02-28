package com.mansourahmed.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class DirectedAcyclicGraph {
	
	private Map<Integer, List<Integer>> adjacencyList;
	
	public DirectedAcyclicGraph() {
		this.adjacencyList = new HashMap<>();
	}
	
	public DirectedAcyclicGraph(final Map<Integer, List<Integer>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	
	private boolean depthFirstSearchForLoops(final Integer node, final List<Integer> visitedNodesList) {
		visitedNodesList.add(node);
		if (adjacencyList.get(node) != null) {
			for (final Integer adjacentNode : adjacencyList.get(node)) {
				if (visitedNodesList.contains(adjacentNode)) {
					return true;
				} else {
					depthFirstSearchForLoops(adjacentNode, visitedNodesList);
				}
			}
		}
		return false;
	}
	
	private boolean isAcyclic(final Map<Integer, List<Integer>> adjacencyList) {
		//do DFS for each node in adjacency list, (if come across a node twice in a DFS then we have a cycle)
		for (final Integer node : adjacencyList.keySet()) {
			final List<Integer> visitedNodesList = new ArrayList<>();
			if (depthFirstSearchForLoops(node, visitedNodesList)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addNode(final Integer nodeToAdd, 
						   final List<Integer> successorNodes, 
						   final List<Integer> predecessorNodes) {
		if (successorNodes.contains(nodeToAdd)) {
			return false; //optimisation: do not allow simple cycles
		}
		for (final Integer predecessor : predecessorNodes) {
			adjacencyList.get(predecessor).add(nodeToAdd);
		}
		if (!isAcyclic(adjacencyList)) {
			//undo adding this node to the graph
			for (final Integer predecessor : predecessorNodes) {
				adjacencyList.get(predecessor).remove(nodeToAdd);
			}
			return false; //failed to add node
		}
		return true; //successfully added node
	}
	
	public static void main (String[] args){
		final Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
		adjacencyList.put(1, Lists.newArrayList(2, 7, 8));
		adjacencyList.put(2, Lists.newArrayList(3, 6));
		adjacencyList.put(3, Lists.newArrayList(4, 5));
		adjacencyList.put(4, null);
		adjacencyList.put(5, null);
		adjacencyList.put(6, null);
		adjacencyList.put(7, null);
		adjacencyList.put(8, Lists.newArrayList(9, 12));
		adjacencyList.put(9, Lists.newArrayList(10,11));
		adjacencyList.put(10, null);
		adjacencyList.put(11, null);
		adjacencyList.put(12, null);
		final DirectedAcyclicGraph dag = new DirectedAcyclicGraph(adjacencyList);
		System.out.println("Acyclic? " + dag.isAcyclic(adjacencyList));
	}

}
