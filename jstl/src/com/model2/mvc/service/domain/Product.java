package com.model2.mvc.service.domain;

import java.io.Serializable;
import java.sql.Date;

public class Product {
	
	private int prodNo;
	private String prodName;
	private String prodDetail;
	private String manuDay;
	private int price;
	private String fileName;
	private Date regDate;
	private String proTranCode;

	public Product() {
		// TODO Auto-generated constructor stub
	}


	public int getProdNo() {
		return prodNo;
	}


	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}


	public String getProdName() {
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getProdDetail() {
		return prodDetail;
	}


	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}



	public String getManuDay() {
		return manuDay;
	}


	public void setManuDay(String manuDay) {
		this.manuDay = manuDay;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getProTranCode() {
		return proTranCode;
	}


	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [prodNo=");
		builder.append(prodNo);
		builder.append(", prodName=");
		builder.append(prodName);
		builder.append(", prodDetail=");
		builder.append(prodDetail);
		builder.append(", manuDay=");
		builder.append(manuDay);
		builder.append(", price=");
		builder.append(price);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", proTranCode=");
		builder.append(proTranCode);
		builder.append("]");
		return builder.toString();
	}

	

}
