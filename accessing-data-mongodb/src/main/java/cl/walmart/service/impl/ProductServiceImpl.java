package cl.walmart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.walmart.model.ProductDO;
import cl.walmart.repository.ProductRepository;
import cl.walmart.service.ProductService;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

	@Override
	public List<ProductDO> find(String filter) {
		Optional<List<ProductDO>> product = productRepository.find(filter);
		return product.get();
	}


}
