package com.cyb.work;

import com.cyb.work.dao.WebSpider;
import com.cyb.work.hssf.ConfigExcelFile;
import com.cyb.work.pool.JwsInitUtils;
import com.cyb.work.pool.TaskPool;
import com.cyb.work.pool.ThreadPool;

public class Main {
	
	public static void main(String[] args) throws Exception {
		long cur = System.currentTimeMillis();
		
		
		new JwsInitUtils("book.douban.com", //网站
						 "utf-8", //编码
						 "C:/", //输出excel文件路径
						 -1); //需要查询的页数   	-1是全量搜索
		
		TaskPool pool = new ThreadPool();
		pool.init();
		
		if(JwsInitUtils.page == -1){
			int i = 0;
			while(true){
				pool.addNewDataToQueue(i*20);
				Thread.sleep(300);
				if(WebSpider.getInterfaces().isRun()){
					break;
				}
				i++;
			}
		}else{
			for (int i = 0; i < JwsInitUtils.page; i++) {
				pool.addNewDataToQueue(i*20);
				Thread.sleep(300);
			}
		}
		System.out.println("--------------");
		
		pool.shutdown();  
        while (true) {  
        	System.out.println("是否全部执行完"+pool.isTerminated());
            if (pool.isTerminated()) {  
                System.out.println("线程池全都结束了！");  
                break;  
            }  
            Thread.sleep(200);  
        }
        System.out.println("导出个数："+WebSpider.getListValue().size());
        
        WebSpider.getInterfaces().listCollections();//评分排序
        
        byte[] res = ConfigExcelFile.getInterfaces().writeExcelByte(WebSpider.getListValue());
        ConfigExcelFile.getInterfaces().writeFile(res, JwsInitUtils.path);
        
        System.out.println("全部耗时耗时："+(System.currentTimeMillis()-cur)+"ms");
	}
}
