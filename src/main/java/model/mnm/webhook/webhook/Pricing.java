package model.mnm.webhook.webhook;

public class Pricing {
    private boolean billable;
    private String category;
    private String pricing_model;

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPricing_model() {
        return pricing_model;
    }

    public void setPricing_model(String pricing_model) {
        this.pricing_model = pricing_model;
    }
}
