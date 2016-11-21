package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;

public class PurchaseDAO {
	/*
	 * Payment_option Code 000 : 현금결재 001 : 신용카드결재
	 * 
	 * Tran_Status_code 000 : 판매 중 001 : 배송대기 002 : 배송중 003: 배송완료
	 */

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseVO findPurchase(int tranNo) throws Exception {
		PurchaseVO vo = new PurchaseVO();
		UserDAO userDAO = new UserDAO();
		ProductDAO productDAO = new ProductDAO();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT * FROM transaction WHERE ";
		System.out.println("trano length : " + String.valueOf(tranNo).length());
		if (String.valueOf(tranNo).length() < 9) {
			System.out.println(this.getClass().getName()+" findPurchase use tranNo");
			sql += "tran_no = " + tranNo;
		} else {
			System.out.println(this.getClass().getName()+" findPurchase use prodNo :" +(tranNo/10000));
			sql += "prod_no = " + (tranNo / 10000);
		}
		sql += " ORDER BY tran_no ";

		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			System.out.println(this.getClass().getName()+"findPurchase result.next");
			vo.setTranNo(resultSet.getInt(1));
			vo.setPurchaseProd(productDAO.findProduct(resultSet.getInt(2)));
			vo.setBuyer(userDAO.findUser(resultSet.getString(3)));
			System.out.println("db payment "+resultSet.getString(4));
			String paymentOption = resultSet.getString(4).equals("000") ? "현금결재" : "신용카드결재";
			System.out.println("db payment convert vo payment : "+paymentOption);
			vo.setPaymentOption(paymentOption);
			vo.setReceiverName(resultSet.getString(5));
			vo.setReceiverPhone(resultSet.getString(6));
			vo.setDivyAddr(resultSet.getString(7));
			vo.setDivyRequest(resultSet.getString(8));
			String tsc = resultSet.getString(9).equals("000") ? "판매 중"
					: resultSet.getString(9).equals("001") ? "배송대기" : resultSet.getString(9).equals("002") ? "배송 중" : "배송완료";
			vo.setTranCode(tsc);
			vo.setOrderDate(resultSet.getDate(10));
			vo.setDivyDate(resultSet.getString(11));
			
			System.out.println(this.getClass().getName()+"Find vo "+vo);
		}

		connection.close();
		return vo;
	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String userName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT * FROM transaction WHERE buyer_id = '" + userName + "' ";
		if (searchVO.getSearchCondition() != null) {
			int searchCondition = Integer.parseInt(searchVO.getSearchCondition());
			switch (searchCondition) {
			case 0:
				sql += "tran_no = '" + searchVO.getSearchKeyword() + "'";
				break;
			case 1:
				sql += "prod_no = '" + searchVO.getSearchKeyword() + "'";
				break;
			case 2:
				sql += "buyer_id = " + searchVO.getSearchKeyword() + "'";
				break;
			}
			sql += " ORDER BY tran_no ";
		}

		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		resultSet.last();
		int count = resultSet.getRow();
		System.out.println("Purchase Row Count : " + count);
		map.put("Listcount", new Integer(count));
		resultSet.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("search getPage : " + searchVO.getPage());
		System.out.println("search getPageUnit : " + searchVO.getPageUnit());
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (count > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				ProductDAO productDAO = new ProductDAO();
				UserDAO userDAO = new UserDAO();

				purchaseVO.setTranNo(resultSet.getInt(1));
				purchaseVO.setPurchaseProd(productDAO.findProduct(resultSet.getInt(2)));
				purchaseVO.setBuyer(userDAO.findUser(resultSet.getString(3)));
				String paymentOption = resultSet.getString(4).equals("000") ? "현금결재" : "신용카드결재";
				purchaseVO.setPaymentOption(paymentOption);
				purchaseVO.setReceiverName(resultSet.getString(5));
				purchaseVO.setReceiverPhone(resultSet.getString(6));
				purchaseVO.setDivyAddr(resultSet.getString(7));
				purchaseVO.setDivyRequest(resultSet.getString(8));
				String tsc = resultSet.getString(9).equals("000") ? "판매 중"
						: resultSet.getString(9).equals("001") ? "배송대기" : resultSet.getString(9).equals("002") ? "배송 중" : "배송완료";
				purchaseVO.setTranCode(tsc);
				purchaseVO.setOrderDate(resultSet.getDate(10));
				purchaseVO.setDivyDate(resultSet.getString(11));

				list.add(purchaseVO);
				if (!resultSet.next())
					break;
			}

		}

		System.out.println("getlist list size : " + list.size());
		map.put("getlist", list);
		System.out.println("getlist map size : " + map.size());
		connection.close();

		return map;
	}

	public HashMap<String, Object> SaleList(SearchVO searchVO) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT * FROM transaction ";
		if (searchVO.getSearchCondition() != null) {
			int searchCondition = Integer.parseInt(searchVO.getSearchCondition());
			switch (searchCondition) {
			case 0:
				sql += "WHERE tran_no = '" + searchVO.getSearchKeyword() + "'";
				break;
			case 1:
				sql += "WHERE prod_no = '" + searchVO.getSearchKeyword() + "'";
				break;
			case 2:
				sql += "WHERE buyer_no = '" + searchVO.getSearchKeyword() + "'";
				break;
			}
		}
		sql += " ORDER BY tran_no";
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		resultSet.last();
		int count = resultSet.getRow();
		System.out.println("saleList count : " + count);
		map.put("saleCount", new Integer(count));
		resultSet.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO getPage : " + searchVO.getPage());
		System.out.println("searchVO getPageUnit : " + searchVO.getPageUnit());
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (count > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				UserDAO userDAO = new UserDAO();
				ProductDAO productDAO = new ProductDAO();
				PurchaseVO purchaseVO = new PurchaseVO();
				purchaseVO.setTranNo(resultSet.getInt(1));
				purchaseVO.setPurchaseProd(productDAO.findProduct(resultSet.getInt(2)));
				purchaseVO.setBuyer(userDAO.findUser(resultSet.getString(3)));
				String paymentOption = resultSet.getString(4).equals("000") ? "현금결재" : "신용카드결재";
				purchaseVO.setPaymentOption(paymentOption);
				purchaseVO.setReceiverName(resultSet.getString(5));
				purchaseVO.setReceiverPhone(resultSet.getString(6));
				purchaseVO.setDivyAddr(resultSet.getString(7));
				purchaseVO.setDivyRequest(resultSet.getString(8));
				String tsc = resultSet.getString(9).equals("000") ? "판매 중"
						: resultSet.getString(9).equals("001") ? "배송대기" : resultSet.getString(9).equals("002") ? "배송 중" : "배송완료";
				purchaseVO.setTranCode(tsc);
				purchaseVO.setOrderDate(resultSet.getDate(10));
				purchaseVO.setDivyDate(resultSet.getDate(11).toString());

				list.add(purchaseVO);
				if (resultSet.next())
					break;
			}
		}

		System.out.println("salelist list size : " + list.size());
		map.put("salelist", list);
		System.out.println("sallist map size : " + map.size());

		connection.close();
		return map;
	}

	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		connection = DBUtil.getConnection();
		System.out.println("무결성 체크 prodNo : " + purchaseVO.getPurchaseProd().getProdNo());
		System.out.println("무결성 체크 userId : " + purchaseVO.getBuyer().getUserId());

		String sql = "INSERT INTO transaction VALUES (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, '001', SYSDATE, ?) ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		preparedStatement.setString(2, purchaseVO.getBuyer().getUserId());
		preparedStatement.setString(3, purchaseVO.getPaymentOption());
		preparedStatement.setString(4, purchaseVO.getReceiverName());
		preparedStatement.setString(5, purchaseVO.getReceiverPhone());
		preparedStatement.setString(6, purchaseVO.getDivyAddr());
		preparedStatement.setString(7, purchaseVO.getDivyRequest());
		preparedStatement.setString(8, purchaseVO.getDivyDate());
		result = preparedStatement.executeUpdate();

		if (result == 1) {
			System.out.println("insertPurchase Success");
		} else {
			System.out.println("insertPurchase Fail");
		}
		connection.close();
	}

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE transaction SET payment_option = ?, receiver_name = ?,receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ? WHERE  tran_no = ? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, purchaseVO.getPaymentOption());
		preparedStatement.setString(2, purchaseVO.getReceiverName());
		preparedStatement.setString(3, purchaseVO.getReceiverPhone());
		preparedStatement.setString(4, purchaseVO.getDivyAddr());
		preparedStatement.setString(5, purchaseVO.getDivyRequest());
		preparedStatement.setString(6, purchaseVO.getDivyDate());
		preparedStatement.setInt(7, purchaseVO.getTranNo());
		result = preparedStatement.executeUpdate();

		if (result == 1) {
			System.out.println("updatePurchase Success");
		} else {
			System.out.println("updatePurchase Fail");
		}

		connection.close();

	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, purchaseVO.getTranCode());
		preparedStatement.setInt(2, purchaseVO.getTranNo());
		result = preparedStatement.executeUpdate();

		if (result == 1) {
			System.out.println("updateTranCode Succes");
		} else {
			System.out.println("updateTranCode Fail");
		}

		connection.close();

	}

}
