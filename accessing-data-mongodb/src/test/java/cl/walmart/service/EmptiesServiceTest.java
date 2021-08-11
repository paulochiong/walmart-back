package cl.walmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import cl.walmart.model.ProductDO;
import cl.walmart.repository.ProductRepository;
import cl.walmart.service.impl.ProductServiceImpl;

public class EmptiesServiceTest {

	ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);
	
	@Autowired
	ProductService productService = new ProductServiceImpl(productRepositoryMock);
	
	@BeforeEach
	void setUp() {
		Optional<List<ProductDO>> opProd = Optional.ofNullable(new ArrayList<ProductDO>());
		Optional<List<String>> opStr = Optional.ofNullable(new ArrayList<String>());
		Mockito.when(productRepositoryMock.find("")).thenReturn(opProd);
	}
	
	@DisplayName("Should send empty array of products")
	@Test
	void productShouldSendEmptyWhenNoData() {
		List<ProductDO> response = productService.find("");
		System.out.println("Object returned:" + response.size());
		Assertions.assertEquals(true, response.isEmpty());
	}
}
