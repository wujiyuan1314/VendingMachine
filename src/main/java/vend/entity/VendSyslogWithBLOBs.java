package vend.entity;

public class VendSyslogWithBLOBs extends VendSyslog {
    private String operUrl;

    private String operDescription;

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl == null ? null : operUrl.trim();
    }

    public String getOperDescription() {
        return operDescription;
    }

    public void setOperDescription(String operDescription) {
        this.operDescription = operDescription == null ? null : operDescription.trim();
    }
}