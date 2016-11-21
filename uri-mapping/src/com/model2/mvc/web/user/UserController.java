package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping
	public ModelAndView addUser(@ModelAttribute("user") User user) throws Exception {
		userService.addUser(user);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/loginView.jsp");

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView checkDuplication(@RequestParam("userId") String userId) throws Exception {

		boolean result = userService.checkDuplication(userId);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("result", new Boolean(result));
		modelAndView.setViewName("/user/checkDuplicatioin.jsp");

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView getUser(@RequestParam("userId") String userId) throws Exception {

		User user = userService.getUser(userId);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("user", user);
		modelAndView.setViewName("/user/getUser.jsp");

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView listUser(@ModelAttribute("search") Search search) throws Exception {

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("count")).intValue(), pageUnit,
				pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("list", map.get("list"));

		modelAndView.setViewName("/user/listUser.jsp");

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView login(@ModelAttribute User user, HttpSession session) throws Exception {

		User dbUser = userService.getUser(user.getUserId());

		ModelAndView modelAndView = new ModelAndView();
		if (user.getPassword().equals(dbUser.getPassword())) {
			modelAndView.setViewName("redirect:/index.jsp");
			session.setAttribute("user", dbUser);
		} else {
			modelAndView.setViewName("user/loginView.jsp");
		}

		return modelAndView;
	}

	@RequestMapping
	public String logout(HttpSession session) throws Exception {

		session.removeAttribute("user");

		return "redirect:/index.jsp";
	}

	@RequestMapping
	public String removeUser(@RequestParam("userId") String userId, HttpSession session) throws Exception {

		userService.removeUser(userId);

		session.removeAttribute("user");

		return "redirect:/index.jsp";
	}

	@RequestMapping
	public String updateUser(@ModelAttribute User user, HttpSession session) throws Exception {

		userService.updateUser(user);

		User sessionUser = (User) session.getAttribute("user");

		if (user.equals(sessionUser)) {
			session.setAttribute("user", user);
		}

		return "redirect:/user/getUser?userId=" + user.getUserId();
	}

	@RequestMapping
	public String updateUserView(@RequestParam("userId") String userId, Model model) throws Exception {

		User user = userService.getUser(userId);

		model.addAttribute("user", user);

		return "/user/updateUser.jsp";
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
