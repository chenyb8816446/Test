package com.cyb.work.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.work.bo.BookBean;
import com.cyb.work.pool.JwsInitUtils;

public class WebSpider {
	private boolean isRun = false;
	private static List<BookBean> list = null;
	private static WebSpider wbs = null;
	private WebSpider() {

	}
	
	public synchronized boolean isRun() {
		return isRun;
	}
	public synchronized void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	

	public synchronized static List<BookBean> getListValue() {
		return list == null ? null : list;
	}
	public synchronized static void setListValue(List<BookBean> addList) {
		if (list == null) {
			list = new ArrayList<BookBean>();
		}
		list.addAll(addList);
	}
	public int getListtSize() {
		if (list == null) {
			list = new ArrayList<BookBean>();
		}
		return list.size();
	}
	public void listCollections() {
		System.out.println("数据排序！");
		Collections.sort(list, new Comparator<BookBean>() {
			public int compare(BookBean o1, BookBean o2) {
				double i1 = o1.getGrade();
				double i2 = o2.getGrade();
				if (i1 > i2) {
					return -1;
				} else if (i1 == i2) {
					return 0;
				} else {
					return 1;
				}
			};
		});
	}
	public synchronized static WebSpider getInterfaces() {
		if (wbs == null) {
			wbs = new WebSpider();
		}
		return wbs;
	}

	public String getURLContent(String urlStr, String chart) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(chart)));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				sb.append(temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public List<BookBean> getMatherSubstrs(String sStr, String regexStr) {
		Pattern p = Pattern.compile(regexStr);
		Matcher m = p.matcher(sStr);
		List<BookBean> lis = new ArrayList<BookBean>();
		while (m.find()) {
			BookBean b = new BookBean();
			b.setBookName(m.group(1));
			b.setGrade(Double.parseDouble(m.group(2)));
			b.setAuthor(m.group(3));

			lis.add(b);
		}

		if (lis.size() == 0) {
			this.isRun = true;
		}
		System.out.println("匹配到个数：" + lis.size());
		return lis;
	}
	
	
	public static String getMatherSubstrsByStr(String sStr, String regexStr) {
		if(sStr!=null){ sStr = sStr.trim();}
		Pattern p = Pattern.compile(regexStr);
		Matcher m = p.matcher(sStr);
		while (m.find()) {
			return m.group();
		}
		return null;
	}
	
	/**
	 * Jsoup获取页面源码
	 * 
	 * @param url
	 * @return
	 */
	public Document getPageJsoup(String url) {
		Document doc = null;
		try {
			doc = Jsoup
					.connect(url.toString())
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language",
							"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
					.timeout(99999).get();
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return doc;
	}
	
	public List<BookBean> getDocument(Document document){
		Elements lists = document.select("li.subject-item");
		List<BookBean> listBe = new ArrayList<BookBean>();
		for (Element element : lists) {
			BookBean b = new BookBean();
			b.setBookName(element.select("div.info > h2 > a").attr("title"));
//			System.out.println("书名："+element.select("div.info > h2 > a").attr("title"));
			
			setValue(element.select("div.pub").text(), b);
//			System.out.println(element.select("div.pub").text());
			
			String tempPl=element.select("span.pl").text();
			b.setAssess(Long.parseLong(getMatherSubstrsByStr(tempPl, JwsInitUtils.MatherSubstrsAssess)==null?"0":getMatherSubstrsByStr(tempPl, JwsInitUtils.MatherSubstrsAssess)));
//			System.out.println("评价人数："+Integer.valueOf(getMatherSubstrsByStr(tempPl.substring(1, tempPl.length()-4), JwsInitUtils.MatherSubstrsAssess)));
			
			
			String d = element.select("span.rating_nums").text();
			b.setGrade(Double.parseDouble(d==null||d.equals("")?"0":d));
//			System.out.println("评分："+d);
//			System.out.println();
			listBe.add(b);
		}
		return listBe;
	}
	
	private static void setValue(String sStr,BookBean b){
		if(sStr == null || sStr.equals(""))return;
		String[] u = sStr.split("/");
		if(u == null || u.length ==0){
			return;
		}else if(u.length == 5){
			b.setAuthor(u[0]);
			b.setPress(u[2]);
			b.setPressDate(u[3]);
			b.setPrice(u[4]);
		}else if(u.length == 4){
			b.setAuthor(u[0]);
			b.setPress(u[1]);
			b.setPressDate(u[2]);
			b.setPrice(u[3]);
		}else{
			return;
		}
	}
	
}
