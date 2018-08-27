package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "product_category")
public class ProductCategory implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "N_PRODUCT_ID")
    private Integer nProductId;

    @Column(name = "N_CATEGORY_ID")
    private Integer nCategoryId;

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
     * @return N_CATEGORY_ID
     */
    public Integer getnCategoryId() {
        return nCategoryId;
    }

    /**
     * @param nCategoryId
     */
    public void setnCategoryId(Integer nCategoryId) {
        this.nCategoryId = nCategoryId;
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
        sb.append(", nProductId=").append(nProductId);
        sb.append(", nCategoryId=").append(nCategoryId);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}