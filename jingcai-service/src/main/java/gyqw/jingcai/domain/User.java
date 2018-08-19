package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class User implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "C_OPEN_ID")
    private String cOpenId;

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
     * @return C_OPEN_ID
     */
    public String getcOpenId() {
        return cOpenId;
    }

    /**
     * @param cOpenId
     */
    public void setcOpenId(String cOpenId) {
        this.cOpenId = cOpenId == null ? null : cOpenId.trim();
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
        sb.append(", cOpenId=").append(cOpenId);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}