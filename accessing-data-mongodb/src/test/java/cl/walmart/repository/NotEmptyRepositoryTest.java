package cl.walmart.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import cl.walmart.model.ProductDO;
import cl.walmart.repository.impl.ProductRepositoryImpl;

public class NotEmptyRepositoryTest {
	
	MongoOperations mongoOperationsMock = Mockito.mock(MongoOperations.class);
	
	@Autowired
	ProductRepository productRepository = new ProductRepositoryImpl(mongoOperationsMock);
	
	@BeforeEach
	void setUp() {
		List<ProductDO> productMock = new ArrayList<ProductDO>();
		productMock.add(new ProductDO(181,"abab","ababab","TestUnit.img",100));
		productMock.add(new ProductDO(18,"ababa","ababab","TestUnit.img",1000));
		productMock.add(new ProductDO(180,"ababab","abababa","TestUnit.img",10000));
		productMock.add(new ProductDO(1811,"abababa","ababab","TestUnit.img",100000));
		productMock.add(new ProductDO(1813,"abababab","ababab","TestUnit.img",1000000));
		Mockito.when(mongoOperationsMock.find(new Query(), ProductDO.class)).thenReturn(productMock);
	}
	
	@DisplayName("Should send array of products")
	@Test
	void productShouldSendEmptyWhenNoData() {
		Optional<List<ProductDO>> response = productRepository.find("aba");
		System.out.println("Object returned:" + response.get().size());
		Assertions.assertEquals(false, response.get().isEmpty());
	}
}
