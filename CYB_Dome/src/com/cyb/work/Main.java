package com.cyb.work;

import com.cyb.work.dao.WebSpider;
import com.cyb.work.hssf.ConfigExcelFile;
import com.cyb.work.pool.JwsInitUtils;
import com.cyb.work.pool.TaskPool;
import com.cyb.work.pool.ThreadPool;

public class Main {
	
	public static void main(String[] args) throws Exception {
		long cur = System.currentTimeMillis();
		
		
		new JwsInitUtils("book.douban.com", //��վ
						 "utf-8", //����
						 "C:/", //���excel�ļ�·��
						 -1); //��Ҫ��ѯ��ҳ��   	-1��ȫ������
		
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
        	System.out.println("�Ƿ�ȫ��ִ����"+pool.isTerminated());
            if (pool.isTerminated()) {  
                System.out.println("�̳߳�ȫ�������ˣ�");  
                break;  
            }  
            Thread.sleep(200);  
        }
        System.out.println("����������"+WebSpider.getListValue().size());
        
        WebSpider.getInterfaces().listCollections();//��������
        
        byte[] res = ConfigExcelFile.getInterfaces().writeExcelByte(WebSpider.getListValue());
        ConfigExcelFile.getInterfaces().writeFile(res, JwsInitUtils.path);
        
        System.out.println("ȫ����ʱ��ʱ��"+(System.currentTimeMillis()-cur)+"ms");
	}
}
