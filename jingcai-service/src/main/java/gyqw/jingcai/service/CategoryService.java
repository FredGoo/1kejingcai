package gyqw.jingcai.service;

import gyqw.jingcai.domain.Category;

import java.util.List;

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
    List<Category> listAll();

    /**
     * 根据id删除类别
     *
     * @param id
     * @return
     */
    int deleteById(int id);
}
