package com.model2.mvc.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2.mvc.service.domain.User;

public class LogonCheckHandlerAdaptor extends HandlerInterceptorAdapter {

	public LogonCheckHandlerAdaptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Login Check preHandler");

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

//		로그인한 유저
		if (user != null) {
			String uri = request.getRequestURI();
//			로그인한 유저가 로그인을 하려고 하면
			if (uri.indexOf("addUserView") != -1 || uri.indexOf("loginView") != -1
					|| uri.indexOf("checkDuplication") != -1 || uri.indexOf("addUser") != -1
					|| uri.indexOf("login") != -1) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return false;
			}
			return true;

		} else {
//			로그인 안 한유저가 로그인 하려고 
			String uri = request.getRequestURI();

			if (uri.indexOf("addUserView") != -1 || uri.indexOf("loginView") != -1
					|| uri.indexOf("checkDuplication") != -1 || uri.indexOf("addUser") != -1
					|| uri.indexOf("login") != -1) {
				return true;
			}
			
//			로그인도 안 하고 다른 uri  접속하려고 하면
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;

		}

	}
}
