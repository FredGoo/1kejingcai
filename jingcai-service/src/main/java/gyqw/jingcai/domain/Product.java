package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    /**
     * 产品名称
     */
    @Column(name = "C_TITLE")
    private String cTitle;

    /**
     * 产品价格（以分为单位）
     */
    @Column(name = "N_AMOUNT")
    private Integer nAmount;

    /**
     * 产品排序
     */
    @Column(name = "N_ORDER")
    private Integer nOrder;

    @Column(name = "D_CREATE")
    private Date dCreate;

    @Column(name = "D_UPDATE")
    private Date dUpdate;

    /**
     * 图片路径
     */
    @Column(name = "T_IMAGE_URL")
    private String tImageUrl;

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
     * 获取产品名称
     *
     * @return C_TITLE - 产品名称
     */
    public String getcTitle() {
        return cTitle;
    }

    /**
     * 设置产品名称
     *
     * @param cTitle 产品名称
     */
    public void setcTitle(String cTitle) {
        this.cTitle = cTitle == null ? null : cTitle.trim();
    }

    /**
     * 获取产品价格（以分为单位）
     *
     * @return N_AMOUNT - 产品价格（以分为单位）
     */
    public Integer getnAmount() {
        return nAmount;
    }

    /**
     * 设置产品价格（以分为单位）
     *
     * @param nAmount 产品价格（以分为单位）
     */
    public void setnAmount(Integer nAmount) {
        this.nAmount = nAmount;
    }

    /**
     * 获取产品排序
     *
     * @return N_ORDER - 产品排序
     */
    public Integer getnOrder() {
        return nOrder;
    }

    /**
     * 设置产品排序
     *
     * @param nOrder 产品排序
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

    /**
     * 获取图片路径
     *
     * @return T_IMAGE_URL - 图片路径
     */
    public String gettImageUrl() {
        return tImageUrl;
    }

    /**
     * 设置图片路径
     *
     * @param tImageUrl 图片路径
     */
    public void settImageUrl(String tImageUrl) {
        this.tImageUrl = tImageUrl == null ? null : tImageUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nId=").append(nId);
        sb.append(", cTitle=").append(cTitle);
        sb.append(", nAmount=").append(nAmount);
        sb.append(", nOrder=").append(nOrder);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", dUpdate=").append(dUpdate);
        sb.append(", tImageUrl=").append(tImageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}