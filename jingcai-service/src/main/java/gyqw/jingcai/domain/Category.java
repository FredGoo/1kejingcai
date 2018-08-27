package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    /**
     * 类别名称
     */
    @Column(name = "C_TITLE")
    private String cTitle;

    /**
     * 类别排序
     */
    @Column(name = "N_ORDER")
    private Integer nOrder;

    @Column(name = "D_CREATE")
    private Date dCreate;

    @Column(name = "D_UPDATE")
    private Date dUpdate;

    private static final long serialVersionUID = 1L;

    /**
     * @return N_ID
     */
    public Integer getnId() {
        return nId;
    }

    /**
     * @param nId
     */
    public void setnId(Integer nId) {
        this.nId = nId;
    }

    /**
     * 获取类别名称
     *
     * @return C_TITLE - 类别名称
     */
    public String getcTitle() {
        return cTitle;
    }

    /**
     * 设置类别名称
     *
     * @param cTitle 类别名称
     */
    public void setcTitle(String cTitle) {
        this.cTitle = cTitle == null ? null : cTitle.trim();
    }

    /**
     * 获取类别排序
     *
     * @return N_ORDER - 类别排序
     */
    public Integer getnOrder() {
        return nOrder;
    }

    /**
     * 设置类别排序
     *
     * @param nOrder 类别排序
     */
    public void setnOrder(Integer nOrder) {
        this.nOrder = nOrder;
    }

    /**
     * @return D_CREATE
     */
    public Date getdCreate() {
        return dCreate;
    }

    /**
     * @param dCreate
     */
    public void setdCreate(Date dCreate) {
        this.dCreate = dCreate;
    }

    /**
     * @return D_UPDATE
     */
    public Date getdUpdate() {
        return dUpdate;
    }

    /**
     * @param dUpdate
     */
    public void setdUpdate(Date dUpdate) {
        this.dUpdate = dUpdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nId=").append(nId);
        sb.append(", cTitle=").append(cTitle);
        sb.append(", nOrder=").append(nOrder);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", dUpdate=").append(dUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}