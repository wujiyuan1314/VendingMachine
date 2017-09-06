package vend.entity;

public class VendPara {
    private Integer id;

    private String paraCode;

    private String paraContent;

    private String extend1;

    private String extend2;

    private String extend3;

    private String desprision;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    public String getParaContent() {
        return paraContent;
    }

    public void setParaContent(String paraContent) {
        this.paraContent = paraContent == null ? null : paraContent.trim();
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

    public String getDesprision() {
        return desprision;
    }

    public void setDesprision(String desprision) {
        this.desprision = desprision == null ? null : desprision.trim();
    }
}