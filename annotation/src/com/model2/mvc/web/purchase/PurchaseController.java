package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
public class PurchaseController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Value("#{commonProperties['pageUnit']}")
	private int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	private int pageSize;

	public PurchaseController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@ModelAttribute Purchase purchase, @RequestParam("buyerId") String buyerId,
			@RequestParam("prodNo") int prodNo) throws Exception {

		Product product = productService.getProduct(prodNo);
		User user = userService.getUser(buyerId);

		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);

		purchaseService.addPurchase(purchase);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/purchase/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);

		return modelAndView;
	}

	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addpurchaseView(@RequestParam("prodNo") int prodNo) throws Exception {

		Product product = productService.getProduct(prodNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);

		return modelAndView;
	}

	@RequestMapping("/getPurchase.do")
	public ModelAndView getPurchase(@RequestParam("tranNo") int tranNo) throws Exception {

		Purchase purchase = purchaseService.getPurchase(tranNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		modelAndView.addObject("purchase", purchase);

		return modelAndView;
	}

	@RequestMapping("/listPurchase.do")
	public ModelAndView listPurchase(@ModelAttribute Search search, HttpSession session) throws Exception {

		String buyerId = ((User) session.getAttribute("user")).getUserId();

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);

		Page page = new Page(search.getCurrentPage(), ((Integer) map.get("count")).intValue(), pageUnit, pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", page);

		return modelAndView;
	}

	@RequestMapping("/listSale.do")
	public ModelAndView listSale(@ModelAttribute("search") Search search, @RequestParam("menu") String menu)
			throws Exception {

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = purchaseService.getSaleList(search);

		Page page = new Page(search.getCurrentPage(), ((Integer) map.get("count")).intValue(), pageUnit, pageSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/listSale.jsp");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("menu", menu);
		modelAndView.addObject("resultPage", page);

		return modelAndView;
	}

	@RequestMapping("/updatePurchase.do")
	public ModelAndView updatePurchase(@RequestParam("tranNo") int tranNo, @RequestParam("buyerId") String userId)
			throws Exception {

		User user = userService.getUser(userId);

		Purchase purchase = purchaseService.getPurchase(tranNo);

		purchase.setBuyer(user);

		purchaseService.updatePurchase(purchase);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		modelAndView.addObject("purchase", purchase);

		return modelAndView;
	}

	@RequestMapping("/updatePurchaseView.do")
	public ModelAndView updatePurchaseView(@RequestParam("tranNo") int tranNo) throws Exception {

		Purchase purchase = purchaseService.getPurchase(tranNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");
		modelAndView.addObject("purchase", purchase);

		return modelAndView;
	}

	@RequestMapping("/updateTranCode.do")
	public ModelAndView updateTranCode(@RequestParam("tranCode") String tranCode, @RequestParam(value="prodNo",defaultValue="1") int prodNo,
			@RequestParam(value="tranNo",defaultValue="1") int tranNo) throws Exception {

		Purchase purchase = null;

		ModelAndView modelAndView = new ModelAndView();
		if (tranCode.equals("2")) {
			purchase = purchaseService.getPurchase2(prodNo);
			purchase.setTranCode(tranCode);
			purchaseService.updateTranCode(purchase);
			modelAndView.setViewName("/listSale.do?menu=manage");
		} else if (tranCode.equals("3")) {
			purchase = purchaseService.getPurchase(tranNo);
			purchase.setTranCode(tranCode);
			purchaseService.updateTranCode(purchase);
			modelAndView.setViewName("/listPurchase.do");
		}
		
		return modelAndView;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
