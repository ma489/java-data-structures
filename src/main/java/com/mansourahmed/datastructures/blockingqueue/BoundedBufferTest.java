package com.mansourahmed.datastructures.blockingqueue;

public class BoundedBufferTest {
	
	static class Consumer implements Runnable {
		
		private BoundedBuffer boundedBuffer;
		
		public Consumer(final BoundedBuffer boundedBuffer) {
			this.boundedBuffer = boundedBuffer;
		}

		@Override
		public void run() {
			for (int i = 1; i < 10; i++) {
				System.out.println(String.format("Thread %d - Attempting to remove head", Thread.currentThread().getId()));
				try {
					boundedBuffer.removeItemFromQueue();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class Producer implements Runnable {
		
		private BoundedBuffer boundedBuffer;
		
		public Producer(final BoundedBuffer boundedBuffer) {
			this.boundedBuffer = boundedBuffer;
		}

		@Override
		public void run() {
			for (int i = 1; i < 22; i++) {
				System.out.println(String.format("Thread %d - Attempting to add item to end of queue", Thread.currentThread().getId()));
				try {
					boundedBuffer.addToQueue(Integer.valueOf(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		final BoundedBuffer boundedBuffer = new BoundedBuffer(10);
		final Thread consumerThread = new Thread(new Consumer(boundedBuffer));
		final Thread producerThread = new Thread(new Producer(boundedBuffer));
		consumerThread.start();
		Thread.sleep(5_000);
		producerThread.start();
	}

}
