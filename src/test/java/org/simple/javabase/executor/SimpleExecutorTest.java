package org.simple.javabase.executor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class SimpleExecutorTest {

	private static final int NTHREADS = 10;

	private static final ExecutorService fixedThreadPoolexec = Executors
			.newFixedThreadPool(NTHREADS);

	private static final Executor cachedThreadPoolexec = Executors
			.newCachedThreadPool();

	private static final ScheduledExecutorService singleThreadScheduledExecutor = Executors
			.newSingleThreadScheduledExecutor();

	private static final ScheduledExecutorService scheduledThreadPoolExecutor = Executors
			.newScheduledThreadPool(NTHREADS);

	private static final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
			scheduledThreadPoolExecutor);
	
	

	@Test
	public void testFixedThreadPoolExecutor() {
		long start = System.currentTimeMillis();
		int i = 100;
		while (i > 0) {
			final int j = i--;
			Runnable task = new Runnable() {
				public void run() {
					System.out.println("task tun here...,i=" + j);
				}
			};
			fixedThreadPoolexec.execute(task);
		}
		long end = System.currentTimeMillis();
		System.out
				.println("total time for fixed  thread pool:" + (end - start));
	}

	@Test
	public void testCacchedThreadPoolExecutor() {
		long start = System.currentTimeMillis();
		int i = 100000;
		while (i > 0) {
			final int j = i--;
			Runnable task = new Runnable() {
				public void run() {
					// System.out.println("task tun here...,i="+j);
				}
			};
			cachedThreadPoolexec.execute(task);
		}
		long end = System.currentTimeMillis();
		System.out
				.println("total time for cached thread pool:" + (end - start));

	}

	@Test
	public void testSingleThreadScheduledExecutor() {
		long start = System.currentTimeMillis();
		int i = 10;
		while (i > 0) {
			final int j = i--;
			Callable<Integer> task = new Callable<Integer>() {
				public Integer call() throws Exception {
					return j;
				}
			};
			ScheduledFuture<Integer> result = singleThreadScheduledExecutor
					.schedule(task, 10, TimeUnit.SECONDS);
			try {
				Integer r = result.get();
				System.out.println("scheduled task result ,i=" + r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("task is completed...");
		}
		long end = System.currentTimeMillis();
		System.out.println("total time for single scheduled thread:"
				+ (end - start));
	}

	@Test
	public void testscheduledThreadPoolExecutor() {
		long start = System.currentTimeMillis();
		int i = 10;
		while (i > 0) {
			final int j = i--;
			Callable<Integer> task = new Callable<Integer>() {
				public Integer call() throws Exception {
					return j;
				}
			};
			ScheduledFuture<Integer> result = scheduledThreadPoolExecutor
					.schedule(task, 10, TimeUnit.SECONDS);
			try {
				Integer r = result.get();
				System.out.println("scheduled task result ,i=" + r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("task is completed...");
		}
		long end = System.currentTimeMillis();
		System.out.println("time for scheduled thread pool with size "
				+ NTHREADS + ":" + (end - start));
	}

	@Test
	public void testCompletionService() {
		Callable<Integer> task1 = new Callable<Integer>() {
			public Integer call() throws Exception {
				return 1;
			}
		};
		Callable<Integer> task2 = new Callable<Integer>() {
			public Integer call() throws Exception {
				return 1;
			}
		};
		Future<Integer> result1 = completionService.submit(task1);
		Future<Integer> result = completionService.submit(task2);
		try {
			// boolean isCancelled = result1.cancel(true);
			// System.out.println("task is cancelled:" + isCancelled);
			assertThat(result1.isDone()).isEqualTo(false);
			assertThat(result1.isCancelled()).isEqualTo(false);
			Future<Integer> result2 = completionService.take();
			assertThat(result1).isEqualTo(result2);
			assertThat(result1.isDone()).isEqualTo(true);
			assertThat(result2.isDone()).isEqualTo(true);
			System.out.println("completion task1 result is:" + result2.get());
			System.out.println("completion task2 result is:" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testListTask() {
		List<DemoTask> tasks = Lists.newArrayList();
		tasks.add(new DemoTask(2));
		tasks.add(new DemoTask(1));
		tasks.add(new DemoTask(3));
		try {
			List<Future<Integer>> taskResults = fixedThreadPoolexec
					.invokeAll(tasks);
			for (Future<Integer> future : taskResults) {
				System.out.println("Demo task execution result is:"
						+ future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	class DemoTask implements Callable<Integer> {

		public DemoTask(Integer value) {
			super();
			this.value = value;
		}

		private Integer value;

		public Integer call() throws Exception {
			return value;
		}

	}

}
