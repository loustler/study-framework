package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PurchaseService service = new PurchaseServiceImpl();
		HttpSession session = request.getSession(true);
		UserVO userVO = (UserVO) session.getAttribute("user");
		SearchVO searchVO = new SearchVO();
		PurchaseVO purchaseVO = null;

		String userId = userVO.getUserId();
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		String pageSize = getServletContext().getInitParameter("pageSize");
		System.out.println("page Size : " + pageSize);

		searchVO.setPageUnit(Integer.parseInt(pageSize));
		Map<String, Object> map = service.getPurchaseList(searchVO, userId);
		
		System.out.println("ListPurchaseAction hashmap size : " + map.size());
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);

		return "forward:/purchase/listPurchase.jsp";
	}

}
