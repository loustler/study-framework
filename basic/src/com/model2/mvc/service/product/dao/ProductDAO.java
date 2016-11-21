package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ProductDAO {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO findProduct(int prodNo) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ProductVO productVO = new ProductVO();
		try {
			connection = DBUtil.getConnection();
			String sql = "SELECT * FROM product WHERE ";
			preparedStatement = connection.prepareStatement(sql + "PROD_NO = ? ");
			preparedStatement.setInt(1, prodNo);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				productVO.setProdNo(resultSet.getInt(1));
				productVO.setProdName(resultSet.getString(2));
				productVO.setProdDetail(resultSet.getString(3));
				productVO.setManuDate(resultSet.getString(4));
				productVO.setPrice(resultSet.getInt(5));
				productVO.setFileName(resultSet.getString(6));
				productVO.setRegDate(resultSet.getDate(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connection.close();
		return productVO;
	}

	public HashMap getProductList(SearchVO searchVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		connection = DBUtil.getConnection();
		String sql = "SELECT * FROM product ";
		if (searchVO.getSearchCondition() != null) {
			int searchCondition = Integer.parseInt(searchVO.getSearchCondition());
			switch (searchCondition) {
			case 0:
				sql += "WHERE prod_no = '" + searchVO.getSearchKeyword() + "' ";
				break;
			case 1:
				sql += "WHERE UPPER(prod_name) LIKE UPPER('%" + searchVO.getSearchKeyword()
						+ "%') OR UPPER(prod_name) LIKE UPPER('" + searchVO.getSearchKeyword()
						+ "%') OR UPPER(prod_name) LIKE UPPER('%" + searchVO.getSearchKeyword() + "') ";
				break;

			case 2:
				sql += "WHERE price = '" + searchVO.getSearchKeyword() + "' ";
				break;
			}
			sql += "ORDER BY prod_no";
		} else {
			sql += "ORDER BY prod_no";
		}
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		resultSet = preparedStatement.executeQuery();
		resultSet.last();
		int count = resultSet.getRow();
		System.out.println("Row count : " + count);

		map.put("count", new Integer(count));
		resultSet.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<Object> purList = new ArrayList<Object>();
		ArrayList<Object> proList = new ArrayList<Object>();

		if (count > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				PurchaseService service = new PurchaseServiceImpl();

				vo.setProdNo(resultSet.getInt(1));
				vo.setProdName(resultSet.getString(2));
				vo.setProdDetail(resultSet.getString(3));
				vo.setManuDate(resultSet.getString(4));
				vo.setPrice(resultSet.getInt(5));
				vo.setFileName(resultSet.getString(6));
				vo.setRegDate(resultSet.getDate(7));

				if(service.getPurchase2(vo.getProdNo()).getBuyer()!=null) {
					PurchaseVO purchaseVO = service.getPurchase2(vo.getProdNo());
					System.out.println(i + 1);
					System.out.println(purchaseVO);
					purList.add(purchaseVO);
					
				}
				proList.add(vo);
					
				if (!resultSet.next())
					break;
			}
		}

		System.out.println("proList size : " + proList.size());
		System.out.println("purList size : " + purList.size());
		map.put("proList", proList);
		map.put("purList", purList);
		System.out.println("map size : " + map.size());
		connection.close();

		return map;
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "INSERT INTO product VALUES (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, productVO.getProdName());
		preparedStatement.setString(2, productVO.getProdDetail());
		preparedStatement.setString(3, productVO.getManuDate());
		preparedStatement.setInt(4, productVO.getPrice());
		preparedStatement.setString(5, productVO.getFileName());
		result = preparedStatement.executeUpdate();

		if (result == 1) {
			System.out.println("Product value Insert Success");
			System.out.println(productVO);
		} else {
			System.out.println("Product value Insert Fail");
		}
		connection.close();

	}

	public void updateProduct(ProductVO productVO) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		connection = DBUtil.getConnection();
		String sql = "UPDATE product SET PROD_NAME = ?, PROD_DETAIL = ?, MANUFACTURE_DAY = ?, PRICE = ?, IMAGE_FILE = ?, REG_DATE = sysdate WHERE prod_no = ? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, productVO.getProdName());
		preparedStatement.setString(2, productVO.getProdDetail());
		preparedStatement.setString(3, productVO.getManuDate());
		preparedStatement.setInt(4, productVO.getPrice());
		preparedStatement.setString(5, productVO.getFileName());
		preparedStatement.setInt(6, productVO.getProdNo());
		result = preparedStatement.executeUpdate();

		if (result == 1) {
			System.out.println("Update Success");
			System.out.println(productVO);
		} else {
			System.out.println("Product Update Fail");
		}
		connection.close();

	}

}
