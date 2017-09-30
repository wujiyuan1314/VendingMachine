package vend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class VendGoods {
    private Integer id;

    private String goodsName;

    private String pic;

    private BigDecimal price;

    private String goodsInfo;

    private Integer heatChno;

    private Integer coldChno;

    private Date createTime;

    private Date updateTime;

    private String pic1;

    private String pic2;

    private String pic3;

    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotEmpty(message="商品名不能为空")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }
    @NotEmpty(message="商品图片不能为空")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
    @NotNull(message="价格不能为空")
    @Range(min=0,max=100,message="价格必须在0到100之间")
    @Digits(integer=2,fraction=2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Length(min=0,max=300,message="长度不能超过300字")
    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo == null ? null : goodsInfo.trim();
    }
    @NotNull(message="热饮通道号不能为空")
    @Range(min=1,max=3,message="值必须在1到3之间")
    public Integer getHeatChno() {
        return heatChno;
    }

    public void setHeatChno(Integer heatChno) {
        this.heatChno = heatChno;
    }
    @NotNull(message="冷饮通道号不能为空")
    @Range(min=17,max=19,message="值必须在17到19之间")
    public Integer getColdChno() {
        return coldChno;
    }

    public void setColdChno(Integer coldChno) {
        this.coldChno = coldChno;
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

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
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
}