package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDAO {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public Product findProduct(int prodNo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Product product = null;

		connection = DBUtil.getConnection();
//		String sql = "SELECT * FROM product WHERE prod_no = ? ";
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.reg_date, p.image_file, t.tran_status_code FROM product p, transaction t WHERE p.prod_no = t.prod_no(+) AND p.prod_no = ? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, prodNo);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			product = new Product();
			product.setProdNo(resultSet.getInt("prod_no"));
			product.setProdName(resultSet.getString("prod_name"));
			product.setProdDetail(resultSet.getString("prod_detail"));
			product.setManuDay(resultSet.getString("manufacture_day"));
			product.setPrice(resultSet.getInt("price"));
			product.setFileName(resultSet.getString("image_file"));
			product.setRegDate(resultSet.getDate("reg_date"));
			product.setProTranCode(CommonUtil.null2str(resultSet.getString("tran_status_code")).trim());

		}

		System.out.println(this.getClass().getName() + " getproduct " + (product == null ? "null" : "sucess"));
		connection.close();

		return product;
	}

	public void insertProduct(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "INSERT INTO product VALUES (seq_product_prod_no.nextval, ?, ?, ?, ?, ?, sysdate)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, product.getProdName());
		preparedStatement.setString(2, product.getProdDetail());
		preparedStatement.setString(3, CommonUtil.dateToString(product.getManuDay()));
		preparedStatement.setInt(4, product.getPrice());
		preparedStatement.setString(5, product.getFileName());
		result = preparedStatement.executeUpdate();

		System.out.println(this.getClass().getName() + "insertProduct " + (result == 1 ? "success" : "fail"));

	}

	public Map<String, Object> getProductList(Search search) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String, Object> map = null;

		connection = DBUtil.getConnection();
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, t.tran_status_code FROM product p, transaction t WHERE p.prod_no = t.prod_no(+) ";

		System.out.println("searchKeyword : " + search.getSearchKeyword());
		System.out.println("searchCondition : " + search.getSearchCondition());
		if (search.getSearchKeyword() != null) {
			int searchCondition = Integer.parseInt(search.getSearchCondition());
			switch (searchCondition) {
			case 0:
				sql += "AND p.prod_no = " + search.getSearchKeyword();
				break;
			case 1:
				sql += "AND (UPPER(p.prod_name) LIKE UPPER('%" + search.getSearchKeyword()
						+ "%') OR UPPER(p.prod_name) LIKE UPPER('%" + search.getSearchKeyword()
						+ "') OR UPPER(p.prod_name) LIKE UPPER('" + search.getSearchKeyword() + "%') OR p.prod_name = '"
						+ search.getSearchKeyword() + "')";
				break;
			case 2:
				sql += "AND p.price = " + search.getSearchKeyword();
				break;
			}
		}
		sql += " ORDER BY p.prod_no";

		System.out.println("before in count : " + sql);
		int totalCount = DBUtil.getTotalCount(sql);
		System.out.println("listProduct totalCount : " + totalCount);

		sql = DBUtil.makeCurrentPageSql(sql, search);

		System.out.println("getProductList sql : " + sql);
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			List<Product> list = new ArrayList<Product>();
			map = new HashMap<String, Object>();
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
			System.out.println("getProductList list size : " + list.size());
			map.put("totalCount", totalCount);
			map.put("list", list);
		} else {
			System.out.println(this.getClass().getName() + "getProductList resultSet False");
		}
		System.out.println(this.getClass().getName() + "getProductList : " + (map == null ? "fail" : "success"));

		return map;
	}

	public void updateProduct(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE product SET prod_name = ?, prod_detail = ?, manufacture_day = ?, price = ?, image_file = ?, reg_date = ? WHERE prod_no = ? ";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, product.getProdName());
		preparedStatement.setString(2, product.getProdDetail());
		preparedStatement.setString(3, product.getManuDay());
		preparedStatement.setInt(4, product.getPrice());
		preparedStatement.setString(5, product.getFileName());
		preparedStatement.setDate(6, product.getRegDate());
		preparedStatement.setInt(7, product.getProdNo());

		result = preparedStatement.executeUpdate();

		System.out.println(this.getClass().getName() + " updateProduct " + (result == 1 ? "Success" : "Fail"));

	}

}
