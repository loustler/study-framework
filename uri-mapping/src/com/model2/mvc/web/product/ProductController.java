package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping
	public String addProduct(@ModelAttribute("product") Product product, Map<String, Object> map,
			@RequestParam("uploadFile") MultipartFile file) throws Exception {

		String temp = product.getManuDay();

		String[] splitTemp = temp.split("-");

		product.setManuDay(splitTemp[0] + splitTemp[1] + splitTemp[2]);

		String fileName = file.getOriginalFilename();

		System.out.println("Origin FileName : " + fileName);
		String path = "/Users/DY/Documents/workspace/6.Model2MVCShop(Presentation+BusinessLogic)/WebContent/images/uploadFiles/";

		File uploadFile = new File(path, fileName);

		file.transferTo(uploadFile);

		product.setFileName(fileName);
		System.out.println("저장된 파일이름 : " + fileName);
		productService.addProduct(product);

		map.put("product", product);

		System.out.println("addproduct : " + product);

		return "/product/productView.jsp";
	}

	@RequestMapping
	public String getProduct(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu,
			Map<String, Object> map) throws Exception {

		Product product = productService.getProduct(prodNo);

		map.put("product", product);
		map.put("menu", menu);

		return "/product/getProduct.jsp";
	}

	@RequestMapping
	public String listProduct(@ModelAttribute Search search, @RequestParam("menu") String menu, Map<String, Object> map)
			throws Exception {

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> mapTemp = productService.listProduct(search);

		Page page = new Page(search.getCurrentPage(), ((Integer) mapTemp.get("count")).intValue(), pageUnit, pageSize);

		map.put("list", mapTemp.get("list"));
		// map.put("search", search);
		map.put("resultPage", page);
		map.put("menu", menu);

		return "/product/listProduct.jsp";
	}

	@RequestMapping
	public String updateProduct(@ModelAttribute Product product, Model model) throws Exception {

		productService.updateProduct(product);

		model.addAttribute("product", product);

		return "/product/getProduct.jsp";
	}

	@RequestMapping
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		Product product = productService.getProduct(prodNo);

		model.addAttribute("product", product);

		return "/product/updateProductView.jsp";
	}

	/**
	 * @param productService
	 *            the productService to set
	 */
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
