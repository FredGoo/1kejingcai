package gyqw.jingcai.controller;

import gyqw.jingcai.domain.Product;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/list")
    public BaseModel list() {
        BaseModel baseModel = new BaseModel();
        List<Product> productList = this.productService.getAll();
        baseModel.setResult(productList);
        return baseModel;
    }

    @RequestMapping(value = "/getProductByCategoryId", method = RequestMethod.GET)
    public BaseModel findProductByCategoryId(@RequestParam("categoryId") int categoryId) {
        BaseModel baseModel = new BaseModel();
        List<Product> productList = this.productService.getProductListByCategoryId(categoryId);
        baseModel.setResult(productList);
        return baseModel;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseModel update(@RequestBody Product product) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.productService.updateProduct(product));
        return baseModel;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseModel delete(@RequestBody Product product) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.productService.deleteProductById(product.getnId()));
        return baseModel;
    }
}
