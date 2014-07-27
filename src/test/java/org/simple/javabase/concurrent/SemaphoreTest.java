package org.simple.javabase.concurrent;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SemaphoreTest {
	
	private Semaphore semaphore = new Semaphore(1, false);

	private static ThreadPoolExecutor exec = new ThreadPoolExecutor(1, 1, 0L,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));
	static {
		exec.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
	}

	@Test
	public void testSubmitTaskWithSemaphore() throws InterruptedException {
		try {
			for (int i = 0; i < 10; i++) {
//				semaphore.acquire();
				final int j = i;
				Runnable run = new Runnable() {
					public void run() {
						try {
							System.out.println("task " + j);
						} finally {
//							semaphore.release();
						}
					}

				};
				exec.execute(run);
			}
		} catch (RejectedExecutionException e) {
//			semaphore.release();
			e.printStackTrace();
		}

	}
}
