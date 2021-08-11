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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cl.walmart.model.ProductDO;
import cl.walmart.repository.impl.ProductRepositoryImpl;

public class EmptiesRepositoryTest {
	
	MongoOperations mongoOperationsMock = Mockito.mock(MongoOperations.class);
	
	@Autowired
	ProductRepository productRepository = new ProductRepositoryImpl(mongoOperationsMock);
	
	@BeforeEach
	void setUp() {
		Mockito.when(mongoOperationsMock.find(new Query(Criteria.where("brand").regex("")
				.orOperator(Criteria.where("description").regex(""))), ProductDO.class))
				.thenReturn(new ArrayList<ProductDO>());
		Mockito.when(mongoOperationsMock.find(new Query(Criteria.where("id").is("")), ProductDO.class)).thenReturn(new ArrayList<ProductDO>());
	}
	
	@DisplayName("Should send empty array of products")
	@Test
	void productShouldSendEmptyWhenNoData() {
		Optional<List<ProductDO>> response = productRepository.find("");
		System.out.println("Object returned:" + response.get().size());
		Assertions.assertEquals(true, response.get().isEmpty());
	}
}
