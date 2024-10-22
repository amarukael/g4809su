package model.external.dana;

import java.util.List;

public class ReqBodyOrderDetail {
    List<OrderDetail> orderIdentifiers;

    public ReqBodyOrderDetail(List<OrderDetail> orderIdentifiers) {
        this.orderIdentifiers = orderIdentifiers;
    }

    public List<OrderDetail> getOrderIdentifiers() {
        return orderIdentifiers;
    }

    public void setOrderIdentifiers(List<OrderDetail> orderIdentifiers) {
        this.orderIdentifiers = orderIdentifiers;
    }
}
