package cl.walmart.service;

import java.util.List;

import cl.walmart.model.ProductDO;

public interface ProductService {
	List<ProductDO> find(String filter);
}
