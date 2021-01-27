package com.cneport.threadpooltest;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<MyExecute> {

	@Override
	public MyExecute call() throws Exception {
		System.out.println("i am " + Thread.currentThread().getName());
		Thread.sleep(1000);
		int i = 1/0;
		return null;
	}

}
