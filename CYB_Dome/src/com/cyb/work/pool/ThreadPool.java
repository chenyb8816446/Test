package com.cyb.work.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool extends TaskPool{
	
	private static ThreadPoolExecutor pool = null;
	private static BlockingQueue<Runnable> queue = null;
	
	private void initThreadPool(int corePoolSize, int maximumPoolSize, long aliveTime) throws Exception {
		queue = new LinkedBlockingQueue<Runnable>();
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, aliveTime, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	public void addNewDataToQueue(int i) {
		pool.execute(new TaskRunnable(i));
	}
	
	public synchronized ThreadPoolExecutor init() throws Exception {
		if(pool==null&&queue==null){
			System.out.println("初始化装载线程池！");
			initThreadPool(30, 60, 60);
		}
		return pool;
	}

	public void shutdown() {
		 pool.shutdown();
	}

	public boolean isTerminated() {
		return pool.isTerminated();
	}
	
}