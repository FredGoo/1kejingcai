package gyqw.jingcai.controller;

import gyqw.jingcai.dao.BaseMapper;
import gyqw.jingcai.domain.Category;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.model.CategoryProductListModel;
import gyqw.jingcai.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController
public class CategoryController {
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public BaseModel listAll() {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.categoryService.getAll());
        return baseModel;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseModel create(@RequestBody Category category) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.categoryService.updateCategory(category));
        return baseModel;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseModel delete(@RequestBody Category category) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.categoryService.deleteById(category.getnId()));
        return baseModel;
    }

    @RequestMapping(value = "/relateCategoryList", method = RequestMethod.POST)
    public BaseModel relateCategoryList(@RequestBody CategoryProductListModel categoryProductListModel) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.categoryService.relateProductIdToCategoryId(categoryProductListModel.getCategoryId(),
                categoryProductListModel.getProductIdList()));
        return baseModel;
    }
}
