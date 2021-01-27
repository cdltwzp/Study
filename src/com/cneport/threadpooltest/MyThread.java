package com.cneport.threadpooltest;

public class MyThread implements Runnable {

	@Override
	public void run() {
		System.out.println("i am " + Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
