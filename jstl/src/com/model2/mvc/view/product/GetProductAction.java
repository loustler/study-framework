package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	public GetProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Product product = new Product();
		ProductService service = new ProductServiceImpl();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		String menu = request.getParameter("menu");
		System.out.println(request.getParameter("menu"));
		
		product = service.getProduct(prodNo);
		
		request.setAttribute("product", product);
		request.setAttribute("menu", menu);
		
		System.out.println("getProduct menu "+menu);
		
		return "forward:/product/getProduct.jsp";
	}

}
