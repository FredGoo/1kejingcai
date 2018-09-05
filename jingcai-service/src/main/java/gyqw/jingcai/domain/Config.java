package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "configs")
public class Config implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "N_IN_USE")
    private Integer nInUse;

    /**
     * 权重，越大越靠前
     */
    @Column(name = "N_ORDER")
    private Integer nOrder;

    @Column(name = "C_CATEGORY")
    private String cCategory;

    @Column(name = "C_KEY")
    private String cKey;

    @Column(name = "C_VAL")
    private String cVal;

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
     * @return N_IN_USE
     */
    public Integer getnInUse() {
        return nInUse;
    }

    /**
     * @param nInUse
     */
    public void setnInUse(Integer nInUse) {
        this.nInUse = nInUse;
    }

    /**
     * 获取权重，越大越靠前
     *
     * @return N_ORDER - 权重，越大越靠前
     */
    public Integer getnOrder() {
        return nOrder;
    }

    /**
     * 设置权重，越大越靠前
     *
     * @param nOrder 权重，越大越靠前
     */
    public void setnOrder(Integer nOrder) {
        this.nOrder = nOrder;
    }

    /**
     * @return C_CATEGORY
     */
    public String getcCategory() {
        return cCategory;
    }

    /**
     * @param cCategory
     */
    public void setcCategory(String cCategory) {
        this.cCategory = cCategory == null ? null : cCategory.trim();
    }

    /**
     * @return C_KEY
     */
    public String getcKey() {
        return cKey;
    }

    /**
     * @param cKey
     */
    public void setcKey(String cKey) {
        this.cKey = cKey == null ? null : cKey.trim();
    }

    /**
     * @return C_VAL
     */
    public String getcVal() {
        return cVal;
    }

    /**
     * @param cVal
     */
    public void setcVal(String cVal) {
        this.cVal = cVal == null ? null : cVal.trim();
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
        sb.append(", nInUse=").append(nInUse);
        sb.append(", nOrder=").append(nOrder);
        sb.append(", cCategory=").append(cCategory);
        sb.append(", cKey=").append(cKey);
        sb.append(", cVal=").append(cVal);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", dUpdate=").append(dUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}