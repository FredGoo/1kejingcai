package gyqw.jingcai.filter;

import java.io.Serializable;

/**
 * @author fred
 * 2018/9/10 下午9:42
 */
public class BaseFilter implements Serializable {
    private int page;
    private int row;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "BaseFilter{" +
                "page=" + page +
                ", row=" + row +
                '}';
    }
}
