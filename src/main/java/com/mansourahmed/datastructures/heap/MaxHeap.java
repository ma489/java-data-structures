package com.mansourahmed.datastructures.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap {

	private List<Integer> heap;
	private Integer heapSize;
	
	public MaxHeap() {
		this.heap = new ArrayList<>();
		this.heapSize = 0;
	}
	
	public MaxHeap(final Integer[] array) {
		this.heap = Arrays.asList(array);
		buildMaxHeap();
	}
	
	public MaxHeap(final List<Integer> list) {
		this.heap = list;
		buildMaxHeap();
	}
	
	public Integer getHeapLength(){
		return heap.size();
	}
	
	public Integer getHeapSize(){
		return heapSize;
	}
	
	public void setHeapSize(final int i){
		this.heapSize = i;
	}
	
	public List<Integer> getUnderlyingArray(){
		return heap;
	}
	
	public Integer getRoot(){
		return heap.get(0);
	}
	
	public Integer getIndexOfParent(final int index){
		return (int) Math.floor(index/2);
	}
	
	public Integer getIndexOfLeft(final int index){
		return (2 * index);
	}
	
	public Integer getIndexOfRight(final int index){
		return (2 * index) + 1;
	}
	
	public void maxHeapify(final int i){
		int left = getIndexOfLeft(i);
		int right = getIndexOfRight(i);
		int largest;
		if (left < getHeapSize() && heap.get(left) > heap.get(i)) {
			largest = left;
		} else {
			largest = i;
		}
		if (right < getHeapSize() && heap.get(right) > heap.get(i)) {
			largest = right;
		}
		if (largest != i) {
			int temp = heap.get(largest);
			heap.set(largest, heap.get(i));
			heap.set(i, temp);
			maxHeapify(largest);
		}
	}
	
	public void buildMaxHeap(){
		setHeapSize(heap.size());
		for (int i = (int) Math.floor(heap.size()/2); i >= 0; i--) {
			maxHeapify(i);
		}
	}
	
}
