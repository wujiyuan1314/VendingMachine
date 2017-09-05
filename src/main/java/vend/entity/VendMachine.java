package vend.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class VendMachine {
    private Integer id;

    private String machineName;

    private String machineCode;

    private String usercode;

    private String machineType;

    private String useStatus;

    private String waterStatus;

    private String cupStatus;

    private String heatStatus;

    private String cleanStatus;

    private Integer clientQrcode;

    private Integer shopQrcode;

    private Integer adId;

    private Date createTime;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotEmpty(message="机器名不能为空")
    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }
    @NotEmpty(message="机器码不能为空")
    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode == null ? null : machineCode.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType == null ? null : machineType.trim();
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
    }

    public String getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        this.waterStatus = waterStatus == null ? null : waterStatus.trim();
    }

    public String getCupStatus() {
        return cupStatus;
    }

    public void setCupStatus(String cupStatus) {
        this.cupStatus = cupStatus == null ? null : cupStatus.trim();
    }

    public String getHeatStatus() {
        return heatStatus;
    }

    public void setHeatStatus(String heatStatus) {
        this.heatStatus = heatStatus == null ? null : heatStatus.trim();
    }

    public String getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(String cleanStatus) {
        this.cleanStatus = cleanStatus == null ? null : cleanStatus.trim();
    }

    public Integer getClientQrcode() {
        return clientQrcode;
    }

    public void setClientQrcode(Integer clientQrcode) {
        this.clientQrcode = clientQrcode;
    }

    public Integer getShopQrcode() {
        return shopQrcode;
    }

    public void setShopQrcode(Integer shopQrcode) {
        this.shopQrcode = shopQrcode;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
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

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4 == null ? null : extend4.trim();
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5 == null ? null : extend5.trim();
    }
}