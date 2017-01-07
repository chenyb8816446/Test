package com.cyb.work.pool;

import java.util.List;

import org.jsoup.nodes.Document;

import com.cyb.work.bo.BookBean;
import com.cyb.work.dao.WebSpider;

public class TaskExecuter {
	public final static TaskExecuter instance = new TaskExecuter();

	private TaskExecuter() {	}

	public void execute(int i) {
		String url = JwsInitUtils.startUrl + JwsInitUtils.URL + JwsInitUtils.ProgrammingLink + i;
		
		Document document = WebSpider.getInterfaces().getPageJsoup(url);
		
		List<BookBean> list = WebSpider.getInterfaces().getDocument(document);
		
		if(list.size() == 0 && JwsInitUtils.page == -1){ WebSpider.getInterfaces().setRun(true);};
		
		System.out.println("线程："+Thread.currentThread().getName()+ "---匹配个数："+list.size());
		
		WebSpider.setListValue(list);
	}
}
