package vend.entity;

import java.util.Date;

public class VendAuthorize {
    private Integer id;

    private String authorizeName;

    private String authorizeIntro;

    private String usercode;

    private Date createTime;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;

    private String extend6;

    private String extend7;

    private String extend8;

    private String extend9;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorizeName() {
        return authorizeName;
    }

    public void setAuthorizeName(String authorizeName) {
        this.authorizeName = authorizeName == null ? null : authorizeName.trim();
    }

    public String getAuthorizeIntro() {
        return authorizeIntro;
    }

    public void setAuthorizeIntro(String authorizeIntro) {
        this.authorizeIntro = authorizeIntro == null ? null : authorizeIntro.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getExtend9() {
        return extend9;
    }

    public void setExtend9(String extend9) {
        this.extend9 = extend9 == null ? null : extend9.trim();
    }
}