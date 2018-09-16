package gyqw.jingcai.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "N_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "C_ORDER_NO")
    private String cOrderNo;

    @Column(name = "N_TYPE")
    private Integer nType;

    @Column(name = "N_STATUS")
    private Integer nStatus;

    /**
     * 产品总价
     */
    @Column(name = "N_PRODUCTS_AMOUNT")
    private Integer nProductsAmount;

    /**
     * 服务费用
     */
    @Column(name = "N_SERVICE_AMOUNT")
    private Integer nServiceAmount;

    /**
     * 折扣费用
     */
    @Column(name = "N_DISCOUNT_AMOUNT")
    private Integer nDiscountAmount;

    /**
     * 总计金额(单位: 分)
     */
    @Column(name = "N_TOTAL_AMOUNT")
    private Integer nTotalAmount;

    /**
     * 微信支付单号
     */
    @Column(name = "C_WECHAT_PAY_ORDER_NO")
    private String cWechatPayOrderNo;

    @Column(name = "N_USER_ID")
    private Integer nUserId;

    @Column(name = "C_CUST_NAME")
    private String cCustName;

    /**
     * 手机
     */
    @Column(name = "C_MOBILE")
    private String cMobile;

    @Column(name = "C_DELIVER")
    private String cDeliver;

    @Column(name = "D_DELIVER")
    private Date dDeliver;

    /**
     * 创建时间
     */
    @Column(name = "D_CREATE")
    private Date dCreate;

    /**
     * 更新时间
     */
    @Column(name = "D_UPDATE")
    private Date dUpdate;

    @Column(name = "T_CUST_ADDRESS")
    private String tCustAddress;

    /**
     * 备注
     */
    @Column(name = "T_REMARK")
    private String tRemark;

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
     * @return N_TYPE
     */
    public Integer getnType() {
        return nType;
    }

    /**
     * @param nType
     */
    public void setnType(Integer nType) {
        this.nType = nType;
    }

    /**
     * @return N_STATUS
     */
    public Integer getnStatus() {
        return nStatus;
    }

    /**
     * @param nStatus
     */
    public void setnStatus(Integer nStatus) {
        this.nStatus = nStatus;
    }

    /**
     * 获取产品总价
     *
     * @return N_PRODUCTS_AMOUNT - 产品总价
     */
    public Integer getnProductsAmount() {
        return nProductsAmount;
    }

    /**
     * 设置产品总价
     *
     * @param nProductsAmount 产品总价
     */
    public void setnProductsAmount(Integer nProductsAmount) {
        this.nProductsAmount = nProductsAmount;
    }

    /**
     * 获取服务费用
     *
     * @return N_SERVICE_AMOUNT - 服务费用
     */
    public Integer getnServiceAmount() {
        return nServiceAmount;
    }

    /**
     * 设置服务费用
     *
     * @param nServiceAmount 服务费用
     */
    public void setnServiceAmount(Integer nServiceAmount) {
        this.nServiceAmount = nServiceAmount;
    }

    /**
     * 获取折扣费用
     *
     * @return N_DISCOUNT_AMOUNT - 折扣费用
     */
    public Integer getnDiscountAmount() {
        return nDiscountAmount;
    }

    /**
     * 设置折扣费用
     *
     * @param nDiscountAmount 折扣费用
     */
    public void setnDiscountAmount(Integer nDiscountAmount) {
        this.nDiscountAmount = nDiscountAmount;
    }

    /**
     * 获取总计金额(单位: 分)
     *
     * @return N_TOTAL_AMOUNT - 总计金额(单位: 分)
     */
    public Integer getnTotalAmount() {
        return nTotalAmount;
    }

    /**
     * 设置总计金额(单位: 分)
     *
     * @param nTotalAmount 总计金额(单位: 分)
     */
    public void setnTotalAmount(Integer nTotalAmount) {
        this.nTotalAmount = nTotalAmount;
    }

    /**
     * 获取微信支付单号
     *
     * @return C_WECHAT_PAY_ORDER_NO - 微信支付单号
     */
    public String getcWechatPayOrderNo() {
        return cWechatPayOrderNo;
    }

    /**
     * 设置微信支付单号
     *
     * @param cWechatPayOrderNo 微信支付单号
     */
    public void setcWechatPayOrderNo(String cWechatPayOrderNo) {
        this.cWechatPayOrderNo = cWechatPayOrderNo == null ? null : cWechatPayOrderNo.trim();
    }

    /**
     * @return N_USER_ID
     */
    public Integer getnUserId() {
        return nUserId;
    }

    /**
     * @param nUserId
     */
    public void setnUserId(Integer nUserId) {
        this.nUserId = nUserId;
    }

    /**
     * @return C_CUST_NAME
     */
    public String getcCustName() {
        return cCustName;
    }

    /**
     * @param cCustName
     */
    public void setcCustName(String cCustName) {
        this.cCustName = cCustName == null ? null : cCustName.trim();
    }

    /**
     * 获取手机
     *
     * @return C_MOBILE - 手机
     */
    public String getcMobile() {
        return cMobile;
    }

    /**
     * 设置手机
     *
     * @param cMobile 手机
     */
    public void setcMobile(String cMobile) {
        this.cMobile = cMobile == null ? null : cMobile.trim();
    }

    /**
     * @return C_DELIVER
     */
    public String getcDeliver() {
        return cDeliver;
    }

    /**
     * @param cDeliver
     */
    public void setcDeliver(String cDeliver) {
        this.cDeliver = cDeliver == null ? null : cDeliver.trim();
    }

    /**
     * @return D_DELIVER
     */
    public Date getdDeliver() {
        return dDeliver;
    }

    /**
     * @param dDeliver
     */
    public void setdDeliver(Date dDeliver) {
        this.dDeliver = dDeliver;
    }

    /**
     * 获取创建时间
     *
     * @return D_CREATE - 创建时间
     */
    public Date getdCreate() {
        return dCreate;
    }

    /**
     * 设置创建时间
     *
     * @param dCreate 创建时间
     */
    public void setdCreate(Date dCreate) {
        this.dCreate = dCreate;
    }

    /**
     * 获取更新时间
     *
     * @return D_UPDATE - 更新时间
     */
    public Date getdUpdate() {
        return dUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param dUpdate 更新时间
     */
    public void setdUpdate(Date dUpdate) {
        this.dUpdate = dUpdate;
    }

    /**
     * @return T_CUST_ADDRESS
     */
    public String gettCustAddress() {
        return tCustAddress;
    }

    /**
     * @param tCustAddress
     */
    public void settCustAddress(String tCustAddress) {
        this.tCustAddress = tCustAddress == null ? null : tCustAddress.trim();
    }

    /**
     * 获取备注
     *
     * @return T_REMARK - 备注
     */
    public String gettRemark() {
        return tRemark;
    }

    /**
     * 设置备注
     *
     * @param tRemark 备注
     */
    public void settRemark(String tRemark) {
        this.tRemark = tRemark == null ? null : tRemark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nId=").append(nId);
        sb.append(", cOrderNo=").append(cOrderNo);
        sb.append(", nType=").append(nType);
        sb.append(", nStatus=").append(nStatus);
        sb.append(", nProductsAmount=").append(nProductsAmount);
        sb.append(", nServiceAmount=").append(nServiceAmount);
        sb.append(", nDiscountAmount=").append(nDiscountAmount);
        sb.append(", nTotalAmount=").append(nTotalAmount);
        sb.append(", cWechatPayOrderNo=").append(cWechatPayOrderNo);
        sb.append(", nUserId=").append(nUserId);
        sb.append(", cCustName=").append(cCustName);
        sb.append(", cMobile=").append(cMobile);
        sb.append(", cDeliver=").append(cDeliver);
        sb.append(", dDeliver=").append(dDeliver);
        sb.append(", dCreate=").append(dCreate);
        sb.append(", dUpdate=").append(dUpdate);
        sb.append(", tCustAddress=").append(tCustAddress);
        sb.append(", tRemark=").append(tRemark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}