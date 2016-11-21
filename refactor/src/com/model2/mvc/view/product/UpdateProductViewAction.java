package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductViewAction extends Action {

	public UpdateProductViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Product  product = new Product();
		ProductService service = new ProductServiceImpl();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		product = service.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		
		return "forward:/product/updateProductView.jsp";
	}

}
