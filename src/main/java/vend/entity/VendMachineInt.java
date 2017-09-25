package vend.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class VendMachineInt {
    private Integer id;

    private Integer belongMachine;

    private Integer goodsId;

    private String goodsName;

    private String wareName;

    private String hotStatus;

    private Integer waterOutTime;

    private Integer grainOutTime;

    private Date createTime;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;

    private String extend6;

    private String extend7;

    private String extend8;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull(message="所属机器不能为空")
    public Integer getBelongMachine() {
        return belongMachine;
    }

    public void setBelongMachine(Integer belongMachine) {
        this.belongMachine = belongMachine;
    }
    @NotNull(message="商品Id不能为空")
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    @NotEmpty(message="商品名不能为空")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }
    @NotEmpty(message="冷热状态不能为空")
    public String getHotStatus() {
        return hotStatus;
    }

    public void setHotStatus(String hotStatus) {
        this.hotStatus = hotStatus == null ? null : hotStatus.trim();
    }
    @NotNull(message="出水时间不能为空")
    @Pattern(regexp="^\\+?[1-9][0-9]*$",message="只能输入正整数")
    public Integer getWaterOutTime() {
        return waterOutTime;
    }
   
    public void setWaterOutTime(Integer waterOutTime) {
        this.waterOutTime = waterOutTime;
    }
    @NotNull(message="出料时间不能为空")
    @Pattern(regexp="^\\+?[1-9][0-9]*$",message="只能输入正整数")
    public Integer getGrainOutTime() {
        return grainOutTime;
    }

    public void setGrainOutTime(Integer grainOutTime) {
        this.grainOutTime = grainOutTime;
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

    public String getExtend6() {
        return extend6;
    }

    public void setExtend6(String extend6) {
        this.extend6 = extend6 == null ? null : extend6.trim();
    }

    public String getExtend7() {
        return extend7;
    }

    public void setExtend7(String extend7) {
        this.extend7 = extend7 == null ? null : extend7.trim();
    }

    public String getExtend8() {
        return extend8;
    }

    public void setExtend8(String extend8) {
        this.extend8 = extend8 == null ? null : extend8.trim();
    }
}