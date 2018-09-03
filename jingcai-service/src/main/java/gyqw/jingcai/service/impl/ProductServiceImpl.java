package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.ProductsMapper;
import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.Product;
import gyqw.jingcai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductsMapper productsMapper;

    @Resource
    public void setProductsMapper(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    @Override
    public int updateProduct(Product product) {
        try {
            Date now = new Date();
            if (product.getnId() != null) {
                product.setdUpdate(new Date());
                return this.productsMapper.updateByPrimaryKeySelective(product);
            } else {
                product.setdCreate(new Date());
                return this.productsMapper.insertSelective(product);
            }
        } catch (Exception e) {
            logger.error("updateProduct error", e);
            return 0;
        }
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
