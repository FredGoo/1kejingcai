package gyqw.jingcai.controller;

import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        baseModel.setResult(this.categoryService.listAll());
        return baseModel;
    }
}
