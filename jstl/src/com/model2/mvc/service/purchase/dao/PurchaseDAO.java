package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}

	public Purchase findPurchase(int tranNo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Purchase purchase = null;

		connection = DBUtil.getConnection();
		int length = String.valueOf(tranNo).length();
		System.out.println("findPurchase tranNo length : " + length);
		String sql = "SELECT * FROM transaction WHERE tran_no = ? ";
		if (length > 5) {
			sql = "SELECT * FROM transaction WHERE prod_no = ?";
			tranNo = tranNo / 10000;
		}
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, tranNo);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			purchase = new Purchase();
			ProductService productService = new ProductServiceImpl();
			UserService userService = new UserServiceImpl();
			while (true) {
				purchase.setTranNo(resultSet.getInt("tran_no"));
				purchase.setPurchaseProd(productService.getProduct(resultSet.getInt("prod_no")));
				purchase.setBuyer(userService.getUser(resultSet.getString("buyer_id")));
				purchase.setPaymentOption(resultSet.getString("payment_option"));
				purchase.setReceiverName(resultSet.getString("receiver_name"));
				purchase.setReceiverPhone(resultSet.getString("receiver_phone"));
				purchase.setDlvyAddr(resultSet.getString("demailaddr"));
				purchase.setDlvyReq(resultSet.getString("dlvy_request"));
				String tranCode = resultSet.getString("tran_status_code") == null
						|| resultSet.getString("tran_status_code").trim().equals("0") ? "판매 중"
								: resultSet.getString("tran_status_code").trim().equals("1") ? "배송대기"
										: resultSet.getString("tran_status_code").trim().equals("2") ? "배송 중" : "배송완료";
				purchase.setTranCode(tranCode);
				purchase.setOrderDate(resultSet.getDate("order_data"));
				purchase.setDlvyDate(resultSet.getString("dlvy_date"));

				if (!resultSet.next())
					break;
			}

		} else {
			System.out.println("findPurchase resultset next false");
		}

		return purchase;
	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Object> map = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT * FROM transaction WHERE buyer_id = '" + buyerId + "' ORDER BY tran_no";

		System.out.println("before in count : " + sql);
		int count = DBUtil.getTotalCount(sql);
		sql = DBUtil.makeCurrentPageSql(sql, search);
		System.out.println("after makeCurrentPageSql : " + sql);

		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			map = new HashMap<String, Object>();
			List<Purchase> list = new ArrayList<Purchase>();
			while (true) {
				Purchase purchase = new Purchase();
				ProductService productService = new ProductServiceImpl();
				UserService userService = new UserServiceImpl();

				purchase.setTranNo(resultSet.getInt("tran_no"));
				purchase.setPurchaseProd(productService.getProduct(resultSet.getInt("prod_no")));
				purchase.setBuyer(userService.getUser(resultSet.getString("buyer_id")));
				purchase.setPaymentOption(resultSet.getString("payment_option"));
				purchase.setReceiverName(resultSet.getString("receiver_name"));
				purchase.setReceiverPhone(resultSet.getString("receiver_phone"));
				purchase.setDlvyAddr(resultSet.getString("demailaddr"));
				purchase.setDlvyReq(resultSet.getString("dlvy_request"));
				System.out.println(resultSet.getString("tran_status_code"));
				String tranCode = resultSet.getString("tran_status_code") == null
						|| resultSet.getString("tran_status_code").trim().equals("0") ? "판매 중"
								: resultSet.getString("tran_status_code").trim().equals("1") ? "배송대기"
										: resultSet.getString("tran_status_code").trim().equals("2") ? "배송 중" : "배송완료";
				purchase.setTranCode(tranCode);
				purchase.setOrderDate(resultSet.getDate("order_data"));
				purchase.setDlvyDate(resultSet.getString("dlvy_date"));

				list.add(purchase);
				System.out.println(purchase);

				if (!resultSet.next())
					break;
			}

			System.out.println("getPurchaseList list size : " + list.size());
			System.out.println("getPurchaseList count : " + count);
			map.put("list", list);
			map.put("count", count);

		} else {
			System.out.println("getPurchaseList ResultSet False");
		}

		return map;
	}

	public Map<String, Object> getSaleList(Search search) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Object> map = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, t.tran_no, t.tran_status_code FROM product p, transaction t WHERE p.prod_no = t.prod_no(+)  ";
		System.out.println("searchKeyword : " + search.getSearchKeyword());
		System.out.println("searchCondition : " + search.getSearchCondition());
		if (search.getSearchKeyword() != null) {
			int searchCondition = Integer.parseInt(search.getSearchCondition());
			switch (searchCondition) {
			case 0:
				System.out.println("case 0");
				sql += "AND p.prod_no = " + search.getSearchKeyword();
				break;
			case 1:
				System.out.println("case 1");
				sql += "AND (UPPER(p.prod_name) LIKE UPPER('%" + search.getSearchKeyword()
						+ "%') OR UPPER(p.prod_name) LIKE UPPER('%" + search.getSearchKeyword()
						+ "') OR UPPER(p.prod_name) LIKE UPPER('" + search.getSearchKeyword() + "%') OR p.prod_name = '"
						+ search.getSearchKeyword() + "')";
				break;
			case 2:
				System.out.println("case 2");
				sql += "AND t.tran_no = " + search.getSearchKeyword();
				break;
			}
		}
		sql += " ORDER BY p.prod_no";

		System.out.println("before in count : " + sql);
		int count = DBUtil.getTotalCount(sql);
		sql = DBUtil.makeCurrentPageSql(sql, search);
		System.out.println("after makeCurrentPageSql : " + sql);
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			map = new HashMap<String, Object>();
			List<Product> list = new ArrayList<Product>();
			while (true) {
				Product product = new Product();
				product.setProdNo(resultSet.getInt("prod_no"));
				product.setProdName(resultSet.getString("prod_name"));
				product.setProdDetail(resultSet.getString("prod_detail"));
				product.setManuDay(CommonUtil.toDateStr(resultSet.getString("manufacture_day")));
				product.setPrice(resultSet.getInt("price"));
				String tranCode = resultSet.getString("tran_status_code") == null
						|| resultSet.getString("tran_status_code").trim().equals("0") ? "판매 중"
								: resultSet.getString("tran_status_code").trim().equals("1") ? "배송대기"
										: resultSet.getString("tran_status_code").trim().equals("2") ? "배송 중" : "배송완료";
				product.setProTranCode(tranCode);

				list.add(product);
				if (!resultSet.next())
					break;
			}
			System.out.println("getSaleList list Size : " + list.size());
			System.out.println("getSaleList count : " + count);
			map.put("list", list);
			map.put("count", count);
		} else {
			System.out.println("getSaleList ResultSet False");
		}

		return map;
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "INSERT INTO transaction VALUES (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, '1', SYSDATE, ?) ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, purchase.getPurchaseProd().getProdNo());
		preparedStatement.setString(2, purchase.getBuyer().getUserId());
		preparedStatement.setString(3, purchase.getPaymentOption());
		preparedStatement.setString(4, purchase.getReceiverName());
		preparedStatement.setString(5, purchase.getReceiverPhone());
		preparedStatement.setString(6, purchase.getDlvyAddr());
		preparedStatement.setString(7, purchase.getDlvyReq());
		preparedStatement.setDate(8, purchase.getOrderDate());

		result = preparedStatement.executeUpdate();
		System.out.println("insertPurchase " + ((result == 1) ? "success" : "fail"));

	}

	public void updatePurchase(Purchase purchase) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE transaction SET payment_option = ?, receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ? WHERE tran_no = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, purchase.getPaymentOption());
		preparedStatement.setString(2, purchase.getReceiverName());
		preparedStatement.setString(3, purchase.getReceiverPhone());
		preparedStatement.setString(4, purchase.getDlvyAddr());
		preparedStatement.setString(5, purchase.getDlvyReq());
		preparedStatement.setString(6, purchase.getDlvyDate());
		preparedStatement.setInt(7, purchase.getTranNo());
		result = preparedStatement.executeUpdate();

		System.out.println("updatePurchase " + ((result == 1) ? "success" : "fail"));

	}

	public void updateTranCode(Purchase purchase) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, purchase.getTranCode());
		preparedStatement.setInt(2, purchase.getTranNo());

		result = preparedStatement.executeUpdate();
		System.out.println("updateTranCode " + ((result == 1) ? "success" : "fail"));

	}

}
