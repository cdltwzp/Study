package com.cneport.threadpooltest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		//method1();
		
		//method2();

		//method3();
		
		//method4();
		

		//newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.submit(new MyThread());
			Future<MyExecute> submit = newFixedThreadPool.submit(new MyCallable());//submit可以捕获执行过程的异常
			
			try {
				MyExecute myExecute = submit.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e + "-----");
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e + "*******");

			}
			
		}
	
	}

	private static void method4() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		List<MyCallable> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new MyCallable());
		}
		try {
			newFixedThreadPool.invokeAll(list);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newFixedThreadPool.shutdown();
	}

	private static void method3() {
		//newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

		//newScheduledThreadPool.schedule(new MyThread(), 10, TimeUnit.SECONDS);  //等待10秒后开始执行线程，只执行一次
		//newScheduledThreadPool.scheduleAtFixedRate(new MyThread(),5,15,TimeUnit.SECONDS); //等待5秒后开始执行线程，间隔15秒周期性执行，优先保证任务执行的频率
		newScheduledThreadPool.scheduleWithFixedDelay(new MyThread(),5, 10, TimeUnit.SECONDS);//等待5秒后开始执行线程，执行完等10秒，优先保证任务执行的间隔	
	}

	private static void method2() {
		//newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.execute(new MyThread());
		}
	}

	private static void method1() {
		//newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			newCachedThreadPool.execute(new MyThread());
		}
	}

}
