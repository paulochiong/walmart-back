package cl.walmart.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cl.walmart.model.ProductDO;
import cl.walmart.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final MongoOperations mongoOperations;

	@Autowired
	public ProductRepositoryImpl(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	@Override
	public Optional<List<ProductDO>> find(String filter) {
		Query searchQuery=null;
		String filterReverse = "";
		for(int i = 0; i < filter.length()/2; i++) {
			filterReverse = filter.charAt(i) + filterReverse;
		}
		if (filter.matches("[0-9]+"))
			searchQuery = new Query(Criteria.where("id").in(Integer.valueOf(filter)));
		else 
			searchQuery = new Query(new Criteria().orOperator(Criteria.where("brand").regex(filter),
					Criteria.where("description").regex(filter)));
		List<ProductDO> productos = this.mongoOperations.find(searchQuery, ProductDO.class);
		if(filter.endsWith(filterReverse)) {
			productos.forEach((p)->p.setPrice(p.getPrice()/2));
		}
		Optional<List<ProductDO>> optionalProducts = Optional.ofNullable(productos);
		return optionalProducts;
	}

}
