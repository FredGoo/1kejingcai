package gyqw.jingcai.service.impl;

import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.Product;
import gyqw.jingcai.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product mergeProduct(Product product) {
        // todo
        return null;
    }

    @Override
    public boolean deleteProductById(int id) {
        // todo
        return false;
    }

    @Override
    public List<Product> getProductListByCategoryId(int categoryId) {
        // todo
        return null;
    }
}
