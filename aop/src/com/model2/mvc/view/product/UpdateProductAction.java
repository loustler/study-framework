package com.model2.mvc.view.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.view.ProductDI;

public class UpdateProductAction extends Action {

	public UpdateProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if (FileUpload.isMultipartContent(request)) {
			String tempDir = "/Users/DY/Documents/workspace/5.Model2MVCShop(AOP To Service Layer)/WebContent/images/uploadFiles//";

			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(tempDir);

			// MaxFile Size 10MB
			fileUpload.setSizeMax(1024 * 1024 * 10);

			fileUpload.setSizeThreshold(1024 * 100);

			if (request.getContentLength() < fileUpload.getSizeMax()) {
				Product product = new Product();

				StringTokenizer stringTokenizer = null;

				List fileItemList = fileUpload.parseRequest(request);
				int size = fileItemList.size();
				for (int i = 0; i < size; i++) {
					FileItem fileItem = (FileItem) fileItemList.get(i);
					if (fileItem.isFormField()) {
						if (fileItem.getFieldName().equals("manuDate")) {
							stringTokenizer = new StringTokenizer(fileItem.getString("UTF-8"), "-");
							String manuDate = stringTokenizer.nextToken() + stringTokenizer.nextToken()
									+ stringTokenizer.nextToken();
							product.setManuDay(manuDate);
						} else if (fileItem.getFieldName().equals("prodName"))
							product.setProdName(fileItem.getString("UTF-8"));
						else if (fileItem.getFieldName().equals("prodDetail"))
							product.setProdDetail(fileItem.getString("UTF-8"));
						else if (fileItem.getFieldName().equals("price"))
							product.setPrice(Integer.parseInt(fileItem.getString("UTF-8")));
						else if (fileItem.getFieldName().equals("prodNo"))
							product.setProdNo(Integer.parseInt(fileItem.getString("UTF-8")));
							
					} else {
						if (fileItem.getSize() > 0) {
							int index = fileItem.getName().lastIndexOf("//");
							if (index == 1) {
								index = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(index + 1);
							product.setFileName(fileName);
							try {
								File uploadFile = new File(tempDir, fileName);
								fileItem.write(uploadFile);
							} catch (IOException e) {
								System.out.println(e);
							}
						} else {
							product.setFileName("../../images/empty.GIF");
						}
					}
				}
				ProductService service = ProductDI.getService();
				service.updateProduct(product);

				request.setAttribute("product", product);
			} else {
				int overSize = (request.getContentLength() / 1000000);
				System.out.println("<script>alert('파일의 크기는 1MB까지 입니다.용량이 초과되었습니다. 용량 : " + overSize + "MB ");
			}

		} else {
			System.out.println("인코딩 타입이 multipart/for-data가 아님");
		}
		
		return "forward:/product/getProduct.jsp";
	}

}
