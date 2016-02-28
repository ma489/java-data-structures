package com.mansourahmed.datastructures.circularbuffer;

public class CircularBuffer<T> {
	
	private T[] backingArray;
	private int pointerToStartOfValidData;
	private int pointerToEndOfValidData;
	
	@SuppressWarnings("unchecked")
	public CircularBuffer(final int size){
		this.backingArray = (T[]) new Object[size];
		this.pointerToStartOfValidData = 0;
		this.pointerToEndOfValidData = 0;
	}
	
	public boolean addItem(final T item) {
		if (pointerToEndOfValidData + pointerToStartOfValidData == backingArray.length) {
			return false; //discard the data if the buffer is full
		}
		backingArray[pointerToEndOfValidData] = item;
		pointerToEndOfValidData++;
		if (pointerToEndOfValidData > backingArray.length) {
			pointerToEndOfValidData = 0;
		}
		return true;
	}
	
	public T removeItem() {
		if (backingArray.length == 0) {
			return null; //return nothing if the buffer is empty
		}
		if (pointerToEndOfValidData == pointerToStartOfValidData
			&& pointerToEndOfValidData + pointerToStartOfValidData != backingArray.length) {
			return null; //return nothing if the buffer is empty
		}
		if (pointerToStartOfValidData >= backingArray.length) {
			pointerToStartOfValidData = 0;
		}
		final T itemToReturn = backingArray[pointerToStartOfValidData];
		backingArray[pointerToStartOfValidData] = null;
		pointerToStartOfValidData++;
		return itemToReturn;
	}
	
	public static void main(String[] args) {
		final CircularBuffer<Integer> integerCircularBuffer = new CircularBuffer<>(10);
		for (Integer i = 0; i < 12; i++) {
			System.out.println("Adding " + i  + " - " + integerCircularBuffer.addItem(i));
		}
		for (Integer i = 0; i < 12; i++) {
			System.out.println("Removing " + i  + " - " + integerCircularBuffer.removeItem());
		}
	}
	
}
