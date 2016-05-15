package com.mansourahmed.datastructures.mutex;

import java.util.concurrent.atomic.AtomicLong;

public class Mutex {
	
	private AtomicLong lock = new AtomicLong(0);

	public Boolean lock(final long threadId) {
		return lock.compareAndSet(0, threadId);
	}

	public Boolean unlock(final long threadId) {
		return lock.compareAndSet(threadId, 0);
	}
	
}
