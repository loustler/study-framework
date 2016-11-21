package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.view.UserDI;

public class RemoveUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String userId = request.getParameter("userId");

		UserService service = UserDI.getService();

		service.removeUser(userId);
		
		HttpSession httpSession = request.getSession(true);
		httpSession.removeAttribute("user");
		
		return "redirect:/";
	}

}
