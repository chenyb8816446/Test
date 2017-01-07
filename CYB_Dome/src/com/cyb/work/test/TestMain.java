package com.cyb.work.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;

import org.junit.Test;

import com.cyb.work.bo.BookBean;
import com.cyb.work.dao.WebSpider;
import com.cyb.work.hssf.ConfigExcelFile;
import com.cyb.work.pool.TaskPool;
import com.cyb.work.pool.ThreadPool;

public class TestMain {
	
//	public static void main(String[] args) throws Exception{}

	@Test
	public void testPool() throws Exception{

		TaskPool pool = new ThreadPool();
		pool.init();
		
		int i = 1;
		while(true){
			if(!WebSpider.getInterfaces().isRun()){
				i++;
				pool.addNewDataToQueue(i);
			}
			Thread.sleep(1000);
			System.out.println("存储的个数："+WebSpider.getInterfaces().getListtSize());
			
			
			if(WebSpider.getInterfaces().isRun()){
				Thread.sleep(5000);
				break;
			}
		}
		WebSpider.getInterfaces().listCollections();
		
		ConfigExcelFile conf = ConfigExcelFile.getInterfaces();
		
		byte[] res = conf.writeExcelByte(WebSpider.getInterfaces().getListValue());
		conf.writeFile(res, "c:/");
	
	}
	
	@Test
	public void createExcel() throws Exception{
		List<BookBean> listBo = new ArrayList<BookBean>();
		for (int i = 0; i < 1000; i++) {
			BookBean b = new BookBean();
			b.setBookName("java编程思想");
			b.setGrade(11.11);
			b.setAssess(100000);
			b.setAuthor("张三");
			b.setPress("亚信出版社");
			b.setPressDate("2011-01-01");
			b.setPrice("100元");
			listBo.add(b);
		}
		ConfigExcelFile conf = ConfigExcelFile.getInterfaces();
		byte[] res = conf.writeExcelByte(listBo);
		conf.writeFile(res, "c:/");
	}
}
