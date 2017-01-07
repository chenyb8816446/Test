package com.cyb.work.hssf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cyb.work.bo.BookBean;

public class ConfigExcelFile {
	
	private static ConfigExcelFile cefile= null;
	
	private ConfigExcelFile(){
		
	}
	
	public synchronized static ConfigExcelFile getInterfaces(){
		if(cefile == null){
			cefile = new ConfigExcelFile();
		}
		return cefile;
	}
	
	public static String[] getTitles(){
		return new String[] {"���","����","����","��������","����","������","��������","�۸�"};
	}
	
	public byte[] writeExcelByte(List<BookBean> listBo) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ
		HSSFSheet sheet = wb.createSheet("�����ɸߵ���ͼ������");
		writesheet(sheet, style, getTitles(), listBo);
		ByteArrayOutputStream bos=null;
		try{
			bos=new ByteArrayOutputStream();
			wb.write(bos);
			return bos.toByteArray();
		}finally{
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writesheet(HSSFSheet sheet, HSSFCellStyle style, String[] titles, List<BookBean> list) throws Exception {
		HSSFRow row = sheet.createRow((int) 0);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}

		for (int i = 0; i < list.size(); i++) {
			BookBean m = list.get(i);
			row = sheet.createRow(i + 1);
			m.setValue(row, style,i+1);
		}
	}
	
	/*�����ļ�*/
	public void writeFile(byte[] b,String path){
		long cur = System.currentTimeMillis();
		FileOutputStream fos = null ;
		ByteArrayInputStream bis = null;
		try {
			bis = new ByteArrayInputStream(b);
			int len;
			byte[] buffer = new byte[128];
			fos = new FileOutputStream(path+"export_"+ConfigExcelFile.formatCurrentDate("yyyyMMddHHmmss")+".xls");
			while ((len = bis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				bis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("����xls ���ɺ�ʱ��"+(System.currentTimeMillis()-cur)+"ms");
	}
	
	
	public static String formatCurrentDate(String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(getDate());
	}
	
	public static Date getDate(){
		return Calendar.getInstance().getTime();
	}
}
