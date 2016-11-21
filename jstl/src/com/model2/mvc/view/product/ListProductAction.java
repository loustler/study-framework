package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	public ListProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		ProductService service = new ProductServiceImpl();
		Search search = new Search();
		
		String menu = request.getParameter("menu");
		
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 현재페이지
		search.setCurrentPage(currentPage);

		// web.xml에서 추출
		int pageUnit = Integer.parseInt(this.getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("pageSize"));

		search.setPageSize(pageSize);
		
		System.out.println("searchCondition"+request.getParameter("searchCondition"));
		
		if(request.getParameter("searchKeyword")!=null) {
			String searchcondition = request.getParameter("searchCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			search.setSearchKeyword(searchKeyword);
			search.setSearchCondition(searchcondition);
		} 
		
		
		Map<String, Object> map = service.listProduct(search);

		// 출력할 page set
		Page resultPage = new Page(currentPage, ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		request.setAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}

}
