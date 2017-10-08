package vend.entity;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class VendUser {
    private String usercode;

    private String parentUsercode;

    private String username;

    private String password;

    private Integer roleId;

    private String mobile;

    private String address;

    private String linkman;

    private String wechatpubNo;

    private Date createTime;

    private Date updateTime;

    private String permissionList;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;

    private String extend6;

    private Integer attendNum;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getParentUsercode() {
        return parentUsercode;
    }

    public void setParentUsercode(String parentUsercode) {
        this.parentUsercode = parentUsercode == null ? null : parentUsercode.trim();
    }
    @NotEmpty(message="用户名不能为空")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    @NotEmpty(message="密码不能为空")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    @NotEmpty(message="手机号不能为空")
    @Pattern(regexp="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message="手机号格式错误")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
    @NotEmpty(message="地址不能为空")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
    @NotEmpty(message="联系人不能为空")
    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getWechatpubNo() {
        return wechatpubNo;
    }

    public void setWechatpubNo(String wechatpubNo) {
        this.wechatpubNo = wechatpubNo == null ? null : wechatpubNo.trim();
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

    public String getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(String permissionList) {
        this.permissionList = permissionList == null ? null : permissionList.trim();
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

    public Integer getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(Integer attendNum) {
        this.attendNum = attendNum;
    }
}