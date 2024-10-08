package model.mnm.webhook.webhook;

public class Conversation {
    private String id;
    private Origin origin;
    private String expiration_timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getExpiration_timestamp() {
        return expiration_timestamp;
    }

    public void setExpiration_timestamp(String expiration_timestamp) {
        this.expiration_timestamp = expiration_timestamp;
    }
}
