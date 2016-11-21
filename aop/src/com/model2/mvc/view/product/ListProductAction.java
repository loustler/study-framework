package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.view.ProductDI;

public class ListProductAction extends Action {

	public ListProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ProductService service = ProductDI.getService();
		Search search = new Search();

		String menu = request.getParameter("menu");

		int currentPage = 1;
		
		if (request.getParameter("currentPage") != null && request.getParameter("currentPage") != "") {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		if (request.getParameter("sort") != null && request.getParameter("sort") != "0") {
			search.setSort(Integer.parseInt(request.getParameter("sort")));
			System.out.println(search.getSort());
		}

		// 현재페이지
		search.setCurrentPage(currentPage);

		// web.xml에서 추출
		int pageUnit = Integer.parseInt(this.getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("pageSize"));

		search.setPageSize(pageSize);

		if (request.getParameter("sort") != null) {
			search.setSearchCondition(request.getParameter("sortPrice"));
		}

		if (request.getParameter("searchKeyword") != null && request.getParameter("searchKeyword") != "" && request.getParameter("searchCondition")!=null) {
			String searchcondition = request.getParameter("searchCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			search.setSearchKeyword(searchKeyword);
			search.setSearchCondition(searchcondition);
		}

		Map<String, Object> map = service.listProduct(search);

		// 출력할 page set
		Page resultPage = new Page(currentPage, ((Integer) map.get("count")).intValue(), pageUnit, pageSize);

		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		request.setAttribute("menu", menu);

		return "forward:/product/listProduct.jsp";
	}

}
