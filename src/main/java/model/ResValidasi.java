package model;

public class ResValidasi {
    boolean status;
    String remark;

    public ResValidasi(boolean status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
