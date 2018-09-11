package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.CategoriesMapper;
import gyqw.jingcai.dao.ProductCategoryMapper;
import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.ProductCategory;
import gyqw.jingcai.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoriesMapper categoriesMapper;
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    public void setCategoriesMapper(CategoriesMapper categoriesMapper) {
        this.categoriesMapper = categoriesMapper;
    }

    @Resource
    public void setProductCategoryMapper(ProductCategoryMapper productCategoryMapper) {
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public int updateCategory(Category category) {
        try {
            Date now = new Date();

            if (category.getnId() != null) {
                category.setdUpdate(now);
                return this.categoriesMapper.updateByPrimaryKeySelective(category);
            } else {
                return this.categoriesMapper.insertSelective(category);
            }
        } catch (Exception e) {
            logger.info("updateCategory error", e);
            return 0;
        }
    }

    @Override
    public List<Category> getAll() {
        try {
            return this.categoriesMapper.selectAll();
        } catch (Exception e) {
            logger.error("getAll error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            return this.categoriesMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("deleteById error", e);
            return 0;
        }
    }

    @Override
    public int relateProductIdToCategoryId(int categoryId, List<Integer> productIdList) {
        try {
            Date now = new Date();

            // 清空关联
            Condition condition = new Condition(ProductCategory.class);
            condition.createCriteria().andEqualTo("nCategoryId", categoryId);
            this.productCategoryMapper.deleteByCondition(condition);

            // 添加关联
            List<ProductCategory> productCategoryList = new ArrayList<>();
            for (Integer productId : productIdList) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setnCategoryId(categoryId);
                productCategory.setnProductId(productId);
                productCategory.setdCreate(now);
                productCategoryList.add(productCategory);
            }

            // 入库
            if (productCategoryList.size() > 0) {
                return this.productCategoryMapper.insertList(productCategoryList);
            }

            return 0;
        } catch (Exception e) {
            logger.error("relateProductIdToCategoryId error", e);
            return 0;
        }
    }
}
