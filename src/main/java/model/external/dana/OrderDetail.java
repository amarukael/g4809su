package model.external.dana;

public class OrderDetail {
    String orderId;
    String requestId;

    public OrderDetail(String orderId, String requestId) {
        this.orderId = orderId;
        this.requestId = requestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
