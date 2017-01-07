package com.cyb.work.bo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class BookBean {
	
	private String bookName="";//����
	private double grade=0.0;   //����
	private long assess=0;	//��������
	private String author="";  //����
	private String press="";   //������
	private String pressDate="";//��������
	private String price=""; 	 //�۸�

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public long getAssess() {
		return assess;
	}

	public void setAssess(long assess) {
		this.assess = assess;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPressDate() {
		return pressDate;
	}

	public void setPressDate(String pressDate) {
		this.pressDate = pressDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setValue(HSSFRow row,HSSFCellStyle style,int i){
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(i);
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue(this.bookName==null?"":this.bookName);
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue(this.grade);
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue(this.assess);
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue(this.author==null?"":this.author);
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue(this.press==null?"":this.press);
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue(this.pressDate==null?"":this.pressDate);
		cell.setCellStyle(style);
		
		cell = row.createCell(7);
		cell.setCellValue(this.price);
		cell.setCellStyle(style);
	}
}
