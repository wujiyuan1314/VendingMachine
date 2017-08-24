package vend.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class CodeCatalog {
    private String codeno;

    private String codename;

    private String codedescribe;

    private Integer itemnolength;

    private String kind;

    private String parentcodeno;
    @NotEmpty(message="编码不能为空")
    public String getCodeno() {
        return codeno;
    }

    public void setCodeno(String codeno) {
        this.codeno = codeno == null ? null : codeno.trim();
    }
    @NotEmpty(message="编码名不能为空")
    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename == null ? null : codename.trim();
    }

    public String getCodedescribe() {
        return codedescribe;
    }

    public void setCodedescribe(String codedescribe) {
        this.codedescribe = codedescribe == null ? null : codedescribe.trim();
    }

    public Integer getItemnolength() {
        return itemnolength;
    }

    public void setItemnolength(Integer itemnolength) {
        this.itemnolength = itemnolength;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getParentcodeno() {
        return parentcodeno;
    }

    public void setParentcodeno(String parentcodeno) {
        this.parentcodeno = parentcodeno == null ? null : parentcodeno.trim();
    }
}