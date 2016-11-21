package com.model2.mvc.view.product;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action {

	public UpdateProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		StringBuilder manuDate = new StringBuilder();
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println("prodNo 통과 ");
		System.out.println(request.getParameter("prodNo"));
		System.out.println(request.getParameter("manuDate"));
		System.out.println(request.getParameter("prodName"));
		System.out.println(request.getParameter("prodDetail"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("fileName"));

		if (request.getParameter("manuDate").toString().length() > 8) {
			System.out.println("manuDate length above 8 Start..");
			String[] manuDateTemp = request.getParameter("manuDate").split("-");
			manuDate.append(manuDateTemp[0]);
			manuDate.append(manuDateTemp[1]);
			manuDate.append(manuDateTemp[2]);
		} else {
			manuDate.append(request.getParameter("manuDate"));
		}
		System.out.println("manudate 통과");
		ProductVO vo = new ProductVO();

		vo.setProdNo(prodNo);
		vo.setProdName(request.getParameter("prodName"));
		vo.setProdDetail(request.getParameter("prodDetail"));

		vo.setManuDate(manuDate.toString());
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setFileName(request.getParameter("fileName"));

		ProductDAO dao = new ProductDAO();
		dao.updateProduct(vo);

		System.out.println("updateProductAction vo : "+vo);
		request.setAttribute("productVO", vo);

		return "forward:/product/getProduct.jsp";
	}

}
