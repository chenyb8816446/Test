package com.cyb.work.pool;

public class TaskRunnable implements Runnable {
	private int i = -1;   //ҳ��

	public TaskRunnable(int i) {
		this.i = i;
	}

	public void run() {
		TaskExecuter.instance.execute(this.i);
	}
}
