package com.mansourahmed.datastructures.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	
	private final List<Integer> queue = new ArrayList<>();
	private final int queueCapacity;
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public BoundedBuffer(final int i) {
		this.queueCapacity = i;
	}
	
	public void addToQueue(final Integer item) throws InterruptedException {
		lock.lock();
		try { 
			while (queue.size() >= queueCapacity) { // queue is full
				System.out.println("Blocked, queue full");
				notFull.await(); //wait on the notFull condition to be true (ie. await the truth of notFull)
			}
			queue.add(item);
			notEmpty.signal(); //wake on thread up at a time, less overhead than notifyAll (but no necessarily better?)
		} finally {
			lock.unlock();
		}
	}

	public Integer removeItemFromQueue() throws InterruptedException {
		lock.lock();
		try {
			while (queue.size() == 0) { //queue is empty
				System.out.println("Blocked, queue empty");
				notEmpty.await(); //wait on notEmpty to be true
			}
			final Integer itemToReturn = queue.remove(0);
			notFull.signal(); //wake on thread up at a time, less overhead than notifyAll (but no necessarily better?)
			return itemToReturn;
		} finally {
			lock.unlock();
		}
	}
	
}
