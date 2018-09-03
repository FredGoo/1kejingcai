package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.CategoriesMapper;
import gyqw.jingcai.domain.Category;
import gyqw.jingcai.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoriesMapper categoriesMapper;

    @Resource
    public void setCategoriesMapper(CategoriesMapper categoriesMapper) {
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public int updateCategory(Category category) {
        // todo
        return 0;
    }

    @Override
    public List<Category> listAll() {
        try {
            return this.categoriesMapper.selectAll();
        } catch (Exception e) {
            logger.error("listAll error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(int id) {
        // todo
        return 0;
    }
}
