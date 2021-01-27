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
		

		//newFixedThreadPool ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.submit(new MyThread());
			Future<MyExecute> submit = newFixedThreadPool.submit(new MyCallable());//submit���Բ���ִ�й��̵��쳣
			
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
		//newScheduledThreadPool ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ�С�
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

		//newScheduledThreadPool.schedule(new MyThread(), 10, TimeUnit.SECONDS);  //�ȴ�10���ʼִ���̣߳�ִֻ��һ��
		//newScheduledThreadPool.scheduleAtFixedRate(new MyThread(),5,15,TimeUnit.SECONDS); //�ȴ�5���ʼִ���̣߳����15��������ִ�У����ȱ�֤����ִ�е�Ƶ��
		newScheduledThreadPool.scheduleWithFixedDelay(new MyThread(),5, 10, TimeUnit.SECONDS);//�ȴ�5���ʼִ���̣߳�ִ�����10�룬���ȱ�֤����ִ�еļ��	
	}

	private static void method2() {
		//newFixedThreadPool ����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			newFixedThreadPool.execute(new MyThread());
		}
	}

	private static void method1() {
		//newCachedThreadPool����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳�
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			newCachedThreadPool.execute(new MyThread());
		}
	}

}
