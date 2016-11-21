package com.model2.mvc.service.product;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	public int addProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public int updateProduct(Product product) throws Exception;
	
	public int removeProduct(int prodNo) throws Exception;
	
	public List<Product> getProductList(Search search) throws Exception;
	
	public int countData(Search search) throws Exception;

}
