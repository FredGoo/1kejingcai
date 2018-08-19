package gyqw.jingcai.controller;

import gyqw.jingcai.domain.Product;
import gyqw.jingcai.model.BaseResultModel;
import gyqw.jingcai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getProductByCategoryId", method = RequestMethod.GET)
    public BaseResultModel findProductByCategoryId(@RequestParam("categoryId") int categoryId) {
        BaseResultModel baseResultModel = new BaseResultModel();
        List<Product> productList = this.productService.getProductListByCategoryId(categoryId);
        baseResultModel.setResult(productList);
        return baseResultModel;
    }
}
