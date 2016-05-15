package com.mansourahmed.datastructures.blockingqueue;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue {
	
	private List<Integer> queue;
	private int queueCapacity;
	
	//not needed, as arraylist does this for me
//	private int headOfQueue; 
//	private int tailOfQueue;
	
	public BlockingQueue(final int capacity) {
		this.queue = new ArrayList<>();
		this.queueCapacity = capacity;
	}
	
	//synchronized: critical section; two threads cannot be in this method (or any other synchronized method?)
		// and hence execute this code at the same time
	public synchronized void addToEndOfQueue(final Integer item) throws InterruptedException {
		while (queue.size() >= queueCapacity) {
			System.out.println(String.format("Thread %d - Queue full when attempting to add %s, waiting", Thread.currentThread().getId(), item));
			wait();
		} 
		System.out.println(String.format("Thread %d - Adding %s to queue (queue size: %s)", Thread.currentThread().getId(), item, queue.size()));
		queue.add(item);
		notifyAll();
	}
	
	//synchronized: critical section; two threads cannot be in this method (or any other synchronized method?)
		// and hence execute this code at the same time
	public synchronized Integer removeItemAtHead() throws InterruptedException {
		while (queue.isEmpty()) {
			System.out.println(String.format("Thread %d - Queue empty when attempting to remove head, waiting", Thread.currentThread().getId()));
			wait();
		}
		System.out.println(String.format("Thread %d - Removing head of queue [%s]", Thread.currentThread().getId(), queue.size()));
		final Integer removed = queue.remove((int) 0);
		notifyAll();
		return removed;
		//index int not Object Integer is my intention - is this correct? 
			//or do I need to cast the 0 to (int)?
	}
	
}
