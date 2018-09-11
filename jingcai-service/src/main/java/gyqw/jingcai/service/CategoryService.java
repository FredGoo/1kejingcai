package gyqw.jingcai.service;

import gyqw.jingcai.domain.Category;

import java.util.List;

/**
 * 类别接口
 */
public interface CategoryService {

    /**
     * 更新类别
     *
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 获取所有类别
     *
     * @return
     */
    List<Category> getAll();

    /**
     * 根据id删除类别
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 关联产品id到类别id
     *
     * @param categoryId
     * @param productIdList
     * @return
     */
    int relateProductIdToCategoryId(int categoryId, List<Integer> productIdList);
}
