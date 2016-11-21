package com.model2.mvc.view.purchase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		PurchaseService service = new PurchaseServiceImpl();
		Search search = new Search();
		User user = (User)session.getAttribute("user");
		
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(this.getServletContext().getInitParameter("pageUnit"));
		
		search.setCurrentPage(currentPage);
		search.setPageSize(pageSize);
		
		Map<String, Object> map = service.getPurchaseList(search, user.getUserId());
		
		Page page = new Page(currentPage, ((Integer)map.get("count")).intValue(), pageUnit, pageSize);
		
		
		request.setAttribute("page", page);
		request.setAttribute("list", (List<Purchase>)map.get("list"));
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
