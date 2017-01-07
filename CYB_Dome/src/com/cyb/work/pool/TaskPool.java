package com.cyb.work.pool;

import java.util.concurrent.ThreadPoolExecutor;

public abstract class TaskPool {
	public abstract ThreadPoolExecutor init() throws Exception;

	public abstract void addNewDataToQueue(int i);
	
	public abstract void shutdown();
	
	public abstract boolean isTerminated();
}
