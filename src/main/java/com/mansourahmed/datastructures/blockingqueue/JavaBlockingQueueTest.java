package com.mansourahmed.datastructures.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class JavaBlockingQueueTest {
	
	static class Consumer implements Runnable {
		
		private java.util.concurrent.BlockingQueue<Integer> blockingQueue;
		
		public Consumer(final java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			for (int i = 1; i < 10; i++) {
				try {
					System.out.println(String.format("Thread %d - Attempting to remove head", Thread.currentThread().getId()));
					blockingQueue.take();
					System.out.println(String.format("Thread %d - Taken", Thread.currentThread().getId()));
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	static class Producer implements Runnable {
		
		private java.util.concurrent.BlockingQueue<Integer> blockingQueue;
		
		public Producer(final java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			for (int i = 1; i < 10; i++) {
				try {
					System.out.println(String.format("Thread %d - Attempting to add item to end of queue", Thread.currentThread().getId()));
					blockingQueue.put(Integer.valueOf(i));
					System.out.println(String.format("Thread %d - Put", Thread.currentThread().getId()));
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		final java.util.concurrent.BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(); //optionally-bounded queue
			//use ArrayBlockingQueue next time?
		final Thread producerThread = new Thread(new Producer(blockingQueue));
		final Thread consumerThread = new Thread(new Consumer(blockingQueue));
		consumerThread.start();
		Thread.sleep(5_000);
		producerThread.start();
	}

}
