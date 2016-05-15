package com.mansourahmed.datastructures.mutex;

import java.util.ArrayList;
import java.util.List;

public class MutexTest {
	
	private static class MyThread implements Runnable {
		
		private Mutex mutex;
		private List<Integer> sharedResource;
		
		public MyThread(final Mutex mutex, final List<Integer> sharedResource) {
			this.mutex = mutex;
			this.sharedResource = sharedResource;
		}
		
		@Override
		public void run() {
			final long thisThreadId = Thread.currentThread().getId();
			try {
				while (!mutex.lock(thisThreadId)) { //busy-waiting (spin-lock)
					System.out.println("thread-"+thisThreadId + " Couldn't acquire lock, waiting...");
					Thread.sleep(5_000);
				}
				System.out.println("thread-"+thisThreadId + " acquired lock");
				System.out.println("sharedResource before thead-" + thisThreadId + " : " + sharedResource.toString());
				sharedResource.add((int) thisThreadId);
				System.out.println("sharedResource after thead-" + thisThreadId + " : " + sharedResource.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("thread-"+thisThreadId+" released lock");
				mutex.unlock(thisThreadId);
			}
		}
	}

	public static void main(String[] args) {
		final Mutex mutex = new Mutex();
		final List<Integer> sharedResource = new ArrayList<>();
		System.out.println("Initial list: " + sharedResource);
		final Thread myThread1 = new Thread(new MyThread(mutex, sharedResource));
		final Thread myThread2 = new Thread(new MyThread(mutex, sharedResource));
		myThread1.start();
		myThread2.start();
	}

}
