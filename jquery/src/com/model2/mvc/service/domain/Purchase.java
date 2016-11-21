package com.model2.mvc.service.domain;

import java.io.Serializable;
import java.sql.Date;

public class Purchase implements Serializable{
	private int tranNo;
	private Product purchaseProd;
	private User buyer;
	private String paymentOption;
	private String receiverName;
	private String receiverPhone;
	private String dlvyAddr;
	private String dlvyReq;
	private String tranCode;
	private Date orderDate;
	private String dlvyDate;
	
	public Purchase() {
		// TODO Auto-generated constructor stub
	}

	public int getTranNo() {
		return tranNo;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}

	public Product getPurchaseProd() {
		return purchaseProd;
	}

	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getDlvyAddr() {
		return dlvyAddr;
	}

	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}

	public String getDlvyReq() {
		return dlvyReq;
	}

	public void setDlvyReq(String dlvyReq) {
		this.dlvyReq = dlvyReq;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDlvyDate() {
		return dlvyDate;
	}

	public void setDlvyDate(String dlvyDate) {
		this.dlvyDate = dlvyDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Purchase [tranNo=");
		builder.append(tranNo);
		builder.append(", purchaseProd=");
		builder.append(purchaseProd);
		builder.append(", buyer=");
		builder.append(buyer);
		builder.append(", paymentOption=");
		builder.append(paymentOption);
		builder.append(", receiverName=");
		builder.append(receiverName);
		builder.append(", receiverPhone=");
		builder.append(receiverPhone);
		builder.append(", dlvyAddr=");
		builder.append(dlvyAddr);
		builder.append(", dlvyReq=");
		builder.append(dlvyReq);
		builder.append(", tranCode=");
		builder.append(tranCode);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", dlvyDate=");
		builder.append(dlvyDate);
		builder.append("]");
		return builder.toString();
	}
	
	

}
