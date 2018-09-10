package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.ProductCategoryMapper;
import gyqw.jingcai.dao.ProductsMapper;
import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.Product;
import gyqw.jingcai.domain.ProductCategory;
import gyqw.jingcai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductsMapper productsMapper;
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    public void setProductsMapper(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    @Resource
    public void setProductCategoryMapper(ProductCategoryMapper productCategoryMapper) {
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public int updateProduct(Product product) {
        try {
            Date now = new Date();

            if (product.getnId() != null) {
                product.setdUpdate(now);
                return this.productsMapper.updateByPrimaryKeySelective(product);
            } else {
                product.setdCreate(now);
                return this.productsMapper.insertSelective(product);
            }
        } catch (Exception e) {
            logger.error("updateProduct error", e);
            return 0;
        }
    }

    @Override
    public int deleteProductById(int id) {
        try {
            return this.productsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("deleteProductById error", e);
            return 0;
        }
    }

    @Override
    public List<Product> getProductListByCategoryId(int categoryId) {
        List<Product> productList = new ArrayList<>();

        try {
            // 获取类别信息
            Condition categoryCondition = new Condition(ProductCategory.class);
            categoryCondition.createCriteria().andEqualTo("nCategoryId", categoryId);
            List<ProductCategory> productCategoryList = this.productCategoryMapper.selectByCondition(categoryCondition);

            // 获取产品id的list
            List<Integer> productIdList = new ArrayList<>();
            for (ProductCategory productCategory : productCategoryList) {
                productIdList.add(productCategory.getnProductId());
            }

            // 获取产品信息
            if (productIdList.size() > 0) {
                Condition productCondition = new Condition(Product.class);
                productCondition.createCriteria().andIn("nId", productIdList);
                productList = this.productsMapper.selectByCondition(productCondition);
            }

            return productList;
        } catch (Exception e) {
            logger.error("getProductListByCategoryId error", e);
            return productList;
        }
    }

    @Override
    public List<Product> getAll() {
        return this.productsMapper.selectAll();
    }
}
