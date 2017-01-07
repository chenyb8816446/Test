package com.cyb.work.pool;

public class JwsInitUtils {
	
	public static String startUrl = "https://";
	public static int page = 100;  //Ò³Êý
	public static String ProgrammingLink = "/tag/%E7%BC%96%E7%A8%8B?type=S&start=";
	public static String chart = "utf-8";
	public static String URL = "book.douban.com";
	public static String path = "C:/";
	public static String MatherSubstrs = "\\d+?.\\d+?";
	public static String MatherSubstrsAssess = "\\d+";
	
	
	public JwsInitUtils(String url,String chart,String path,int page) throws Exception{
		this.URL = url;
		this.chart = chart;
		this.path = path;
		this.page = page;
		
		if(page < -1){
			throw new Exception("page ÊäÈë´íÎó");
		}
	}
}
