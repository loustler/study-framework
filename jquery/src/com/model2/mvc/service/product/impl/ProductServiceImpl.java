package com.model2.mvc.service.product.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao dao;

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return dao.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getProduct(prodNo);
	}

	@Override
	public Map<String, Object> listProduct(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", dao.countData(search));
		map.put("list", dao.getProductList(search));

		return map;
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateProduct(product);
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	@Override
	public int removeProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return dao.removeProduct(prodNo);
	}

}
