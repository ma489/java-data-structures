package com.mansourahmed.datastructures.blockingqueue;
//
public class BlockingQueueTest {
	
	static class Consumer implements Runnable {
		
		private BlockingQueue blockingQueue;
		
		public Consumer(final BlockingQueue blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			for (int i = 1; i < 10; i++) {
				try {
					System.out.println(String.format("Thread %d - Attempting to remove head", Thread.currentThread().getId()));
					blockingQueue.removeItemAtHead();
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	static class Producer implements Runnable {
		
		private BlockingQueue blockingQueue;
		
		public Producer(final BlockingQueue blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			for (int i = 1; i < 30; i++) {
				try {
					System.out.println(String.format("Thread %d - Attempting to add item to end of queue", Thread.currentThread().getId()));
					blockingQueue.addToEndOfQueue(Integer.valueOf(i));
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		final BlockingQueue blockingQueue = new BlockingQueue(10);
		final Thread consumerThread = new Thread(new Consumer(blockingQueue));
		final Thread producerThread = new Thread(new Producer(blockingQueue));
		consumerThread.start();
		Thread.sleep(5_000);
		producerThread.start();
	}

}
