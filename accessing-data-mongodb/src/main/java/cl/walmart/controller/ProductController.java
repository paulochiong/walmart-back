package cl.walmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.walmart.model.ProductDO;
import cl.walmart.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	private static final Log log = LogFactory.getLog(ProductController.class);

	private final ProductService productsService;

	@Autowired
	public ProductController(ProductService productsService) {
		this.productsService = productsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductDO>> get(String filter) {
		log.info("Get products by search");
		if(filter == null)
			return ResponseEntity.ok(new ArrayList<ProductDO>());
		return ResponseEntity.ok(productsService.find(filter));
	}
}
