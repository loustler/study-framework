package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ListProductAction extends Action {

	public ListProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		SearchVO searchVO = new SearchVO();
		PurchaseVO purchaseVO = null;
		ProductVO productVO = null;
		
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println("ListProductAction page : " +page);

		searchVO.setPage(page);
		
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		String pageUnit = getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		ProductService service = new ProductServiceImpl();
		Map<String, Object> map = service.getProductList(searchVO);
		Integer count = (Integer)map.get("count");
		List<PurchaseVO> purList = (ArrayList<PurchaseVO>)map.get("purList");
		List<ProductVO> proList = (ArrayList<ProductVO>)map.get("proList");

		
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("proList", proList);
		request.setAttribute("purList", purList);
		request.setAttribute("count", count);
		System.out.println(this.getClass().getName()+" setAtt search vo : " +searchVO);
		System.out.println(this.getClass().getName()+" setAtt proList : " +proList);
		System.out.println(this.getClass().getName()+" setAtt purList : " +purList);
		System.out.println(this.getClass().getName()+" setAtt count : " +count);

		return "forward:/product/listProduct.jsp";
	}

}
