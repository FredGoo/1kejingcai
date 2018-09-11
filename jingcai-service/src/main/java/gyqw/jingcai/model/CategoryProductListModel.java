package gyqw.jingcai.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author fred
 * 2018-09-11 6:57 PM
 */
public class CategoryProductListModel implements Serializable {
    private int categoryId;
    private List<Integer> productIdList;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Integer> productIdList) {
        this.productIdList = productIdList;
    }

    @Override
    public String toString() {
        return "CategoryProductListModel{" +
                "categoryId=" + categoryId +
                ", productIdList=" + productIdList +
                '}';
    }
}
