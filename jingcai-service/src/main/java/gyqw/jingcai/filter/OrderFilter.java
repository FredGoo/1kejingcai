package gyqw.jingcai.filter;

import java.io.Serializable;

/**
 * @author fred
 * 2018/9/10 下午9:42
 */
public class OrderFilter extends BaseFilter implements Serializable {
    private String name;
    private String mobile;
    private String type;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderFilter{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
