package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.view.PurchaseDI;

public class ListSaleAction extends Action {

	public ListSaleAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		PurchaseService service = PurchaseDI.getService();
		Search search = new Search();
		
		String menu = request.getParameter("menu");
		
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if (request.getParameter("sort") != null && request.getParameter("sort") != "0") {
			search.setSort(Integer.parseInt(request.getParameter("sort")));
			System.out.println(search.getSort());
		}

		
		if(request.getParameter("searchKeyword")!="") {
			String searchKeyword = request.getParameter("searchKeyword");
			String searchCondition = request.getParameter("searchCondition");
			search.setSearchCondition(searchCondition);
			search.setSearchKeyword(searchKeyword);
		}
		
		
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(this.getServletContext().getInitParameter("pageUnit"));
		search.setCurrentPage(currentPage);
		search.setPageSize(pageSize);
		
		Map<String, Object> map = service.getSaleList(search);
		
		Page page = new Page(currentPage, ((Integer)map.get("count")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("resultPage", page);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		request.setAttribute("menu", menu);
		
		return "forward:/purchase/listSale.jsp";
	}

}
