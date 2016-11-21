package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();

			System.out.println("Origin FileName : " + fileName);
			String path = "/Users/DY/Documents/workspace/9.Model2MVCShop(Use jQuery in View)/WebContent/images/uploadFiles/";

			File uploadFile = new File(path, fileName);

			file.transferTo(uploadFile);

			product.setFileName(fileName);
			System.out.println("저장된 파일이름 : " + fileName);
		}

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
	public String updateProductView(@RequestBody Product product, Model model) throws Exception {

		Product productResult = productService.getProduct(product.getProdNo());

		model.addAttribute("product", productResult);

		return "/product/updateProductView.jsp";
	}

	@RequestMapping
	public void addProductJson(@RequestBody Product product, Model model,
			@RequestParam("uploadFile") MultipartFile file) throws Exception {
		String temp = product.getManuDay();

		String[] splitTemp = temp.split("-");

		product.setManuDay(splitTemp[0] + splitTemp[1] + splitTemp[2]);

		if (file.isEmpty()) {
			String fileName = file.getOriginalFilename();

			System.out.println("Origin FileName : " + fileName);
			String path = "/Users/DY/Documents/workspace/9.Model2MVCShop(Use jQuery in View)/WebContent/images/uploadFiles/";

			File uploadFile = new File(path, fileName);

			file.transferTo(uploadFile);

			product.setFileName(fileName);
			System.out.println("저장된 파일이름 : " + fileName);
		}
		productService.addProduct(product);

		model.addAttribute("product", product);
	}

	@RequestMapping
	public void getProductJson(@RequestBody String str, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("클라이언트 데이터 : " +str);
		JSONObject jsonData = (JSONObject) JSONValue.parse(str);
		
		ObjectMapper objectMapper = new ObjectMapper();
		int prodNo = ( objectMapper.readValue(jsonData.get("prodNo").toString(), Integer.class)).intValue();
		String menu = objectMapper.readValue(jsonData.get("menu").toString(), String.class);

		Product productResult = productService.getProduct(prodNo);

		model.addAttribute("product", productResult);
		model.addAttribute("menu", menu);

	}

	@RequestMapping
	public void listProductJson(@RequestBody String str, Model model) throws Exception {

		System.out.println("클라이언트 데이터 : "+str);
		
		JSONObject jsonObject = (JSONObject)JSONValue.parse(str);
		ObjectMapper mapper = new ObjectMapper();
		Search search = mapper.readValue(jsonObject.get("search").toString(), Search.class);
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(pageSize);

		Map<String, Object> map = productService.listProduct(search);

		Page page = new Page(search.getCurrentPage(), ((Integer) map.get("count")).intValue(), pageUnit, pageSize);

		model.addAttribute("resultPage", page);
		model.addAttribute("list", map.get("list"));
	}

	@RequestMapping
	public void updateProductJson(@RequestBody Product product, Model model) throws Exception {
		productService.updateProduct(product);

		model.addAttribute("product", product);
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
