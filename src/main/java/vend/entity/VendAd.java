package vend.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class VendAd {
    private Integer id;

    private String adName;

    private Integer picInterval;

    private String pic1;

    private String pic2;

    private String pic3;

    private String pic4;

    private String pic5;

    private String pic6;

    private String video;

    private String height;

    private String width;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

    private String type;

    private String machineId;

    private String usercode;

    private String ismachineuse;

    private String extend4;

    private String extend5;

    private String extend6;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotEmpty(message="���������Ϊ��")
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public Integer getPicInterval() {
        return picInterval;
    }

    public void setPicInterval(Integer picInterval) {
        this.picInterval = picInterval;
    }
    @NotEmpty(message="图片1不能为空")
    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }
    @NotEmpty(message="图片2不能为空")
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

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4 == null ? null : pic4.trim();
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5 == null ? null : pic5.trim();
    }

    public String getPic6() {
        return pic6;
    }

    public void setPic6(String pic6) {
        this.pic6 = pic6 == null ? null : pic6.trim();
    }
    @NotEmpty(message="视频不能为空")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }
    @NotNull(message="开始时间不能为空")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @NotNull(message="结束时间不能为空")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
    @NotEmpty(message="视频名不能为空")
    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }
    @NotEmpty(message="广告屏样式不能为空")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getIsmachineuse() {
        return ismachineuse;
    }

    public void setIsmachineuse(String ismachineuse) {
        this.ismachineuse = ismachineuse == null ? null : ismachineuse.trim();
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
}