package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "C_OPEN_ID")
    private String cOpenId;

    @Column(name = "D_UPDATE")
    private Date dUpdate;

    @Column(name = "C_ADDRESS")
    private String cAddress;

    @Column(name = "D_CREATE")
    private Date dCreate;

    @Column(name = "C_MOBILE")
    private String cMobile;

    @Column(name = "C_NAME")
    private String cName;

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
     * @return C_ADDRESS
     */
    public String getcAddress() {
        return cAddress;
    }

    /**
     * @param cAddress
     */
    public void setcAddress(String cAddress) {
        this.cAddress = cAddress == null ? null : cAddress.trim();
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
     * @return C_MOBILE
     */
    public String getcMobile() {
        return cMobile;
    }

    /**
     * @param cMobile
     */
    public void setcMobile(String cMobile) {
        this.cMobile = cMobile == null ? null : cMobile.trim();
    }

    /**
     * @return C_NAME
     */
    public String getcName() {
        return cName;
    }

    /**
     * @param cName
     */
    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nId=").append(nId);
        sb.append(", cOpenId=").append(cOpenId);
        sb.append(", dUpdate=").append(dUpdate);
        sb.append(", cAddress=").append(cAddress);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", cMobile=").append(cMobile);
        sb.append(", cName=").append(cName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}