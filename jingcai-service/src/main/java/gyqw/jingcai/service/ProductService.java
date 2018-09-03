package gyqw.jingcai.service;

import gyqw.jingcai.domain.Category;
import gyqw.jingcai.domain.Product;

import java.util.List;

/**
 * 产品接口
 */
public interface ProductService {

    /**
     * 添加/更新产品
     *
     * @param product 产品信息
     * @return 更新后的产品信息
     */
    int updateProduct(Product product);

    /**
     * 根据产品id删除产品
     *
     * @param id 产品id
     * @return true/false
     */
    boolean deleteProductById(int id);

    /**
     * 根据类别id获取产品
     *
     * @param categoryId 类别id
     * @return 产品列表
     */
    List<Product> getProductListByCategoryId(int categoryId);

}
