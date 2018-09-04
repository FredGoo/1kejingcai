package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "C_ORDER_NO")
    private String cOrderNo;

    @Column(name = "N_PRODUCT_ID")
    private Integer nProductId;

    @Column(name = "C_PRODUCT_TITLE")
    private String cProductTitle;

    @Column(name = "N_PRODUCT_AMOUNT")
    private Integer nProductAmount;

    @Column(name = "N_PRODUCT_PCS")
    private Integer nProductPcs;

    @Column(name = "D_CREATE")
    private Date dCreate;

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
     * @return C_ORDER_NO
     */
    public String getcOrderNo() {
        return cOrderNo;
    }

    /**
     * @param cOrderNo
     */
    public void setcOrderNo(String cOrderNo) {
        this.cOrderNo = cOrderNo == null ? null : cOrderNo.trim();
    }

    /**
     * @return N_PRODUCT_ID
     */
    public Integer getnProductId() {
        return nProductId;
    }

    /**
     * @param nProductId
     */
    public void setnProductId(Integer nProductId) {
        this.nProductId = nProductId;
    }

    /**
     * @return C_PRODUCT_TITLE
     */
    public String getcProductTitle() {
        return cProductTitle;
    }

    /**
     * @param cProductTitle
     */
    public void setcProductTitle(String cProductTitle) {
        this.cProductTitle = cProductTitle == null ? null : cProductTitle.trim();
    }

    /**
     * @return N_PRODUCT_AMOUNT
     */
    public Integer getnProductAmount() {
        return nProductAmount;
    }

    /**
     * @param nProductAmount
     */
    public void setnProductAmount(Integer nProductAmount) {
        this.nProductAmount = nProductAmount;
    }

    /**
     * @return N_PRODUCT_PCS
     */
    public Integer getnProductPcs() {
        return nProductPcs;
    }

    /**
     * @param nProductPcs
     */
    public void setnProductPcs(Integer nProductPcs) {
        this.nProductPcs = nProductPcs;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nId=").append(nId);
        sb.append(", cOrderNo=").append(cOrderNo);
        sb.append(", nProductId=").append(nProductId);
        sb.append(", cProductTitle=").append(cProductTitle);
        sb.append(", nProductAmount=").append(nProductAmount);
        sb.append(", nProductPcs=").append(nProductPcs);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}