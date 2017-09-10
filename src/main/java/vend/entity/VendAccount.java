package vend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class VendAccount {
    private String usercode;

    private BigDecimal ownAmount;

    private String moneyencrypt;

    private Date createTime;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public BigDecimal getOwnAmount() {
        return ownAmount;
    }

    public void setOwnAmount(BigDecimal ownAmount) {
        this.ownAmount = ownAmount;
    }

    public String getMoneyencrypt() {
        return moneyencrypt;
    }

    public void setMoneyencrypt(String moneyencrypt) {
        this.moneyencrypt = moneyencrypt == null ? null : moneyencrypt.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @NotEmpty(message="提现金额不能为空")
    @Pattern(regexp="/^[1-9]*[1-9][0-9]*$/",message="只能是正整数")
    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }
}